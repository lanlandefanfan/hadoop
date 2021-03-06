package com.edu.sdu.mapper.activeDeviceDetail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

import com.edu.sdu.bean.PlayerDeviceDetailBean;

/**
 * 活跃设备设备型号统计的mapper
 * @author 王宁
 *
 */
public class ActiveDevicePTMapper extends Mapper<LongWritable, Text, PlayerDeviceDetailBean, Text> {

	Map<String, String> map = new HashMap<>();
	
	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String line = ivalue.toString();
		String val[] = line.split("\\s+");
		
		String deviceType = val[12]; // 设备型号
		String app_key = val[0];
		String time = val[6];
		String device = val[3];
		
		if (!map.containsKey(app_key + " " + device + " " + deviceType)) {
			map.put(app_key + " " + device + " " + deviceType, "");
			context.write(new PlayerDeviceDetailBean(app_key, deviceType), new Text(time));
		}
	}

}
