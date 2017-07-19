package com.edu.sdu.reducer;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.edu.sdu.bean.TimeValueBean;

/**
 * 日新增设备统计的reducer
 * @author 王宁
 *
 */
public class DailyNewDeviceReducer extends Reducer<Text, TimeValueBean, Text, TimeValueBean> {

	public void reduce(Text _key, Iterable<TimeValueBean> values, Context context) throws IOException, InterruptedException {
		// process values
		int size = 0;
		String time = null;
		for (TimeValueBean val : values) {
			if(time == null){
				time = val.getTime();
			}
			size++;
		}
		
		context.write(_key, new TimeValueBean(size + "", time));
	}

}
