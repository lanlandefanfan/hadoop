package com.edu.sdu.bean;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

/**
 * 地区实体
 * @author 李安修
 *
 */
public class AreaOrderBean implements WritableComparable<AreaOrderBean> {
	private String app_key;// appkey
	private String city;// 城市
	private String state;

	public AreaOrderBean() {
	}
	
	public AreaOrderBean(String app_key, String city, String state) {
		this.app_key = app_key;
		this.city = city;
		this.state = state;
	}

	public String getAppkey() {
		return app_key;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	
	public void readFields(DataInput in) throws IOException {
		this.app_key = in.readUTF();
		this.city = in.readUTF();
		this.state = in.readUTF();
	}

	public void write(DataOutput out) throws IOException {
		out.writeUTF(app_key);
		out.writeUTF(city);
		out.writeUTF(state);
	}

	public int compareTo(AreaOrderBean o) {
		int ret = this.app_key.compareTo(o.getAppkey());
		if (ret == 0) {
			ret = this.city.compareTo(o.getCity());
			if(ret == 0)
				ret = this.state.compareTo(o.getState());
		}
		return ret;
	}

	@Override
	public String toString() {
		return app_key + "\t" + city + "\t" + state;
	}
}
