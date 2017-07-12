package com.edu.sdu.mapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.edu.sdu.runner.DailyNewUser;
import com.sdu.edu.bean.TimeValueBean;

public class DailyNewUserMapper extends Mapper<LongWritable, Text, Text, TimeValueBean> {

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String line = ivalue.toString();
		String[] val = line.split("\\s+");
		
		String app_key = val[0];
		String behavior = val[5];
		String userId = val[2];	// 用户id
		String time = val[6];
		
		if(behavior.equals("0"))
			context.write(new Text(app_key), new TimeValueBean(userId, time));	// appkey, userid
	}
}
