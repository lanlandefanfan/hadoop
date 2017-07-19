package com.edu.sdu.reducer;

import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

import com.edu.sdu.bean.RemainOprBean;

/**
 * 留存用户统计的reducer
 * @author 王宁
 *
 */
public class RemainUserReducer extends Reducer<Text, RemainOprBean, Text, Text> {

	java.util.Map<String, String> map = new HashMap<>();
	
	public void reduce(Text _key, Iterable<RemainOprBean> values, Context context) throws IOException, InterruptedException {
		int size = 0;
		// process values
		for (RemainOprBean val : values) {
			if (map.containsKey(val.device)) {
				if (!map.get(val.device).equals("#")) {
					if (map.get(val.device).equals("0") ^ val.state.equals("0")) {
						size++;
						map.put(val.device, "#");
					}
				}
			} else {
				map.put(val.device, val.state);
			}
		}
		context.write(_key, new Text(size+""));
	}

}