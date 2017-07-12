package com.edu.sdu.reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ActiveUserPlayDayReducer extends Reducer<Text, Text, Text, Text> {

	public void reduce(Text _key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		int count1 = 0;
		int count2_3 = 0;
		int count4_7 = 0;
		int count8_14 = 0;
		int count15_30 = 0;
		int count31_90 = 0;
		// process values
		Map<String, Integer> keyDevice = new HashMap<>();
		for (Text val : values) {
			String device = val.toString();
			if(keyDevice.containsKey(device)){
				int value = keyDevice.get(device);
				keyDevice.remove(device);
				value++;
				keyDevice.put(device, new Integer(value));
				if(value == 2)
					count2_3++;
				else if(value == 4)
					count4_7++;
				else if(value == 8)
					count8_14++;
				else if(value == 15)
					count15_30++;
				else if(value == 31)
					count31_90++;
			}
			else {
				//System.out.println(0);
				keyDevice.put(device, new Integer(1));
				count1++;
			}
		}
		context.write(_key, new Text("1" + "\t" + count1));
		context.write(_key, new Text("2_3" + "\t" + count2_3));
		context.write(_key, new Text("4_7" + "\t" + count4_7));
		context.write(_key, new Text("8_14" + "\t" + count8_14));
		context.write(_key, new Text("15_30" + "\t" + count15_30));
		context.write(_key, new Text("31_90" + "\t" + count31_90));
	}

}
