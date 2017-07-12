package com.edu.sdu.mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 去重操作的mapper
 * @author 王宁
 *
 */
public class DedupMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String line = ivalue.toString();
		String[] val = line.split("\\s+");
		System.out.println(val[0]);
		if(val[0] != null && val[0].length() > 0)
			context.write(new Text(val[0]), new Text(""));
	}

}
