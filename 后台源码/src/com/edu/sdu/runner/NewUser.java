package com.edu.sdu.runner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.edu.sdu.mapper.DailyNewUserMapper;
import com.edu.sdu.mapper.DedupMapper;
import com.edu.sdu.mapper.NewUserMapper;
import com.edu.sdu.reducer.DailyNewUserReducer;
import com.edu.sdu.reducer.DedupReducer;
import com.edu.sdu.reducer.NewUserReducer;
import com.edu.sdu.util.Database;
import com.sdu.edu.bean.Sysmbol;
import com.sdu.edu.bean.TimeValueBean;

public class NewUser {

	public static void main(String[] args) {
		Sysmbol.startDay = args[0];
		Sysmbol.endDay = args[1];
		try {
			Configuration conf = new Configuration();
			Job job = Job.getInstance(conf, "NewUser");
			job.setJarByClass(NewUser.class);
			job.setMapperClass(NewUserMapper.class);
			job.setReducerClass(NewUserReducer.class);
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
			int index = 0;
			String one_fours = ""; String five_tens = ""; String eleven_thirtys = ""; 
			String thirty_sixtys = ""; String one_threem = ""; String four_tenm = ""; String eleven_thirtym = "";
			String thirty_sixtym = ""; String sixtym_more = "";
			String time = null;
			while ((str = bufferedReader.readLine()) != null) {
				String val[] = str.split("\\s+");
				String app_key = val[0];
				if(time == null)
					time = val[2];
				one_fours = val[1];
				five_tens = bufferedReader.readLine().split("\\s+")[1];
				eleven_thirtys = bufferedReader.readLine().split("\\s+")[1];
				thirty_sixtys = bufferedReader.readLine().split("\\s+")[1];
				one_threem = bufferedReader.readLine().split("\\s+")[1];
				four_tenm = bufferedReader.readLine().split("\\s+")[1];
				eleven_thirtym = bufferedReader.readLine().split("\\s+")[1];
				thirty_sixtym = bufferedReader.readLine().split("\\s+")[1];
				sixtym_more = bufferedReader.readLine().split("\\s+")[1];
				
				boolean flag = database.updateNewUserData(app_key, one_fours, five_tens, eleven_thirtys, 
						thirty_sixtys, one_threem, four_tenm, eleven_thirtym, thirty_sixtym, sixtym_more, time); 
				System.out.println(flag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
