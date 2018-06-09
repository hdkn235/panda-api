package com.hd.api.utils.common;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author Hoda
 * 
 * @since 3.0.0
 */
public final class DateUtil {

	private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private DateUtil() {
	}

	/**
	 * 获取当前日期与时间
	 */
	public static String getCurrentDateTime() {
		return dateTimeFormat.format(new Date());
	}

	/**
	 * 将字符串格式化成日期格式 传入字符串格式为 "yyyy-MM-DD hh:mm:ss" 或 "yyyy-MM-DD" parse a string
	 * "yyyy-MM-DD hh:mm:ss" to date parse a string "yyyy-MM-DD" to date
	 * ("00:00:00")
	 */
	public static Date parseToDate(String value) {
		Calendar calendar = Calendar.getInstance();
		String syear = null, smonth = null, sday = null, shour = null, sminute = null, ssecond = null;
		int iStart = 0, iEnd = 0;

		if (value == null || value.length() < 8)
			return null;

		if (value.indexOf("年") > 0 && value.indexOf("月") > 0 && value.indexOf("日") > 0) {
			iStart = 0;
			iEnd = value.indexOf("年");
			if (iStart >= 0 && iEnd > 0)
				syear = value.substring(iStart, iEnd);
			else
				return null;
			calendar.set(Calendar.YEAR, CastUtil.castInt(syear));

			iStart = iEnd + "年".length();
			iEnd = value.indexOf("月", iStart);
			if (iStart > 0 && iEnd > 0)
				smonth = value.substring(iStart, iEnd);
			else
				return null;
			calendar.set(Calendar.MONTH, CastUtil.castInt(smonth) - 1);

			iStart = iEnd + "月".length();
			iEnd = value.indexOf("日", iStart);
			if (iStart > 0 && iEnd > 0)
				sday = value.substring(iStart, iEnd);
			else
				return null;
			calendar.set(Calendar.DAY_OF_MONTH, CastUtil.castInt(sday));
		} else {
			if (value.length() >= 4)
				syear = value.substring(0, 4);
			else
				return null;
			calendar.set(Calendar.YEAR, CastUtil.castInt(syear));
			if (value.length() >= 7)
				smonth = value.substring(5, 7);
			else
				return null;
			calendar.set(Calendar.MONTH, CastUtil.castInt(smonth) - 1);
			if (value.length() >= 10)
				sday = value.substring(8, 10);
			else
				return null;
			calendar.set(Calendar.DAY_OF_MONTH, CastUtil.castInt(sday));
		}
		if (value.indexOf("时") > 0 && value.indexOf("分") > 0 && value.indexOf("秒") > 0) {
			iStart = value.indexOf(" ", iEnd + "日".length());
			iEnd = value.indexOf("时", iStart);
			if (iStart > 0 && iEnd > 0)
				shour = value.substring(iStart, iEnd);
			else
				return null;
			calendar.set(Calendar.HOUR_OF_DAY, CastUtil.castInt(shour));

			iStart = iEnd + "时".length();
			iEnd = value.indexOf("分", iStart);
			if (iStart > 0 && iEnd > 0)
				sminute = value.substring(iStart, iEnd);
			else
				return null;
			calendar.set(Calendar.MINUTE, CastUtil.castInt(sminute));

			iStart = iEnd + "分".length();
			iEnd = value.indexOf("秒", iStart);
			if (iStart > 0 && iEnd > 0)
				ssecond = value.substring(iStart, iEnd);
			else
				return null;
			calendar.set(Calendar.SECOND, CastUtil.castInt(ssecond));
		} else {
			if (value.length() >= 13) {
				shour = value.substring(11, 13);
				calendar.set(Calendar.HOUR_OF_DAY, CastUtil.castInt(shour));
			}
			if (value.length() >= 16) {
				sminute = value.substring(14, 16);
				calendar.set(Calendar.MINUTE, CastUtil.castInt(sminute));
			}
			if (value.length() >= 19) {
				ssecond = value.substring(17, 19);
				calendar.set(Calendar.SECOND, CastUtil.castInt(ssecond));
			}
		}

		Date date = calendar.getTime();
		return date;
	}

	/**
	 * 将日期格式化成 "yyyy-MM-DD hh:mm:ss" 字符串 parse a date to string with format
	 * "yyyy-MM-DD hh:mm:ss"
	 */
	public static String parseToDatetimeString(Date date) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int iYEAR = calendar.get(Calendar.YEAR);
		int iMONTH = calendar.get(Calendar.MONTH);
		int iDAY = calendar.get(Calendar.DAY_OF_MONTH);
		int iHOUR = calendar.get(Calendar.HOUR_OF_DAY);
		int iMINUTE = calendar.get(Calendar.MINUTE);
		int iSECOND = calendar.get(Calendar.SECOND);
		String year = standardValue(iYEAR, Calendar.YEAR);
		String month = standardValue(iMONTH, Calendar.MONTH);
		String day = standardValue(iDAY, Calendar.DAY_OF_MONTH);
		String hour = standardValue(iHOUR, Calendar.HOUR_OF_DAY);
		String minute = standardValue(iMINUTE, Calendar.MINUTE);
		String second = standardValue(iSECOND, Calendar.SECOND);

		String sDate = year + "-" + month + "-" + day;
		sDate += " " + hour + ":" + minute + ":" + second;
		return sDate;
	}

	/**
	 * 将日期格式化 "yyyy年MM月DD日 星期" parse a date to string with format
	 */
	public static String parseToDateweek(Date date) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int iYEAR = calendar.get(Calendar.YEAR);
		int iMONTH = calendar.get(Calendar.MONTH);
		int iDAY = calendar.get(Calendar.DAY_OF_MONTH);
		int iWEEK = calendar.get(Calendar.DAY_OF_WEEK);
		// int iHOUR = calendar.get(Calendar.HOUR_OF_DAY);
		// int iMINUTE = calendar.get(Calendar.MINUTE);
		// int iSECOND = calendar.get(Calendar.SECOND);
		String year = standardValue(iYEAR, Calendar.YEAR);
		String month = standardValue(iMONTH, Calendar.MONTH);
		String day = standardValue(iDAY, Calendar.DAY_OF_MONTH);
		// String hour = standardValue(iHOUR, Calendar.HOUR_OF_DAY);
		// String minute = standardValue(iMINUTE, Calendar.MINUTE);
		// String second = standardValue(iSECOND, Calendar.SECOND);

		String sDate = year + "年" + month + "月" + day + "日";
		if (iWEEK == Calendar.SUNDAY)
			sDate += "周日";
		else if (iWEEK == Calendar.MONDAY)
			sDate += "周一";
		else if (iWEEK == Calendar.TUESDAY)
			sDate += "周二";
		else if (iWEEK == Calendar.WEDNESDAY)
			sDate += "周三";
		else if (iWEEK == Calendar.THURSDAY)
			sDate += "周四";
		else if (iWEEK == Calendar.FRIDAY)
			sDate += "周五";
		else if (iWEEK == Calendar.SATURDAY)
			sDate += "周六";
		return sDate;
	}

	/**
	 * 将日期格式化成 "yyyy-MM-DD hh:mm:ss.lll" 字符串 parse a date to string with format
	 * "yyyy-MM-DD hh:mm:ss.lll"
	 */
	public static String datetimeallToString(Date date) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int iYEAR = calendar.get(Calendar.YEAR);
		int iMONTH = calendar.get(Calendar.MONTH);
		int iDAY = calendar.get(Calendar.DAY_OF_MONTH);
		int iHOUR = calendar.get(Calendar.HOUR_OF_DAY);
		int iMINUTE = calendar.get(Calendar.MINUTE);
		int iSECOND = calendar.get(Calendar.SECOND);
		// int iMILLISECOND = calendar.get(Calendar.MILLISECOND);
		String year = standardValue(iYEAR, Calendar.YEAR);
		String month = standardValue(iMONTH, Calendar.MONTH);
		String day = standardValue(iDAY, Calendar.DAY_OF_MONTH);
		String hour = standardValue(iHOUR, Calendar.HOUR_OF_DAY);
		String minute = standardValue(iMINUTE, Calendar.MINUTE);
		String second = standardValue(iSECOND, Calendar.SECOND);
		String millisecond = "" + Calendar.MILLISECOND;

		String sDate = year + "-" + month + "-" + day;
		sDate += " " + hour + ":" + minute + ":" + second + "." + millisecond;
		return sDate;
	}

	/**
	 * standard the time value, make single digital to double digital with "0",
	 * finally to String.
	 */
	private static String standardValue(int fieldValue, int fieldType) {
		String value = null;
		if (fieldType == Calendar.YEAR) {
			if (fieldValue > 1900 && fieldValue < 2099) {
				value = String.valueOf(fieldValue);
			} else {
				value = "1970";
			}
		} else if (fieldType == Calendar.MONTH) {
			if (fieldValue >= 0 && fieldValue < 9) {
				value = "0" + String.valueOf(fieldValue + 1);
			} else if (fieldValue >= 9 && fieldValue < 12) {
				value = String.valueOf(fieldValue + 1);
			} else {
				value = "01";
			}
		} else if (fieldType == Calendar.DAY_OF_MONTH) {
			if (fieldValue > 0 && fieldValue < 10) {
				value = "0" + String.valueOf(fieldValue);
			} else if (fieldValue >= 10 && fieldValue < 32) {
				value = String.valueOf(fieldValue);
			} else {
				value = "01";
			}
		} else if (fieldType == Calendar.HOUR_OF_DAY) {
			if (fieldValue >= 0 && fieldValue < 10) {
				value = "0" + String.valueOf(fieldValue);
			} else if (fieldValue >= 10 && fieldValue < 25) {
				value = String.valueOf(fieldValue);
			} else {
				value = "00";
			}
		} else if (fieldType == Calendar.MINUTE) {
			if (fieldValue >= 0 && fieldValue < 10) {
				value = "0" + String.valueOf(fieldValue);
			} else if (fieldValue >= 10 && fieldValue < 61) {
				value = String.valueOf(fieldValue);
			} else {
				value = "00";
			}
		} else if (fieldType == Calendar.SECOND) {
			if (fieldValue >= 0 && fieldValue < 10) {
				value = "0" + String.valueOf(fieldValue);
			} else if (fieldValue >= 10 && fieldValue < 61) {
				value = String.valueOf(fieldValue);
			} else {
				value = "00";
			}
		} else {
		}
		return value;
	}

	/**
	 * 返回格式化时间字符串(yyyy-MM-DD) parse a date to string with format "yyyy-MM-DD"
	 */
	public static String parseToString(Date date) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int iYEAR = calendar.get(Calendar.YEAR);
		int iMONTH = calendar.get(Calendar.MONTH);
		int iDAY = calendar.get(Calendar.DAY_OF_MONTH);
		String year = standardValue(iYEAR, Calendar.YEAR);
		String month = standardValue(iMONTH, Calendar.MONTH);
		String day = standardValue(iDAY, Calendar.DAY_OF_MONTH);
		String sDate = year + "-" + month + "-" + day;
		return sDate;
	}

	/**
	 * 时间转换成时间戳
	 * 
	 * @param date
	 * @return
	 */
	public static long toTimeStamp(Date date) {
		return date.getTime() / 1000;
	}

	/**
	 * 返回当前时间的月
	 */
	public static String parseToMonth(Date date) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int iYEAR = calendar.get(Calendar.YEAR);
		int iMONTH = calendar.get(Calendar.MONTH);
		String year = standardValue(iYEAR, Calendar.YEAR);
		String month = standardValue(iMONTH, Calendar.MONTH);
		String sDate = year + "-" + month;
		return sDate;
	}

	/**
	 * 返回当前时间的年
	 */
	public static String parseToYear(Date date) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int iYEAR = calendar.get(Calendar.YEAR);
		String year = standardValue(iYEAR, Calendar.YEAR);
		String sDate = year;
		return sDate;
	}

	/**
	 * 时间字符串转换成时间戳
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Long toTimeStamp(String dateStr) {
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date.getTime() / 1000;
	}

	/**
	 * 时间字符串转换成时间戳字符串
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String toTimeStampStr(String dateStr) {
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}

		return Long.toString(toTimeStamp(dateStr));
	}

	/**
	 * 时间字符串转换成时间戳字符串（秒）
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String toTimeStampStrM(Date date) {
		return Long.toString(date.getTime() / 1000);
	}

	/**
	 * 时间字符串转换成时间戳字符串（毫秒）
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String toTimeStampStr(Date date) {
		return Long.toString(date.getTime());
	}
	
	/**
	 * 将时间转换成刻度
	 * 
	 * @param time
	 * @return
	 */
	public static Double toTimeScale(String time) {
		if (StringUtils.isEmpty(time)) {
			return null;
		}
		String[] timeArr = time.split(":");
		double hour = Double.parseDouble(timeArr[0]);
		double minute = Double.parseDouble(timeArr[1]);

		double scale = 0;
		if (minute < 12) {
			scale = hour;
		} else if (minute >= 12 && minute < 24) {
			scale = NumberUtil.add(hour, 0.2);
		} else if (minute >= 24 && minute < 36) {
			scale = NumberUtil.add(hour, 0.4);
		} else if (minute >= 36 && minute < 48) {
			scale = NumberUtil.add(hour, 0.6);
		} else if (minute >= 48) {
			scale = NumberUtil.add(hour, 0.8);
		}
		return scale;
	}

	public static int getDayOfMonth(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		int result = 0;
		try {
			cal.setTime(format.parse(date));
			result = cal.get(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static int getMaxDayOfMonth(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();
		int result = 0;
		try {
			cal.setTime(format.parse(date));
			result = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 四舍五入时间 09:30→10:00
	 * 
	 * @param timeStr
	 * @return
	 */
	public static String roundHour(String timeStr) {
		String hourStr = timeStr.substring(0, 2);
		Integer hour = Integer.parseInt(hourStr);
		String minuteStr = timeStr.substring(3, 5);
		Integer minute = Integer.parseInt(minuteStr);
		if (minute >= 30) {
			hour++;
		}
		LocalTime localTime = LocalTime.of(hour, 0);
		String resultTime = localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
		return resultTime;
	}

	/**
	 * 四舍五入小时
	 * 
	 * @param time
	 * @return
	 */
	public static int roundHour(LocalTime time) {
		if (time.getMinute() >= 30) {
			time.plusHours(1);
		}
		return time.getHour();
	}
}
