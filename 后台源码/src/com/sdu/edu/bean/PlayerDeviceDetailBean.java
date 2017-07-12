package com.sdu.edu.bean;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class PlayerDeviceDetailBean implements Writable, WritableComparable<PlayerDeviceDetailBean>{
	public String device;
	public String key;
	
	public PlayerDeviceDetailBean(String key, String device) {
		// TODO Auto-generated constructor stub
		this.device = device;
		this.key = key;
	}
	
	public PlayerDeviceDetailBean() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void readFields(DataInput arg0) throws IOException {
		// TODO Auto-generated method stub
		this.key = arg0.readUTF();
		this.device = arg0.readUTF();
	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		// TODO Auto-generated method stub
		arg0.writeUTF(this.key);
		arg0.writeUTF(this.device);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return key + "\t" + device;
	}

	@Override
	public int compareTo(PlayerDeviceDetailBean o) {
		// TODO Auto-generated method stub
		String key = o.key;
		String device = o.device;
		if(this.key.compareTo(key) < 0){
			return -1;
		}
		else if(this.key.compareTo(key) == 0){
			if(this.device.compareTo(device) < 0)
				return -1;
			else if (this.device.compareTo(device) == 0) {
				return 0;
			}
			else {
				return 1;
			}
		}
		else {
			return 1;
		}
	}
}
