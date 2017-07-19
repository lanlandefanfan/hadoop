package com.edu.sdu.reducer;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

import com.edu.sdu.bean.TimeValueBean;

/**
 * 下载量统计的reducer
 * @author 王宁
 *
 */
public class DownloadCountReducer extends Reducer<Text, TimeValueBean, Text, TimeValueBean> {

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
