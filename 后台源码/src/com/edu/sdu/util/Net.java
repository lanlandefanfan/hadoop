package com.edu.sdu.util;

import java.io.File;
import java.nio.charset.Charset;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.math3.genetics.BinaryChromosome;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 网络请求类
 * @author 王宁
 *
 */
public class Net {

	/*
	 * 上传json文件类
	 */
	public static void upLoadJsonFile(String filePath, String fileName, String xx) {
		HttpClient httpClient = null;
		try {
			Configuration conf = new Configuration();
			FileSystem fs = FileSystem.get(conf);
			fs.copyToLocalFile(new Path(filePath), new Path(System.getProperty("user.dir") + "/" + fileName));

			httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://123.206.184.214/upload/" + xx);
			// 把文件转换成流对象FileBody
			FileBody bin = new FileBody(new File(System.getProperty("user.dir") + "/" + fileName));
			System.out.println(bin.getFilename());
			StringBody filename = new StringBody(fileName);

			MultipartEntity reqEntity = new MultipartEntity();
			reqEntity.addPart("file", bin);
			reqEntity.addPart("filename", filename);
			httpPost.setEntity(reqEntity);
			HttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				System.out.println("服务器正常响应");
				// 获取响应对象
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					// 打印响应长度
					System.out.println("Response content length: " + resEntity.getContentLength());
					// 打印响应内容
					System.out.println(EntityUtils.toString(resEntity, Charset.forName("UTF-8")));
				}
				// 销毁
				EntityUtils.consume(resEntity);
			} else {
				System.out.println("服务器未响应");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				File file = new File(System.getProperty("user.dir") + fileName);
				httpClient.getConnectionManager().shutdown();
				file.delete();
			} catch (Exception ignore) {
				ignore.printStackTrace();
			}
		}
	}

	/*
	 * 请求发送邮件类
	 */
	public static void sendMail(String id) {
		HttpClient httpClient = null;
		try {
			httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://123.206.184.214/mail/send");
			StringBody sendId = new StringBody(id);

			MultipartEntity reqEntity = new MultipartEntity();
			reqEntity.addPart("alert_ids", sendId);
			httpPost.setEntity(reqEntity);
			HttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				System.out.println("服务器正常响应");
				// 获取响应对象
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					// 打印响应长度
					System.out.println("Response content length: " + resEntity.getContentLength());
					// 打印响应内容
					System.out.println(EntityUtils.toString(resEntity, Charset.forName("UTF-8")));
				}
				// 销毁
				EntityUtils.consume(resEntity);
			} else {
				System.out.println("服务器未响应");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.getConnectionManager().shutdown();
			} catch (Exception ignore) {
				ignore.printStackTrace();
			}
		}
	}
}
