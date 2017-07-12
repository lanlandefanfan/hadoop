package com.edu.sdu.mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import com.sdu.edu.bean.RemainOprBean;
import com.sdu.edu.bean.Sysmbol;

public class RemainUserMapper extends Mapper<LongWritable, Text, Text, RemainOprBean> {

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		//获取map当前处理的输入文件
		String path = ((FileSplit)context.getInputSplit()).getPath().toString();
		
		String line = ivalue.toString();
		String val[] = line.split("\\s+");
		
		String app_key = val[0];
		String state = val[5];
		String user = val[2];
		
		if(path.indexOf(Sysmbol.endDay) > 0){
			if(!state.equals("0"))	// 后来使用的设备
				context.write(new Text(app_key), new RemainOprBean(user, state));
		}
		else {
			if(state.equals("0"))
				context.write(new Text(app_key), new RemainOprBean(user, state));
		}
	}

}
