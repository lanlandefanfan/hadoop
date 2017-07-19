package com.edu.sdu.mapper;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.edu.sdu.bean.TimeValueBean;

/**
 * 每天每个app的用户支付人物以及支付总金额的mapper
 * @author 王宁
 *
 */
public class PayUserMapper extends Mapper<LongWritable, Text, Text, TimeValueBean> {

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String line = ivalue.toString();
		String val[] = line.split("\\s+");
		
		String app_key = val[0];
		String time = val[6];
		String cost = val[10];
		
		context.write(new Text(app_key), new TimeValueBean(cost, time));
	}

}
