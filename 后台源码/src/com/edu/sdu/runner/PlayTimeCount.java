package com.edu.sdu.runner;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @功能	
 * 计算在state=0,1,3状态下使用设备时长规格为
 * <10s
 * 10s-60s
 * 1min-3mins
 * 10min-30mins
 * 30min-60mins
 * 1hour-2hours
 * 2hours-4hours
 * >4hours
 * 的用户数量
 * 
 * @author 谢世杰
 * */

public class PlayTimeCount {

	/**
	 * 把state=0,1,3的用户提取出来，并计算每一个用户在每一个APP下使用时长
	 * */
	public static class map_choose
			extends Mapper<LongWritable, Text, Text, Text> {

		@Override
		protected void map(LongWritable key, Text value,
				Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			String[] files = line.split("\t");
			String state = files[5];
			String app_user = files[0] + "\t" + files[2];
			String time = files[7];
			if (state.equals("0")) {
				context.write(new Text(app_user), new Text(time));
			} else if (state.equals("1")) {
				context.write(new Text(app_user), new Text(time));
			} else if (state.equals("3")) {
				context.write(new Text(app_user), new Text(time));
			}
		}

	}

	public static class reduce_choose extends Reducer<Text, Text, Text, Text> {

		@Override
		protected void reduce(Text key, Iterable<Text> value,
				Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			int count = 0;
			for (Text text : value) {
				int num_text = Integer.parseInt(text.toString());
				count += num_text;
			}
			String str_count = count + "";
			context.write(key, new Text(str_count));
		}

	}

	/**
	 * 根据时长获取相应时间段的用户数量
	 * */
	public static class map_playCount
			extends Mapper<LongWritable, Text, Text, Text> {

		@Override
		protected void map(LongWritable key, Text value,
				Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			String[] files = line.split("\t");
			int second = Integer.parseInt(files[2]);
			int min = (int) second / 60;
			int hour = (int) min / 60;
			String app = files[0];
			String user = files[1];
			if (second < 10) {
				context.write(new Text(app + "\t" + "<10s"), new Text(user));
			} else if (second < 60 & second >= 10) {
				context.write(new Text(app + "\t" + "10s-60s"), new Text(user));
			} else {// >=60s
				if (min < 3 & min >= 1) {
					context.write(new Text(app + "\t" + "1min-3mins"),
							new Text(user));
				} else if (min < 10 & min >= 3) {
					context.write(new Text(app + "\t" + "3min-10mins"),
							new Text(user));
				} else if (min < 30 & min >= 10) {
					context.write(new Text(app + "\t" + "10min-30mins"),
							new Text(user));
				} else if (min < 60 & min >= 30) {
					context.write(new Text(app + "\t" + "30min-60mins"),
							new Text(user));
				} else {// >=60mins
					if (hour < 2 & hour >= 1) {
						context.write(new Text(app + "\t" + "1hour-2hours"),
								new Text(user));
					} else if (hour < 4 & hour >= 2) {
						context.write(new Text(app + "\t" + "2hours-4hours"),
								new Text(user));
					} else {// >=4hours
						context.write(new Text(app + "\t" + ">4hours"),
								new Text(user));
					}
				}
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
		JobConf conf = new JobConf(PlayTimeCount.class);

		// 第一个job的配置
		Job job1 = new Job(conf, "join1");
		job1.setJarByClass(PlayTimeCount.class);

		job1.setMapperClass(map_choose.class);
		job1.setReducerClass(reduce_choose.class);

		job1.setMapOutputKeyClass(Text.class);// map阶段的输出的key
		job1.setMapOutputValueClass(Text.class);// map阶段的输出的value

		job1.setOutputKeyClass(Text.class);// reduce阶段的输出的key
		job1.setOutputValueClass(Text.class);// reduce阶段的输出的value

		// 加入控制容器
		ControlledJob ctrljob1 = new ControlledJob(conf);
		ctrljob1.setJob(job1);
		// job1的输入输出文件路径
		FileInputFormat.addInputPath(job1, new Path(args[0]));
		FileOutputFormat.setOutputPath(job1, new Path(args[1]));

		// 第二个job的配置
		Job job2 = new Job(conf, "Join2");
		job2.setJarByClass(PlayTimeCount.class);

		job2.setMapperClass(map_playCount.class);
		job2.setReducerClass(reduce_playCount.class);

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
		FileInputFormat.addInputPath(job2, new Path(args[1]));

		// 输出路径从新传入一个参数，这里需要注意，因为我们最后的输出文件一定要是没有出现过得
		// 因此我们在这里new Path(args[2])因为args[2]在上面没有用过，只要和上面不同就可以了
		FileOutputFormat.setOutputPath(job2, new Path(args[2]));

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
	}
}
