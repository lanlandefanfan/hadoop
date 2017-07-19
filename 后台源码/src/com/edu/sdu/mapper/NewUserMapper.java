package com.edu.sdu.mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.edu.sdu.bean.TimeValueBean;

/**
 * 获取新用户首次使用时长的mapper
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
