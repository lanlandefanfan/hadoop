package com.edu.sdu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 工具类
 * @author 王宁
 *
 */
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

	/*
	 * 获取date这一天前days天的日期
	 */
	public static String getPreNdayDate(String date, int days) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String three_days_ago = null;
		try {
			Date cur = sdf1.parse(date);
			calendar.setTime(cur);
			calendar.add(Calendar.DATE, -days);
			three_days_ago = sdf1.format(calendar.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return three_days_ago;
	}
	
	/*
	 * 获取是否需要预警
	 */
	public static boolean getIsAlertOrNot(int predata, int curdata, int percent, int trigger) {
		int differ = Math.abs(curdata - predata);
		if(trigger == 0) {// 小于
			if(differ < (predata * percent / 100)){
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if(differ > (predata * percent / 100)){
				return true;
			}
			else {
				return false;
			}
		}
	}
}