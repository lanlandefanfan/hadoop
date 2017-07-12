package com.edu.sdu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tool {

	/*
	 * 获取间隔天数
	 */
	public static int getSpaceDay(String startday, String endday) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		long between_days = 0l;
		try {
			Date start = format.parse(startday);
			Date end = format.parse(endday);
			Calendar cal = Calendar.getInstance();
			cal.setTime(start);
			long time1 = cal.getTimeInMillis();
			cal.setTime(end);
			long time2 = cal.getTimeInMillis();
			between_days = (time2 - time1) / (1000 * 3600 * 24);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.parseInt(String.valueOf(between_days));
	}
}
