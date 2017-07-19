package com.edu.sdu.runner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.edu.sdu.bean.Sysmbol;
import com.edu.sdu.bean.TimeValueBean;
import com.edu.sdu.mapper.NewDeviceMapper;
import com.edu.sdu.reducer.NewDeviceReducer;
import com.edu.sdu.util.Database;

/**
 * 新设备首次使用时长
 * @author 杜仲楠
 *
 */
public class NewDevice {
	public static void main(String[] args) throws Exception {
		Sysmbol.startDay = args[0];
		Sysmbol.endDay = args[1];
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);

		job.setJarByClass(NewDevice.class);

		job.setMapperClass(NewDeviceMapper.class);
		job.setReducerClass(NewDeviceReducer.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(TimeValueBean.class);

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
		/* job.submit(); */
		job.waitForCompletion(true);
		
		/*向数据库写数据操作*/
		Database database = Database.getInstance();
		FileSystem fs0 = FileSystem.get(conf);
		FSDataInputStream fdis = fs0.open(new Path(args[3] + "/part-r-00000"));
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fdis));
		String str = null;
		int index = 0;
		String app_key = "";
		String one_fours = ""; String five_tens = ""; String eleven_thirtys = ""; 
		String thirty_sixtys = ""; String one_threem = ""; String four_tenm = ""; String eleven_thirtym = "";
		String thirty_sixtym = ""; String sixtym_more = "";
		String time = null;
		while ((str = bufferedReader.readLine()) != null) {
			String val[] = str.split("\\s+");
			app_key = val[0];
			
			val = bufferedReader.readLine().split("\\s+");
			one_fours = val[1];
			time = val[2];
			
			five_tens = bufferedReader.readLine().split("\\s+")[1];
			eleven_thirtys = bufferedReader.readLine().split("\\s+")[1];
			thirty_sixtys = bufferedReader.readLine().split("\\s+")[1];
			one_threem = bufferedReader.readLine().split("\\s+")[1];
			four_tenm = bufferedReader.readLine().split("\\s+")[1];
			eleven_thirtym = bufferedReader.readLine().split("\\s+")[1];
			thirty_sixtym = bufferedReader.readLine().split("\\s+")[1];
			sixtym_more = bufferedReader.readLine().split("\\s+")[1];
			
			boolean flag = database.updateNewDeviceData(app_key, one_fours, five_tens, eleven_thirtys, 
					thirty_sixtys, one_threem, four_tenm, eleven_thirtym, thirty_sixtym, sixtym_more, time); 
			System.out.println(flag);
		}
	}
}
