package com.edu.sdu.mapper;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.sdu.edu.bean.AreaOrderBean;
import com.sun.xml.internal.ws.message.StringHeader;

public class AreaCountMapper extends Mapper<LongWritable, Text, AreaOrderBean, Text> {
	
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] fields = line.split("\\s+");
		String app_key = fields[0];
		String city = fields[11];
		String time = fields[6];
		String state = fields[5];
		AreaOrderBean bean = new AreaOrderBean(app_key, city, state);
		if(state.equals("0") || state.equals("5"))
			context.write(bean, new Text(time));
	}
}