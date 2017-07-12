package com.edu.sdu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.google.gson.*;

public class WriteJson {
	
	public static void doWriteFile(String inputPath, String outputPath) throws Exception {

		/**
		 * 输入文件、输出文件路径，根据情况改变路径
		 * */
		Configuration conf = new Configuration();
		FileSystem fs0 = FileSystem.get(conf);
		FSDataInputStream fdis = fs0.open(new Path(inputPath));
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fdis));
		String line = null;
		JsonObject jsonObject = null;
		JsonObject app_jsonObject;
		JsonArray jsonArray = new JsonArray();
		JsonArray app_jsonArray = new JsonArray();
		

		/**
		 * 获取文件第一个APPKEY
		 */
		String[] filesFirst = bufferedReader.readLine().split("\t");
		String firstline = null;
		String app_keyfirst = filesFirst[0];
		
		String date = filesFirst[3];
		
		/**
		 * 获取一个APPKEY下面所拥有的value数量
		 * */
		int num = 1;
		while ((firstline = bufferedReader.readLine()) != null) {
			String[] newfilesFirst = firstline.split("\t");
			String m_app_key = newfilesFirst[0];
			if (!app_keyfirst.equals(m_app_key)) {
				break;
			}
			num++;
		}

		String app_key = null;
		int count = 0;
		
		/**
		 * 循环读取文件，构建JSON
		 * */
		while ((line = bufferedReader.readLine()) != null) {

			String[] files = line.split("\t");

			app_key = files[0];

			jsonObject = new JsonObject();
			jsonObject.addProperty("value", files[1]);
			jsonObject.addProperty("count", files[2]);
			jsonArray.add(jsonObject);

			if (count == (num - 1) && app_key.equals(app_keyfirst)) {
				app_jsonObject = new JsonObject();
				app_jsonObject.addProperty("app_key", app_key);
				app_jsonObject.add("data", jsonArray);
				app_jsonArray.add(app_jsonObject);
				jsonArray = new JsonArray();
				count = 0;
			}

			if (count == num && !app_key.equals(app_keyfirst)) {
				app_jsonObject = new JsonObject();
				app_jsonObject.addProperty("app_key", app_key);
				app_jsonObject.add("data", jsonArray);
				app_jsonArray.add(app_jsonObject);
				jsonArray = new JsonArray();
				count = 0;
			}

			count++;

		}

		JsonObject finalJSON = new JsonObject();
		finalJSON.addProperty("date", date);
		finalJSON.add("data", app_jsonArray);
		System.out.println(finalJSON);

		writeFile(outputPath, finalJSON.toString());

	}

	/**
	 * 将JSON格式信息写入文件（需要将JSON信息转化成string类型）
	 */
	public static void writeFile(String filePath, String sets) throws IOException {
		Configuration conf = new Configuration();
		FileSystem fs0 = FileSystem.get(conf);
		FSDataOutputStream fdos = fs0.create(new Path(filePath));
		byte[] readBuf = sets.getBytes("UTF-8");
		fdos.write(readBuf, 0, readBuf.length);
		fdos.close();
		fs0.close();
	}

}
