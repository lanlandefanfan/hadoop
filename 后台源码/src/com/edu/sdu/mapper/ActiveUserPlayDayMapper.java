package com.edu.sdu.mapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 活跃用户使用天数的mapper
 * @author 王宁
 *
 */
public class ActiveUserPlayDayMapper extends Mapper<LongWritable, Text, Text, Text> {

	Map<String, String> dedupMap = new HashMap<>();
	
	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String line = ivalue.toString();
		String val[] = line.split("\\s+");
		
		String app_key = val[0];
		String time = val[6];
		String user = val[2];
		
		if(!dedupMap.containsKey(app_key + " " + time + " " + user)){	// 目前没有重复的
			dedupMap.put(app_key + " " + time + " " + user, "");
			context.write(new Text(app_key), new Text(user));
		}
	}

}
