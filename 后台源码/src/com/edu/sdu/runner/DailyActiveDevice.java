package com.edu.sdu.runner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.edu.sdu.util.Database;
import com.sdu.edu.bean.Sysmbol;

/**
 * function 日活跃设备
 * 
 * @author 谢世杰
 */

public class DailyActiveDevice {
	/**
	 * 去重操作，把一天之内多次登录同一个APP的用户去掉
	 */
	public static class map_keeyOnly
			extends Mapper<LongWritable, Text, Text, Text> {

		@Override
		protected void map(LongWritable key, Text value,
				Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			String[] files = line.split("\t");
			String m_value = files[0] + "\t" + files[3];
			String time = files[6];
			context.write(new Text(m_value), new Text(time));
		}
	}

	public static class reduce_keeyOnly
			extends Reducer<Text, Text, Text, Text> {

		@Override
		protected void reduce(Text key, Iterable<Text> value,
				Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			String time = null;
			for(Text val : value){
				time = val.toString();
				break;
			}
			context.write(key, new Text(time));
		}

	}

	/**
	 * 获取某APP下活跃用户数量
	 */
	public static class map_appUser
			extends Mapper<LongWritable, Text, Text, Text> {

		@Override
		protected void map(LongWritable key, Text value,
				Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			String[] files = line.split("\\s+");
			context.write(new Text(files[0] + "\t" + files[2]), new Text(files[1]));
		}
	}

	public static class reduce_appUser extends Reducer<Text, Text, Text, Text> {

		@Override
		protected void reduce(Text key, Iterable<Text> value,
				Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			int count = 0;
			for (Text test : value) {
				count++;
			}
			String finalcount = count + "";
			context.write(key, new Text(finalcount));
		}

	}

	public static void main(String[] args) throws Exception {
		Sysmbol.startDay = args[0];
		Sysmbol.endDay = args[1];
		JobConf conf = new JobConf(DailyActiveDevice.class);
		Configuration configuration = new Configuration();

		// 第一个job的配置
		Job job1 = Job.getInstance(configuration);
		job1.setJarByClass(DailyActiveDevice.class);

		job1.setMapperClass(map_keeyOnly.class);
		job1.setReducerClass(reduce_keeyOnly.class);

		job1.setMapOutputKeyClass(Text.class);// map阶段的输出的key
		job1.setMapOutputValueClass(Text.class);// map阶段的输出的value

		job1.setOutputKeyClass(Text.class);// reduce阶段的输出的key
		job1.setOutputValueClass(Text.class);// reduce阶段的输出的value

		// 加入控制容器
		ControlledJob ctrljob1 = new ControlledJob(conf);
		ctrljob1.setJob(job1);
		// job1的输入输出文件路径
		/*FileInputFormat.addInputPath(job1, new Path(args[0]));
		FileOutputFormat.setOutputPath(job1, new Path(args[1]));*/
		FileInputFormat.addInputPath(job1, new Path(args[2]));
		FileSystem fs2 = FileSystem.get(conf);
		Path op2 = new Path(args[3]);
		if (fs2.exists(op2)) {
			fs2.delete(op2, true);
			System.out.println("存在此输出路径，已删除！！！");
		}
		FileOutputFormat.setOutputPath(job1, op2);

		// 第二个job的配置
		Job job2 = Job.getInstance(configuration);
		job2.setJarByClass(DailyActiveDevice.class);

		job2.setMapperClass(map_appUser.class);
		job2.setReducerClass(reduce_appUser.class);

		job2.setMapOutputKeyClass(Text.class);// map阶段的输出的key
		job2.setMapOutputValueClass(Text.class);// map阶段的输出的value

		job2.setOutputKeyClass(Text.class);// reduce阶段的输出的key
		job2.setOutputValueClass(Text.class);// reduce阶段的输出的value

		// 作业2加入控制容器
		ControlledJob ctrljob2 = new ControlledJob(conf);
		ctrljob2.setJob(job2);

		// 设置多个作业直接的依赖关系
		// 如下所写：
		// 意思为job2的启动，依赖于job1作业的完成

		ctrljob2.addDependingJob(ctrljob1);

		// 输入路径是上一个作业的输出路径，因此这里填args[1],要和上面对应好
		FileInputFormat.addInputPath(job2, new Path(args[3] + "/part-r-00000"));
		//FileInputFormat.addInputPath(job2, new Path(args[1]));

		// 输出路径从新传入一个参数，这里需要注意，因为我们最后的输出文件一定要是没有出现过得
		// 因此我们在这里new Path(args[2])因为args[2]在上面没有用过，只要和上面不同就可以了
		FileSystem fs3 = FileSystem.get(conf);
		Path op3 = new Path(args[4]);
		if (fs3.exists(op3)) {
			fs3.delete(op3, true);
			System.out.println("存在此输出路径，已删除！！！");
		}
		FileOutputFormat.setOutputPath(job2, op3);
		//FileOutputFormat.setOutputPath(job2, new Path(args[2]));

		// 主的控制容器，控制上面的总的两个子作业
		JobControl jobCtrl = new JobControl("myctrl");

		// 添加到总的JobControl里，进行控制
		jobCtrl.addJob(ctrljob1);
		jobCtrl.addJob(ctrljob2);

		// 在线程启动，记住一定要有这个
		Thread t = new Thread(jobCtrl);
		t.start();

		while (true) {

			if (jobCtrl.allFinished()) {// 如果作业成功完成，就打印成功作业的信息
				System.out.println(jobCtrl.getSuccessfulJobList());
				jobCtrl.stop();
				break;
			}
		}
		
		/*向数据库写数据操作*/
		Database database = Database.getInstance();
		FileSystem fs0 = FileSystem.get(conf);
		FSDataInputStream fdis = fs0.open(new Path(args[4] + "/part-r-00000"));
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fdis));
		
		String str = null;
		boolean flag = false;
		while ((str = bufferedReader.readLine()) != null) {
			String[] val = str.split("\\s+");
			flag = database.updateAppCriticalData(val[0],
					"", "", "", val[2], "", "", val[1]);
		}
		System.out.println(flag);
	}
}

