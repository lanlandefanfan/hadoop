package com.edu.sdu.bean;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

/**
 * 时间与数量的bean
 * @author 王宁
 *
 */
public class TimeValueBean implements Writable {
	String time;
	String value;
	
	public TimeValueBean() {
		// TODO Auto-generated constructor stub
	}
	
	public TimeValueBean(String value, String time) {
		// TODO Auto-generated constructor stub
		this.time = time;
		this.value = value;
	}
	
	public String getNum() {
		return value;
	}
	
	public void setNum(String value) {
		this.value = value;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public void readFields(DataInput arg0) throws IOException {
		// TODO Auto-generated method stub
		this.time = arg0.readUTF();
		this.value = arg0.readUTF();
	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		// TODO Auto-generated method stub
		arg0.writeUTF(this.time);
		arg0.writeUTF(this.value);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return value + "\t" + time;
	}
}
