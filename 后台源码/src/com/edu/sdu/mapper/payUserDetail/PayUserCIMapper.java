package com.edu.sdu.mapper.payUserDetail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.sdu.edu.bean.PlayerDeviceDetailBean;

public class PayUserCIMapper extends Mapper<LongWritable, Text, PlayerDeviceDetailBean, Text> {

	Map<String, String> map = new HashMap<>();

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String line = ivalue.toString();
		String val[] = line.split("\\s+");

		String ciType = val[16]; // 联网方式
		String app_key = val[0];
		String time = val[6];
		String user = val[2];
		String state = val[5];

		if (state.equals("3")) { // 支付状态
			if (!map.containsKey(app_key + " " + user + " " + ciType)) {
				map.put(app_key + " " + user + " " + ciType, "");
				context.write(new PlayerDeviceDetailBean(app_key, ciType), new Text(time));
			}
		}
	}
}
