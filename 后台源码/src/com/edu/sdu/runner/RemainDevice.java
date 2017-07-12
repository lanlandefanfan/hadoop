package com.edu.sdu.runner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import com.edu.sdu.mapper.RemainDeviceMapper;
import com.edu.sdu.reducer.RemainDeviceReducer;
import com.edu.sdu.util.Database;
import com.edu.sdu.util.Tool;
import com.sdu.edu.bean.RemainOprBean;
import com.sdu.edu.bean.Sysmbol;

/**
 * 留存设备计算
 * @author 王宁
 *
 */
public class RemainDevice {

	public static void main(String[] args) {
		Sysmbol.startDay = args[0];
		Sysmbol.endDay = args[1];
		try {
			Configuration conf = new Configuration();
			Job job = Job.getInstance(conf, "RemainDevice");
			job.setJarByClass(RemainDevice.class);
			job.setMapperClass(RemainDeviceMapper.class);
			job.setReducerClass(RemainDeviceReducer.class);
			
			job.setMapOutputKeyClass(Text.class);// map阶段的输出的key
			job.setMapOutputValueClass(RemainOprBean.class);// map阶段的输出的value
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			FileInputFormat.addInputPath(job, new Path(args[2]));
			FileInputFormat.addInputPath(job, new Path(args[3]));
			
			FileSystem fs2 = FileSystem.get(conf);
			Path op2 = new Path(args[4]);
			if (fs2.exists(op2)) {
				fs2.delete(op2, true);
				System.out.println("存在此输出路径，已删除！！！");
			}
			FileOutputFormat.setOutputPath(job, op2);
			job.waitForCompletion(true);
			
			/*向数据库写数据操作*/
			Database database = Database.getInstance();
			FileSystem fs0 = FileSystem.get(conf);
			FSDataInputStream fdis = fs0.open(new Path(args[4] + "/part-r-00000"));
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fdis));
			
			String str = null;
			String start = Sysmbol.startDay.substring(0, Sysmbol.startDay.length() - 4);
			String end = Sysmbol.endDay.substring(0, Sysmbol.endDay.length() - 4);
			System.out.println(start);
			System.out.println(end);
			String date = start;
			int space = Tool.getSpaceDay(start, end);
			boolean flag = false;
			while ((str = bufferedReader.readLine()) != null) {
				String[] val = str.split("\\s+");
				String app_key = val[0];
				String count = val[1];
				
				String oneday = "", twoday = "", threeday = "", fourday = "",
						fiveday = "", sixday = "", sevenday = "";
				
				if(space == 1)
					oneday = count;
				else if (space == 2)
					twoday = count;
				else if (space == 3)
					threeday = count;
				else if (space == 4)
					fourday = count;
				else if (space == 5)
					fiveday = count;
				else if (space == 6)
					sixday = count;
				else if (space == 7)
					sevenday = count;
				flag = database.updateRemainDevice(app_key, date, "", oneday, twoday, 
						threeday, fourday, fiveday, sixday, sevenday);
			}
			System.out.println(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
