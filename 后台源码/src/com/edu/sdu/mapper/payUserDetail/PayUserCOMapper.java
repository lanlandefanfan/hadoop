package com.edu.sdu.mapper.payUserDetail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

import com.edu.sdu.bean.PlayerDeviceDetailBean;

/**
 * 支付用户运营商统计的mapper
 * @author 王宁
 *
 */
public class PayUserCOMapper extends Mapper<LongWritable, Text, PlayerDeviceDetailBean, Text> {

	Map<String, String> map = new HashMap<>();
	
	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String line = ivalue.toString();
		String val[] = line.split("\\s+");
		
		String coType = val[13] + " " + val[14]; // 运营商
		String app_key = val[0];
		String time = val[6];
		String user = val[2];
		String state = val[5];

		if (state.equals("3")) { // 支付状态
			if (!map.containsKey(app_key + " " + user + " " + coType)) {
				map.put(app_key + " " + user + " " + coType, "");
				context.write(new PlayerDeviceDetailBean(app_key, coType), new Text(time));
			}
		}
	}
}
