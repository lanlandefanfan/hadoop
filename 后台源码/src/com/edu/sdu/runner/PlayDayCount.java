package com.edu.sdu.runner;

import java.io.IOException;

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

import com.edu.sdu.bean.Sysmbol;

/**
 * function 计算在最近一个月内使用APP在以下时间段 1 2-3 4-7 8-14 15-30 的用户数量
 * 
 * @author 谢世杰
 */

public class PlayDayCount {

	/**
	 * 去重操作，去掉在同一天重复登录同一个APP的用户
	 */
	public static class map_keeyOnly
			extends Mapper<LongWritable, Text, Text, NullWritable> {

		@Override
		protected void map(LongWritable key, Text value,
				Mapper<LongWritable, Text, Text, NullWritable>.Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			String[] files = line.split("\t");
			String[] datetime = files[6].split(" ");
			String date = datetime[0];
			String m_value = files[0] + "\t" + files[3] + "\t" + date;
			context.write(new Text(m_value), NullWritable.get());
		}

	}

	public static class reduce_keeyOnly
			extends Reducer<Text, NullWritable, Text, NullWritable> {

		@Override
		protected void reduce(Text key, Iterable<NullWritable> value,
				Reducer<Text, NullWritable, Text, NullWritable>.Context context)
				throws IOException, InterruptedException {
			context.write(key, NullWritable.get());
		}

	}

	/**
	 * 计算一个用户在相应的APP下最近一个月的登录天数
	 */
	public static class map_appUserDay
			extends Mapper<LongWritable, Text, Text, Text> {

		@Override
		protected void map(LongWritable key, Text value,
				Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			String[] files = line.split("\t");
			String m_value = files[0] + "\t" + files[1];
			context.write(new Text(m_value), new Text(files[2]));

		}

	}

	public static class reduce_appUserDay
			extends Reducer<Text, Text, Text, Text> {

		@Override
		protected void reduce(Text key, Iterable<Text> value,
				Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			int count = 0;
			for (Text text : value) {
				count++;
			}
			String real_count = count + "";
			context.write(key, new Text(real_count));
		}

	}

	/**
	 * 根据用户的登录天数，区分在不同时间段的用户数量
	 */
	public static class map_playCount
			extends Mapper<LongWritable, Text, Text, Text> {

		@Override
		protected void map(LongWritable key, Text value,
				Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			String[] files = line.split("\t");
			int day = Integer.parseInt(files[2]);
			String state = null;
			if (day == 1) {
				state = files[0] + "\t" + "1";
				context.write(new Text(state), new Text(files[1]));
			} else if (day <= 3 & day >= 2) {
				state = files[0] + "\t" + "2-3";
				context.write(new Text(state), new Text(files[1]));
			} else if (day <= 7 & day >= 4) {
				state = files[0] + "\t" + "4-7";
				context.write(new Text(state), new Text(files[1]));
			} else if (day <= 14 & day >= 8) {
				state = files[0] + "\t" + "8-14";
				context.write(new Text(state), new Text(files[1]));
			} else if (day <= 30 & day >= 15) {
				state = files[0] + "\t" + "15-30";
				context.write(new Text(state), new Text(files[1]));
			}
		}

	}

	public static class reduce_playCount
			extends Reducer<Text, Text, Text, Text> {

		@Override
		protected void reduce(Text key, Iterable<Text> value,
				Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			int count = 0;
			for (Text text : value) {
				count++;
			}
			String real_count = count + "";
			context.write(key, new Text(real_count));
		}

	}

	public static void main(String[] args) throws Exception {
		Sysmbol.startDay = "2017-05-01.txt";
		JobConf conf = new JobConf(PlayDayCount.class);

		// 第一个job的配置
		Job job1 = new Job(conf, "join1");
		job1.setJarByClass(PlayDayCount.class);

		job1.setMapperClass(map_keeyOnly.class);
		job1.setReducerClass(reduce_keeyOnly.class);

		job1.setMapOutputKeyClass(Text.class);// map阶段的输出的key
		job1.setMapOutputValueClass(NullWritable.class);// map阶段的输出的value

		job1.setOutputKeyClass(Text.class);// reduce阶段的输出的key
		job1.setOutputValueClass(NullWritable.class);// reduce阶段的输出的value

		// 加入控制容器
		ControlledJob ctrljob1 = new ControlledJob(conf);
		ctrljob1.setJob(job1);
		// job1的输入输出文件路径
		//FileInputFormat.addInputPath(job1, new Path(args[0]));
		//FileOutputFormat.setOutputPath(job1, new Path(args[1]));
		FileInputFormat.addInputPath(job1, new Path("/usr/local/hadoop/file/usr"));
		FileOutputFormat.setOutputPath(job1, new Path("/usr/local/hadoop/file/PlayDayCountCache1"));

		// 第二个job的配置
		Job job2 = new Job(conf, "Join2");
		job2.setJarByClass(PlayDayCount.class);

		job2.setMapperClass(map_appUserDay.class);
		job2.setReducerClass(reduce_appUserDay.class);

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
		//FileInputFormat.addInputPath(job2, new Path(args[1]));
		FileInputFormat.addInputPath(job2, new Path("/usr/local/hadoop/file/PlayDayCountCache1"));

		// 输出路径从新传入一个参数，这里需要注意，因为我们最后的输出文件一定要是没有出现过得
		// 因此我们在这里new Path(args[2])因为args[2]在上面没有用过，只要和上面不同就可以了
		//FileOutputFormat.setOutputPath(job2, new Path(args[2]));
		FileOutputFormat.setOutputPath(job2, new Path("/usr/local/hadoop/file/PlayDayCountCache2"));

		// 第三个job的配置
		Job job3 = new Job(conf, "Join2");
		job3.setJarByClass(PlayDayCount.class);

		job3.setMapperClass(map_playCount.class);
		job3.setReducerClass(reduce_playCount.class);

		job3.setMapOutputKeyClass(Text.class);// map阶段的输出的key
		job3.setMapOutputValueClass(Text.class);// map阶段的输出的value

		job3.setOutputKeyClass(Text.class);// reduce阶段的输出的key
		job3.setOutputValueClass(Text.class);// reduce阶段的输出的value

		// 作业2加入控制容器
		ControlledJob ctrljob3 = new ControlledJob(conf);
		ctrljob3.setJob(job3);

		ctrljob3.addDependingJob(ctrljob2);

		/*FileInputFormat.addInputPath(job3, new Path(args[2]));
		FileOutputFormat.setOutputPath(job3, new Path(args[3]));*/
		FileInputFormat.addInputPath(job3, new Path("/usr/local/hadoop/file/PlayDayCountCache2"));
		FileOutputFormat.setOutputPath(job3, new Path("/usr/local/hadoop/file/PlayDayCount"));

		// 主的控制容器，控制上面的总的两个子作业
		JobControl jobCtrl = new JobControl("myctrl");

		// 添加到总的JobControl里，进行控制
		jobCtrl.addJob(ctrljob1);
		jobCtrl.addJob(ctrljob2);
		jobCtrl.addJob(ctrljob3);

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
	}
}
