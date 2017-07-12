package com.edu.sdu.reducer;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.sdu.edu.bean.TimeValueBean;

public class PayMoneyReducer extends Reducer<Text, TimeValueBean, Text, TimeValueBean> {

	public void reduce(Text _key, Iterable<TimeValueBean> values, Context context) throws IOException, InterruptedException {
		float money = 0.00f;
		String time = null;
		// process values
		for (TimeValueBean val : values) {
			if(time == null)
				time = val.getTime();
			
			double cost = Double.parseDouble(val.getNum());
			if(cost > 0.00){
				money += cost;
			}
		}
		context.write(_key, new TimeValueBean(money + "", time));
	}

}
