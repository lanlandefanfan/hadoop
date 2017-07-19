package com.edu.sdu.reducer;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.edu.sdu.bean.TimeValueBean;

/**
 * 新用户首次使用时长统计的reducer
 * @author 王宁
 *
 */
public class NewUserReducer extends Reducer<Text, TimeValueBean, Text, TimeValueBean> {

	public void reduce(Text _key, Iterable<TimeValueBean> values, Context context) throws IOException, InterruptedException {
		// process values
		int one = 0;	int two = 0;
		int three = 0;	int four = 0;
		int five = 0;	int six = 0;
		int seven = 0;	int eight = 0;
		int nine = 0;
		String time = null;
		for (TimeValueBean val : values) {	
			int useTime = Integer.parseInt(val.getNum());
			if(time == null)
				time = val.getTime();
			if(useTime > 0 && useTime <= 4)
				one++;
			else if(useTime > 4 && useTime <= 10)
				two++;
			else if(useTime > 10 && useTime <= 30)
				three++;
			else if(useTime > 30 && useTime <= 60)
				four++;
			else if(useTime > 60 && useTime <= 180)
				five++;
			else if(useTime > 180 && useTime <= 600)
				six++;
			else if(useTime > 600 && useTime <= 1800)
				seven++;
			else if(useTime > 1800 && useTime <= 3600)
				eight++;
			else
				nine++;
		}
		context.write(new Text(_key), new TimeValueBean(one + "", time));
		context.write(new Text(_key), new TimeValueBean(two + "", time));
		context.write(new Text(_key), new TimeValueBean(three + "", time));
		context.write(new Text(_key), new TimeValueBean(four + "", time));
		context.write(new Text(_key), new TimeValueBean(five + "", time));
		context.write(new Text(_key), new TimeValueBean(six + "", time));
		context.write(new Text(_key), new TimeValueBean(seven + "", time));
		context.write(new Text(_key), new TimeValueBean(eight + "", time));
		context.write(new Text(_key), new TimeValueBean(nine + "", time));
	}

}
