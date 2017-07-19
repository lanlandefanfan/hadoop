package com.edu.sdu.util;

import java.io.File;
import java.nio.charset.Charset;
import java.sql.*;

import javax.annotation.Nonnull;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.hadoop.yarn.server.applicationhistoryservice.webapp.AppPage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.sun.jersey.core.util.StringIgnoreCaseKeyComparator;

/**
 * 所有的数据库操作类
 * @author 王宁
 *
 */
public class Database {
	private static Connection conn;
	private static Database datamain;

	public static Database getInstance() {
		if (datamain == null)
			datamain = new Database();
		return datamain;
	}

	// 连接数据库
	public Database() {
		try {
			String name1 = "root";
			String password1 = "687853";
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://123.206.184.214:3306/summer?useUnicode=true&characterEncoding=utf-8";
			conn = DriverManager.getConnection(url, name1, password1);
			if (conn != null)
				System.out.println("数据库连接成功！");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 更新app_critical_data表
	 */
	public boolean updateAppCriticalData(String app_key, String newUser, String newDevice, String activeUser,
			String activeDevice, String payCount, String payMoney, String datetime) {
		int i = 0;
		try {
			String str = "select * from app_critical_data where app_key='" + app_key + "' and datetime='" + datetime
					+ "'";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(str);

			while (rs.next()) {
				i++;
			}
			if (i > 0) { // 有这条数据,只用更新就行
				int length = 0;
				str = "update app_critical_data t set";
				if (!newUser.equals("")) {
					length++;
					str = str.concat(" t.new_user='" + newUser + "'");
				}
				if (!newDevice.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.new_device='" + newDevice + "'");
				}
				if (!activeUser.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.active_user='" + activeUser + "'");
				}
				if (!activeDevice.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.active_device='" + activeDevice + "'");
				}
				if (!payCount.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.pay_user_count='" + payCount + "'");
				}
				if (!payMoney.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.pay_money='" + payMoney + "'");
				}
				str = str.concat(" where t.app_key='" + app_key + "' and t.datetime='" + datetime + "'");
				// System.out.println(str);
				PreparedStatement update = conn.prepareStatement(str);
				update.execute();
				update.close();
			} else {
				str = "insert into app_critical_data(app_key, new_user, new_device, active_user, active_device, pay_user_count, pay_money, datetime)values('"
						+ app_key + "', '" + newUser + "', '" + newDevice + "', '" + activeUser + "', '" + activeDevice
						+ "', '" + payCount + "', '" + payMoney + "', '" + datetime + "')";
				state.executeUpdate(str);
				state.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/*
	 * 更新new_user表
	 */
	public boolean updateNewUserData(String app_key, String one_fours, String five_tens, String eleven_thirtys,
			String thirty_sixtys, String one_threem, String four_tenm, String eleven_thirtym, String thirty_sixtym,
			String sixtym_more, String datetime) {
		int i = 0;
		try {
			String str = "select * from new_user where app_key='" + app_key + "' and datetime='" + datetime + "'";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(str);

			while (rs.next()) {
				i++;
			}
			if (i > 0) { // 有这条数据,只用更新就行
				int length = 0;
				str = "update new_user set " + "1_4s='" + one_fours + "', " + "5_10s='" + five_tens + "', " + "11_30s='"
						+ eleven_thirtys + "', " + "31_60s='" + thirty_sixtys + "', " + "1_3m='" + one_threem + "', "
						+ "4_10m='" + four_tenm + "', " + "11_30m='" + eleven_thirtym + "', " + "31_60m='"
						+ thirty_sixtym + "', " + "60_m='" + sixtym_more + "'";
				str = str.concat(" where app_key='" + app_key + "' and datetime='" + datetime + "'");
				System.out.println(str);
				PreparedStatement update = conn.prepareStatement(str);
				update.execute();
				update.close();
			} else {
				str = "insert into new_user(app_key, 1_4s, 5_10s, 11_30s, 31_60s, 1_3m, 4_10m, 11_30m, 31_60m, 60_m, datetime)values('"
						+ app_key + "', '" + one_fours + "', '" + five_tens + "', '" + eleven_thirtys + "', '"
						+ thirty_sixtys + "', '" + one_threem + "', '" + four_tenm + "', '" + eleven_thirtym + "', '"
						+ thirty_sixtym + "', '" + sixtym_more + "', '" + datetime + "')";
				state.executeUpdate(str);
				state.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/*
	 * 更新new_device表
	 */
	public boolean updateNewDeviceData(String app_key, String one_fours, String five_tens, String eleven_thirtys,
			String thirty_sixtys, String one_threem, String four_tenm, String eleven_thirtym, String thirty_sixtym,
			String sixtym_more, String datetime) {
		int i = 0;
		try {
			String str = "select * from new_device where app_key='" + app_key + "' and datetime='" + datetime + "'";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(str);

			while (rs.next()) {
				i++;
			}
			if (i > 0) { // 有这条数据,只用更新就行
				int length = 0;
				str = "update new_device set " + "1_4s='" + one_fours + "', " + "5_10s='" + five_tens + "', "
						+ "11_30s='" + eleven_thirtys + "', " + "31_60s='" + thirty_sixtys + "', " + "1_3m='"
						+ one_threem + "', " + "4_10m='" + four_tenm + "', " + "11_30m='" + eleven_thirtym + "', "
						+ "31_60m='" + thirty_sixtym + "', " + "60_m='" + sixtym_more + "'";
				str = str.concat(" where app_key='" + app_key + "' and datetime='" + datetime + "'");
				System.out.println(str);
				PreparedStatement update = conn.prepareStatement(str);
				update.execute();
				update.close();
			} else {
				str = "insert into new_device(app_key, 1_4s, 5_10s, 11_30s, 31_60s, 1_3m, 4_10m, 11_30m, 31_60m, 60_m, datetime)values('"
						+ app_key + "', '" + one_fours + "', '" + five_tens + "', '" + eleven_thirtys + "', '"
						+ thirty_sixtys + "', '" + one_threem + "', '" + four_tenm + "', '" + eleven_thirtym + "', '"
						+ thirty_sixtym + "', '" + sixtym_more + "', '" + datetime + "')";
				state.executeUpdate(str);
				state.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/*
	 * 更新survive_device表
	 */
	public boolean updateRemainDevice(String app_key, String date, String today, String oneday, String twoday,
			String threeday, String fourday, String fiveday, String sixday, String sevenday, String fourteenday, String thirtyday) {
		int i = 0;
		try {
			String str = "select * from survive_device where app_key='" + app_key + "' and date='" + date + "'";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(str);

			while (rs.next()) {
				i++;
			}
			if (i > 0) { // 有这条数据,只用更新就行
				int length = 0;
				str = "update survive_device t set";
				if (!today.equals("")) {
					length++;
					str = str.concat(" t.today='" + today + "'");
				}
				if (!oneday.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.1day='" + oneday + "'");
				}
				if (!twoday.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.2day='" + twoday + "'");
				}
				if (!threeday.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.3day='" + threeday + "'");
				}
				if (!fourday.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.4day='" + fourday + "'");
				}
				if (!fiveday.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.5day='" + fiveday + "'");
				}
				if (!sixday.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.6day='" + sixday + "'");
				}
				if (!sevenday.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.7day='" + sevenday + "'");
				}
				if (!fourteenday.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.14day='" + fourteenday + "'");
				}
				if (!thirtyday.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.30day='" + thirtyday + "'");
				}
				str = str.concat(" where t.app_key='" + app_key + "' and t.date='" + date + "'");
				PreparedStatement update = conn.prepareStatement(str);
				update.execute();
				update.close();
			} else {
				str = "insert into survive_device(app_key, today, 1day, 2day, 3day, 4day, 5day, 6day, 7day, 14day, 30day, date)values('"
						+ app_key + "', '" + today + "', '" + oneday + "', '" + twoday + "', '" + threeday + "', '"
						+ fourday + "', '" + fiveday + "', '" + sixday + "', '" + sevenday + "', '" + fourteenday + "', '" + 
						thirtyday + "', '" + date + "')";
				state.executeUpdate(str);
				state.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/*
	 * 更新survive_user表
	 */
	public boolean updateRemainUser(String app_key, String date, String today, String oneday, String twoday,
			String threeday, String fourday, String fiveday, String sixday, String sevenday, String fourteenday, String thirtyday) {
		int i = 0;
		try {
			String str = "select * from survive_user where app_key='" + app_key + "' and date='" + date + "'";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(str);

			while (rs.next()) {
				i++;
			}
			if (i > 0) { // 有这条数据,只用更新就行
				int length = 0;
				str = "update survive_user t set";
				if (!today.equals("")) {
					length++;
					str = str.concat(" t.today='" + today + "'");
				}
				if (!oneday.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.1day='" + oneday + "'");
				}
				if (!twoday.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.2day='" + twoday + "'");
				}
				if (!threeday.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.3day='" + threeday + "'");
				}
				if (!fourday.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.4day='" + fourday + "'");
				}
				if (!fiveday.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.5day='" + fiveday + "'");
				}
				if (!sixday.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.6day='" + sixday + "'");
				}
				if (!sevenday.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.7day='" + sevenday + "'");
				}
				if (!fourteenday.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.14day='" + fourteenday + "'");
				}
				if (!thirtyday.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.30day='" + thirtyday + "'");
				}
				str = str.concat(" where t.app_key='" + app_key + "' and t.date='" + date + "'");
				PreparedStatement update = conn.prepareStatement(str);
				update.execute();
				update.close();
			} else {
				str = "insert into survive_user(app_key, today, 1day, 2day, 3day, 4day, 5day, 6day, 7day, 14day, 30day, date)values('"
						+ app_key + "', '" + today + "', '" + oneday + "', '" + twoday + "', '" + threeday + "', '"
						+ fourday + "', '" + fiveday + "', '" + sixday + "', '" + sevenday + "', '" + fourteenday + "', '" + 
						thirtyday + "', '" + date + "')";
				state.executeUpdate(str);
				state.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/*
	 * 更新area表
	 */
	public boolean updateArea(String app_key, String area, String newDevCount, String newUserCount, String date) {
		int i = 0;
		try {
			String str = "select * from area where app_key='" + app_key + "' and date='" + date + "' and area='" + area
					+ "'";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(str);

			while (rs.next()) {
				i++;
			}
			if (i > 0) { // 有这条数据,只用更新就行
				str = "update area set " + "new_dev_count='" + newDevCount + "', " + "new_user_count='" + newUserCount
						+ "'";
				str = str.concat(" where app_key='" + app_key + "' and date='" + date + "' and area='" + area + "'");
				// System.out.println(str);
				PreparedStatement update = conn.prepareStatement(str);
				update.execute();
				update.close();
			} else {
				str = "insert into area(app_key, area, new_dev_count, new_user_count, date)values('" + app_key + "', '"
						+ area + "', '" + newDevCount + "', '" + newUserCount + "', '" + date + "')";
				state.executeUpdate(str);
				state.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/*
	 * 更新active_device表
	 */
	public boolean updateActiveDevice(String app_key, String one, String twoToThree, String fourToSeven,
			String eightToFourteen, String fifteenToThirty, String ThirtyOneToNinty) {
		int i = 0;
		try {
			String str = "select * from active_device where app_key='" + app_key + "'";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(str);

			while (rs.next()) {
				i++;
			}
			if (i > 0) { // 有这条数据,只用更新就行
				str = "update active_device set " + "1d='" + one + "', " + "2_3d='" + twoToThree + "', " + "4_7d='"
						+ fourToSeven + "', " + "8_14d='" + eightToFourteen + "', " + "15_30d='" + fifteenToThirty
						+ "', " + "31_90d='" + ThirtyOneToNinty + "'";
				str = str.concat(" where app_key='" + app_key + "'");
				// System.out.println(str);
				PreparedStatement update = conn.prepareStatement(str);
				update.execute();
				update.close();
			} else {
				str = "insert into active_device(app_key, 1d, 2_3d, 4_7d, 8_14d, 15_30d, 31_90d)values('" + app_key
						+ "', '" + one + "', '" + twoToThree + "', '" + fourToSeven + "', '" + eightToFourteen + "', '"
						+ fifteenToThirty + "', '" + ThirtyOneToNinty + "')";
				state.executeUpdate(str);
				state.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/*
	 * 更新active_user表
	 */
	public boolean updateActiveUser(String app_key, String one, String twoToThree, String fourToSeven,
			String eightToFourteen, String fifteenToThirty, String ThirtyOneToNinty) {
		int i = 0;
		try {
			String str = "select * from active_user where app_key='" + app_key + "'";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(str);

			while (rs.next()) {
				i++;
			}
			if (i > 0) { // 有这条数据,只用更新就行
				str = "update active_user set " + "1d='" + one + "', " + "2_3d='" + twoToThree + "', " + "4_7d='"
						+ fourToSeven + "', " + "8_14d='" + eightToFourteen + "', " + "15_30d='" + fifteenToThirty
						+ "', " + "31_90d='" + ThirtyOneToNinty + "'";
				str = str.concat(" where app_key='" + app_key + "'");
				// System.out.println(str);
				PreparedStatement update = conn.prepareStatement(str);
				update.execute();
				update.close();
			} else {
				str = "insert into active_user(app_key, 1d, 2_3d, 4_7d, 8_14d, 15_30d, 31_90d)values('" + app_key
						+ "', '" + one + "', '" + twoToThree + "', '" + fourToSeven + "', '" + eightToFourteen + "', '"
						+ fifteenToThirty + "', '" + ThirtyOneToNinty + "')";
				state.executeUpdate(str);
				state.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/*
	 * 更新pay表
	 */
	public boolean updatePayUser(String app_key, String user_totle_count, String money, String datetime) {
		int i = 0;
		try {
			String str = "select * from pay where app_key='" + app_key + "' and datetime='" + datetime + "'";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(str);

			while (rs.next()) {
				i++;
			}
			if (i > 0) { // 有这条数据,只用更新就行
				int length = 0;
				str = "update pay t set";
				if (!user_totle_count.equals("")) {
					length++;
					str = str.concat(" t.user_totle_count='" + user_totle_count + "'");
				}
				if (!money.equals("")) {
					if (length > 0)
						str = str.concat(",");
					length++;
					str = str.concat(" t.money='" + money + "'");
				}
				str = str.concat(" where t.app_key='" + app_key + "' and t.datetime='" + datetime + "'");
				
				PreparedStatement update = conn.prepareStatement(str);
				update.execute();
				update.close();
			} else {
				str = "insert into pay(app_key, user_totle_count, money, datetime)values('" + app_key
						+ "', '" + user_totle_count + "', '" + money + "', '" + datetime + "')";
				state.executeUpdate(str);
				state.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/*
	 * 获取预警信息
	 */
	public String[] getAlertData(String app_key, String item) {
		String str = "select * from alert where app_key='" + app_key + "' and item='" + item + "'";
		String limitdata[] = new String[4];
		Statement state;
		try {
			state = conn.createStatement();
			ResultSet rs = state.executeQuery(str);
			if(rs.next()){
				limitdata[0] = rs.getInt(1) + "";
				limitdata[1] = rs.getInt(4) + "";
				limitdata[2] = rs.getString(6);
				limitdata[3] = rs.getInt(7) + "";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return limitdata;
	}
	
	/*
	 * 获取APP关键数据
	 */
	public String[] getAppCriticalData(String app_key, String date) {
		String str = "select * from app_critical_data where app_key='" + app_key + "' and datetime='" + date + "'";
		String criticaldata[] = new String[4];
		Statement state;
		try {
			state = conn.createStatement();
			ResultSet rs = state.executeQuery(str);
			if(rs.next()){
				criticaldata[0] = rs.getString(5); // 新增用户
				criticaldata[1] = rs.getString(6); // 新增设备
				criticaldata[2] = rs.getString(7); // 活跃用户
				criticaldata[3] = rs.getString(8); // 活跃设备
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return criticaldata;
	}
	
	/*
	 * 更新download表
	 */
	public boolean updateDownload(String app_key, String count, String date) {
		int i = 0;
		try {
			String str = "select * from download where app_key='" + app_key + "' and date='" + date + "'";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(str);

			while (rs.next()) {
				i++;
			}
			if (i > 0) { // 有这条数据,只用更新就行
				str = "update download set " + "count='" + count + "'";
				str = str.concat(" where app_key='" + app_key + "' and date='" + date + "'");
				// System.out.println(str);
				PreparedStatement update = conn.prepareStatement(str);
				update.execute();
				update.close();
			} else {
				str = "insert into download(app_key, count, date)values('" + app_key + "', '"
						+ count + "', '" + date + "')";
				state.executeUpdate(str);
				state.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/*
	 * 获取下载量
	 */
	public String getDownloadCount(String app_key, String date) {
		String str = "select * from download where app_key='" + app_key + "' and date='" + date + "'";
		String count = null;
		Statement state;
		try {
			state = conn.createStatement();
			ResultSet rs = state.executeQuery(str);
			if(rs.next()){
				count = rs.getString(3); // 下载数
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
}
