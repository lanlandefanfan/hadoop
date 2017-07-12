package com.edu.sdu.mapper;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import com.edu.sdu.reducer.DedupReducer;
import com.sdu.edu.bean.TimeValueBean;

/**
 * 获取新用户使用时长的mapper
 * 
 * @author 王宁
 *
 */
public class NewUserMapper extends Mapper<LongWritable, Text, Text, TimeValueBean> {

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String line = ivalue.toString();
		String[] val = line.split("\\s+");
		
		String time = val[8];
		String app_key = val[0];
		String userTime = val[6];
		
		context.write(new Text(app_key), new TimeValueBean(time, userTime));
	}
}
