package com.edu.sdu.runner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.edu.sdu.bean.Sysmbol;
import com.edu.sdu.bean.TimeValueBean;
import com.edu.sdu.mapper.DailyNewDeviceMapper;
import com.edu.sdu.reducer.DailyNewDeviceReducer;
import com.edu.sdu.util.Database;
import com.edu.sdu.util.Net;
import com.edu.sdu.util.Tool;

/**
 * 日新增设备统计
 * @author 王宁
 *
 */
public class DailyNewDevice {

	public static Date startDate;
	public static Date endDate;
	
	public static void main(String[] args) {
		Sysmbol.startDay = args[0];
		Sysmbol.endDay = args[1];
		try {
			Configuration conf = new Configuration();
			Job job = Job.getInstance(conf, "DailyNewDevice");
			job.setJarByClass(DailyNewDevice.class);
			job.setMapperClass(DailyNewDeviceMapper.class);
			job.setReducerClass(DailyNewDeviceReducer.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(TimeValueBean.class);
			FileInputFormat.addInputPath(job, new Path(args[2]));
			
			FileSystem fs2 = FileSystem.get(conf);
			Path op2 = new Path(args[3]);
			if (fs2.exists(op2)) {
				fs2.delete(op2, true);
				System.out.println("存在此输出路径，已删除！！！");
			}
			FileOutputFormat.setOutputPath(job, op2);
			job.waitForCompletion(true);
			
			/*向数据库写数据操作*/
			Database database = Database.getInstance();
			FileSystem fs0 = FileSystem.get(conf);
			FSDataInputStream fdis = fs0.open(new Path(args[3] + "/part-r-00000"));
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fdis));
			
			String str = null;
			boolean flag = false;
			String postStr = "";
			while ((str = bufferedReader.readLine()) != null) {
				String[] val = str.split("\\s+");
				String app_key = val[0]; // 当前appkey
				String newDevice = val[1]; // 当天新增设备
				String date = val[2]; // 当天日期
				
				String limitData[] = database.getAlertData(app_key, "2");
				if (limitData[0] != null) {
					String id = limitData[0];	// 预警id
					int days = Integer.parseInt(limitData[1]); // 计算前days天的数据
					String limit = limitData[2]; // 限制波动率
					int trigger = Integer.parseInt(limitData[3]); // 0:<  1:>
					
					int total = 0;	// 前n天新增设备总数
					int computeDays = 0; // n
					for (int i = 1; i <= days; i++) {
						String predate = Tool.getPreNdayDate(date, i);
						String criticalData[] = database.getAppCriticalData(app_key, predate);
						if(criticalData[0] != null){
							total += Integer.parseInt(criticalData[1]);
							computeDays++;
						}
					}
					if(computeDays > 0){
						int preAverageData = total / computeDays;	// 之前的数据平均
						if(Tool.getIsAlertOrNot(preAverageData, Integer.parseInt(newDevice), Integer.parseInt(limit), trigger)){
							flag = true;
							System.out.println(preAverageData);
							System.out.println(newDevice);
							if(!postStr.equals(""))
								postStr += ",";
							postStr += id;
						}
					}
				}
				
				database.updateAppCriticalData(val[0], 
						"", val[1], "", "", "", "", val[2]);
				database.updateRemainDevice(val[0], val[2], val[1], "", "", "", "", "", "", "", "", "");
			}
			System.out.println(flag);
			if(flag){
				if(!postStr.equals(""))
					System.out.println(postStr);
					Net.sendMail(postStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
