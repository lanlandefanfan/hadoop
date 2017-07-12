package com.sdu.edu.bean;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class RemainOprBean implements Writable{

	public String device;
	public String state;
	
	public RemainOprBean(String device, String state) {
		// TODO Auto-generated constructor stub
		this.device = device;
		this.state = state;
	}
	
	public RemainOprBean() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void readFields(DataInput arg0) throws IOException {
		// TODO Auto-generated method stub
		this.device = arg0.readUTF();
		this.state = arg0.readUTF();
	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		// TODO Auto-generated method stub
		arg0.writeUTF(this.device);
		arg0.writeUTF(this.state);
	}
	
	public String getDevice() {
		return device;
	}

	public String getState() {
		return state;
	}
}
