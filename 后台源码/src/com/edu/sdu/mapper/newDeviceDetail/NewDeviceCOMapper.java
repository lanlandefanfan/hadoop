package com.edu.sdu.mapper.newDeviceDetail;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

import com.sdu.edu.bean.PlayerDeviceDetailBean;

public class NewDeviceCOMapper extends Mapper<LongWritable, Text, PlayerDeviceDetailBean, Text> {

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String line = ivalue.toString();
		String val[] = line.split("\\s+");
		
		String coType = val[13] + " " + val[14]; // 运营商
		String app_key = val[0];
		String state = val[5];
		String time = val[6];
		
		if(state.equals("5"))
			context.write(new PlayerDeviceDetailBean(app_key, coType), new Text(time));
	}
}
