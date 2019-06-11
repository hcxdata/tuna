package com.hobin.crm.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

	public static String getCurrTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return sdf.format(date);
	}

	public static Timestamp getCurrTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	public static String getCurrTime(String farmat) {
		SimpleDateFormat sdf = new SimpleDateFormat(farmat);
		Date date = new Date();
		return sdf.format(date);
	}

	public static double getDays(long timestamp) {
		return (System.currentTimeMillis() - timestamp) / 86400000.0;
	}

	public static double getHour(long timestamp) {
		return (System.currentTimeMillis() - timestamp) / 3600000.0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(getCurrTime("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
	}

}
