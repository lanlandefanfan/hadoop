package com.edu.sdu.mapper;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.edu.sdu.bean.TimeValueBean;

/**
 * 新设备首次使用时长的mapper
 * @author 杜仲楠
 *
 */
public class NewDeviceMapper extends Mapper<LongWritable, Text, Text, TimeValueBean> {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, TimeValueBean>.Context context)
			throws IOException, InterruptedException {

		String line = value.toString();
		String[] fields = line.split("\\s+");
		
		String app_key = fields[0];
		String time = fields[6];
		int state = Integer.parseInt(fields[5]);
		String durtime = fields[8]; 
		
		if (state == 5) {
			context.write(new Text(app_key), new TimeValueBean(durtime, time));
		}

	}
}