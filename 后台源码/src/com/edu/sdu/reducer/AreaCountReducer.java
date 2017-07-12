package com.edu.sdu.reducer;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import com.sdu.edu.bean.AreaOrderBean;

public class AreaCountReducer extends Reducer<AreaOrderBean, Text, AreaOrderBean, Text> {

	@Override
	protected void reduce(AreaOrderBean key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		String time = null;
		for (Text val : values) {
			time = val.toString();
			sum++;
		}
		context.write(key, new Text(sum + "\t" + time));
	}
}