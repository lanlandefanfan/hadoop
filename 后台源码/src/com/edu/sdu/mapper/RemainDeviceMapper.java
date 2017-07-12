package com.edu.sdu.mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import com.sdu.edu.bean.RemainOprBean;
import com.sdu.edu.bean.Sysmbol;
import com.sdu.edu.bean.PlayerDeviceDetailBean;
import com.sun.jersey.core.util.StringIgnoreCaseKeyComparator;

/**
 * 留存设备数的mapper
 * @author 王宁
 *
 */
public class RemainDeviceMapper extends Mapper<LongWritable, Text, Text, RemainOprBean> {

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String path = ((FileSplit)context.getInputSplit()).getPath().toString();
		
		String line = ivalue.toString();
		String val[] = line.split("\\s+");
		
		String app_key = val[0];
		String state = val[5];
		String device = val[3];
		
		if(path.indexOf(Sysmbol.endDay) > 0){
			if(!state.equals("5"))	// 后来使用的设备
				context.write(new Text(app_key), new RemainOprBean(device, state));
		}
		else {
			if(state.equals("5"))
				context.write(new Text(app_key), new RemainOprBean(device, state));
		}
	}

}
