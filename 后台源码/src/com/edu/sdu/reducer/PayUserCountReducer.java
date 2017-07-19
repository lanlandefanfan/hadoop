package com.edu.sdu.reducer;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.edu.sdu.bean.TimeValueBean;

/**
 * 支付用户人数的reducer
 * @author 王宁
 *
 */
public class PayUserCountReducer extends Reducer<Text, TimeValueBean, Text, TimeValueBean> {

	public void reduce(Text _key, Iterable<TimeValueBean> values, Context context) throws IOException, InterruptedException {
		int size = 0;
		String time = null;
		// process values
		for (TimeValueBean val : values) {
			if(time == null)
				time = val.getTime();
			
			if(Float.parseFloat(val.getNum()) > 0.00){
				size++;
			}
		}
		context.write(_key, new TimeValueBean(size + "", time));
	}

}
