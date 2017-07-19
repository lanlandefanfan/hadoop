package com.edu.sdu.runner;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
import com.edu.sdu.mapper.DailyNewUserMapper;
import com.edu.sdu.mapper.DownloadCountMapper;
import com.edu.sdu.reducer.DailyNewUserReducer;
import com.edu.sdu.reducer.DownloadCountReducer;
import com.edu.sdu.util.Database;
import com.edu.sdu.util.Net;
import com.edu.sdu.util.Tool;

/**
 * 下载量统计
 * @author 王宁
 *
 */
public class DownloadCount {

	public static void main(String[] args) {
		Sysmbol.startDay = args[0];
		Sysmbol.endDay = args[1];
		try {
			Configuration conf = new Configuration();
			Job job = Job.getInstance(conf, "DownloadCount");
			job.setJarByClass(DownloadCount.class);
			job.setMapperClass(DownloadCountMapper.class);
			job.setReducerClass(DownloadCountReducer.class);
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
				String downloadCount = val[1]; // 当天新增用户
				String date = val[2]; // 当天日期
				
				String limitData[] = database.getAlertData(app_key, "0");
				if (limitData[0] != null) {
					String id = limitData[0];	// 预警id
					int days = Integer.parseInt(limitData[1]); // 计算前days天的数据
					String limit = limitData[2]; // 限制波动率
					int trigger = Integer.parseInt(limitData[3]); // 0:<  1:>
					
					int total = 0;	// 前n天下载量总数
					int computeDays = 0; // n
					for (int i = 1; i <= days; i++) {
						String predate = Tool.getPreNdayDate(date, i);
						String preDownloadCount = database.getDownloadCount(app_key, predate);
						if(preDownloadCount != null){
							total += Integer.parseInt(preDownloadCount);
							computeDays++;
						}
					}
					if(computeDays > 0){
						int preAverageData = total / computeDays;	// 之前的数据平均
						if(Tool.getIsAlertOrNot(preAverageData, Integer.parseInt(downloadCount), Integer.parseInt(limit), trigger)){
							flag = true;
							System.out.println(preAverageData);
							System.out.println(downloadCount);
							if(!postStr.equals(""))
								postStr += ",";
							postStr += id;
						}
					}
				}
				
				database.updateDownload(app_key, downloadCount, date);
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
