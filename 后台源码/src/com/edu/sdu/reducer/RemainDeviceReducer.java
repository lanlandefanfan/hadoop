package com.edu.sdu.reducer;

import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.sdu.edu.bean.PlayerDeviceDetailBean;
import com.sdu.edu.bean.RemainOprBean;
import com.sun.javafx.collections.MappingChange.Map;

public class RemainDeviceReducer extends Reducer<Text, RemainOprBean, Text, Text> {

	java.util.Map<String, String> map = new HashMap<>();
	String app_key = "";

	public void reduce(Text _key, Iterable<RemainOprBean> values, Context context)
			throws IOException, InterruptedException {
		int size = 0;
		map.clear();
		// process values
		for (RemainOprBean val : values) {
			if (map.containsKey(val.device)) {
				if (!map.get(val.device).equals("#")) {
					if (map.get(val.device).equals("5") ^ val.state.equals("5")) {
						size++;
						map.put(val.device, "#");
					}
				}
			} else {
				map.put(val.device, val.state);
			}
		}
		context.write(_key, new Text(size + ""));
	}

}
