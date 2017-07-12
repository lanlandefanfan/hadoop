package com.edu.sdu.reducer;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import com.sdu.edu.bean.PlayerDeviceDetailBean;

/**
 * 所有详情信息的reducer
 * @author 王宁
 *
 */
public class DetailReducer extends Reducer<PlayerDeviceDetailBean, Text, PlayerDeviceDetailBean, Text> {

	public void reduce(PlayerDeviceDetailBean _key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		// process values
		int size = 0;
		String time = null;
		for (Text val : values) {
			if(time == null)
				time = val.toString();
			size++;
		}
		
		context.write(_key, new Text(size + "\t" + time));
	}

}
