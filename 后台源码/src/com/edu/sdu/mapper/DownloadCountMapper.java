package com.edu.sdu.mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

import com.edu.sdu.bean.TimeValueBean;

/**
 * 下载量统计的mapper
 * @author 王宁
 *
 */
public class DownloadCountMapper extends Mapper<LongWritable, Text, Text, TimeValueBean> {

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String line = ivalue.toString();
		String[] val = line.split("\\s+");
		
		String app_key = val[0];
		String state = val[5];
		String time = val[6];
		
		if(state.equals("6"))
			context.write(new Text(app_key), new TimeValueBean(state, time));
	}
}
