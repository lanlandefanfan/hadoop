package com.edu.sdu.runner.payDeviceDetail;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.edu.sdu.mapper.payDeviceDetail.PayDeviceCOMapper;
import com.edu.sdu.mapper.payDeviceDetail.PayDeviceOSMapper;
import com.edu.sdu.reducer.DetailReducer;
import com.edu.sdu.util.Net;
import com.edu.sdu.util.WriteJson;
import com.sdu.edu.bean.PlayerDeviceDetailBean;
import com.sdu.edu.bean.Sysmbol;

public class PayDeviceOS {

	public static void main(String[] args) {
		Sysmbol.startDay = args[0];
		Sysmbol.endDay = args[1];
		try {
			Configuration conf = new Configuration();
			Job job = Job.getInstance(conf, "PayDeviceOS");
			job.setJarByClass(PayDeviceOS.class);
			job.setMapperClass(PayDeviceOSMapper.class);
			job.setReducerClass(DetailReducer.class);
			
			job.setMapOutputKeyClass(PlayerDeviceDetailBean.class);// map阶段的输出的key
			job.setMapOutputValueClass(Text.class);// map阶段的输出的value
			job.setOutputKeyClass(PlayerDeviceDetailBean.class);
			job.setOutputValueClass(Text.class);
			FileInputFormat.addInputPath(job, new Path(args[2]));
			
			FileSystem fs2 = FileSystem.get(conf);
			Path op2 = new Path(args[3]);
			if (fs2.exists(op2)) {
				fs2.delete(op2, true);
				System.out.println("存在此输出路径，已删除！！！");
			}
			FileOutputFormat.setOutputPath(job, op2);
			job.waitForCompletion(true);
			
			String fileName = Sysmbol.startDay.substring(0, Sysmbol.startDay.length() - 4) + 
					"_pd_os.txt";
			String filePath = args[3] + "/" + fileName;
			WriteJson.doWriteFile(args[3] + "/part-r-00000", filePath);
			
			Net.upLoadJsonFile(filePath, fileName, "pd");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
