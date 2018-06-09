package com.hd.api.constant;

/**
 * 通用常量
 * 
 * @author hoda
 *
 */
public class DateTimeConst {

	public final static String FORMAT_YEAR_MONTH = "yyyy-MM";

	public final static String FORMAT_YEAR_DAY = "yyyy-MM-dd";

	public final static String FORMAT_YEAR_HOUR = "yyyy-MM-dd hh";
	
	public final static String FORMAT_WO_YEAR_HOUR = "yyyy-MM-dd_H";

	public final static String FORMAT_YEAR_MINUTE = "yyyy-MM-dd HH:mm";
	
	public final static String FORMAT_YEAR_SECOND = "yyyy-MM-dd HH:mm:ss";

	public final static String FORMAT_HOUR_SECOND = "HH:mm:ss";

	public final static String DAY_LAST = " 23:59:59.999";

	/**
	 * 日期
	 * 
	 * @author hoda
	 *
	 */
	public enum DateTypeEnum {
		DAY, MONTH
	}
}
