package com.edu.sdu.runner;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class UploadTest {

	public static void main(String[] args) {
		HttpClient httpClient = null;
		try {
			httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://123.206.184.214/upload/ad");
			// 把文件转换成流对象FileBody
			FileBody bin = new FileBody(new File("/usr/local/hadoop/file/ActiveDeviceCI/2017-05-01_ad_ci.txt"));
			StringBody filename = new StringBody("2017-05-01_ad_ci.txt");
			
			MultipartEntity reqEntity = new MultipartEntity();
			reqEntity.addPart("file", bin);
			reqEntity.addPart("filename", filename);
			httpPost.setEntity(reqEntity);
			HttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode == HttpStatus.SC_OK){
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
			}
			else {
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
