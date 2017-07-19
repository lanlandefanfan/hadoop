package com.edu.sdu.reducer;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.edu.sdu.bean.TimeValueBean;

/**
 * 新设备首次使用时长的reducer
 * @author 杜仲楠
 *
 */
public class NewDeviceReducer extends Reducer<Text, TimeValueBean, Text, TimeValueBean> {
	@Override
	protected void reduce(Text key, Iterable<TimeValueBean> value,
			Reducer<Text, TimeValueBean, Text, TimeValueBean>.Context context)
			throws IOException, InterruptedException {

		context.write(key, null);

		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int e = 0;
		int f = 0;
		int g = 0;
		int h = 0;
		int i = 0;

		String datetime = null;
		for (TimeValueBean item : value) {
			if (datetime == null)
				datetime = item.getTime();
			int durtime = Integer.parseInt(item.getNum());
			if (0 < durtime && durtime <= 4) {
				a++;
			} else if (4 < durtime && durtime <= 10) {
				b++;
			} else if (10 < durtime && durtime <= 30) {
				c++;
			} else if (30 < durtime && durtime <= 60) {
				d++;
			} else if (60 < durtime && durtime <= 180) {
				e++;
			} else if (180 < durtime && durtime <= 600) {
				f++;
			} else if (600 < durtime && durtime <= 1800) {
				g++;
			} else if (1800 < durtime && durtime <= 3600) {
				h++;
			} else {
				i++;
			}
		}
		context.write(new Text("1~4s"), new TimeValueBean(a + "", datetime));
		context.write(new Text("5~10s"), new TimeValueBean(b + "", datetime));
		context.write(new Text("11~30s"), new TimeValueBean(c + "", datetime));
		context.write(new Text("31~60s"), new TimeValueBean(d + "", datetime));
		context.write(new Text("1~3min"), new TimeValueBean(e + "", datetime));
		context.write(new Text("4~10min"), new TimeValueBean(f + "", datetime));
		context.write(new Text("11~30min"), new TimeValueBean(g + "", datetime));
		context.write(new Text("31~60min"), new TimeValueBean(h + "", datetime));
		context.write(new Text(">1h"), new TimeValueBean(i + "", datetime));
	}
}
