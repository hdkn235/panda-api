package com.hd.api.utils.common;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility
 * 
 * @author Hoda
 * 
 * @since 3.0.0
 * 
 */
public class CastUtil {

	public static final char UNDERLINE = '_';

	private CastUtil() {
	}

	/**
	 * 将Double数据转换为字符串
	 * 
	 * @param d
	 *            Double数据
	 * @return
	 */
	public static String toFormatString(Double d) {
		if (d == null)
			return "";
		String format = "###########0.####";
		return (new java.text.DecimalFormat(format)).format(d.doubleValue());
	}

	/**
	 * 将Double数据转换为字符串
	 * 
	 * @param d
	 *            Double数据
	 * @param format
	 *            自定义格式化
	 * @return
	 */
	public static String toFormatString(Double d, String format) {
		if (d == null)
			return null;
		if (format == null)
			format = "###########0.####";
		return (new java.text.DecimalFormat(format)).format(d.doubleValue());
	}

	/**
	 * 将Double数据转换为字符串，尾数四舍五入
	 * 
	 * @param d
	 *            Float数据
	 * @param decimalDigits
	 *            保留小数位数
	 * @return
	 */
	public static String toRoundString(Double d, int decimalDigits) {
		if (d == null || decimalDigits < 0)
			return "";
		double value = d.doubleValue();
		boolean bl = true;
		if (value < 0d) {
			bl = false;
			value = value * -1d;
		}
		// value = toPrecisionDouble(value, decimalDigits);
		String format = "###########0";
		String result = null;
		if (decimalDigits <= 0) {
			result = (new java.text.DecimalFormat(format)).format(value);
			return result;
		}
		format += ".";
		for (int i = 0; i < decimalDigits; i++) {
			format += "#";
		}
		result = (new java.text.DecimalFormat(format)).format(value);
		if (!bl)
			return "-" + result;
		return result;
	}

	/**
	 * 将Double数据转换为字符串，尾数四舍五入
	 * 
	 * @param d
	 *            Float数据
	 * @return
	 */
	public static String toRoundString(Double d) {
		return toRoundString(d, 2);
	}

	/**
	 * 得到精度范围内四舍五入浮点数值
	 * 
	 * @param value
	 *            浮点数值
	 * @param decimalDigits
	 *            保留小数位数
	 * @return 四舍五入浮点数值
	 */
	public static double toPrecisionDouble(double value, int decimalDigits) {
		double intValue = 1.0f;
		for (int i = 0; i < decimalDigits; i++)
			intValue *= 10.0f;
		int iPart = (int) (value * intValue);
		double fPart = value * 10.0f * intValue;
		int endPart = (int) (fPart - iPart * 10.0f);
		if ((endPart) >= 5)
			return (iPart + 1) * 1.0f / intValue;// 四舍五入，尾数+1
		else
			return iPart * 1.0f / intValue; // 截去尾数
	}

	/**
	 * 转为 String 型
	 */
	public static String castString(Object obj) {
		return CastUtil.castString(obj, "");
	}

	/**
	 * 转为 String 型（提供默认值）
	 */
	public static String castString(Object obj, String defaultValue) {
		return obj != null ? String.valueOf(obj) : defaultValue;
	}

	/**
	 * 转为 double 型
	 */
	public static double castDouble(Object obj) {
		return CastUtil.castDouble(obj, 0);
	}

	/**
	 * 转为 double 型（提供默认值）
	 */
	public static double castDouble(Object obj, double defaultValue) {
		double doubleValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtils.isNotEmpty(strValue)) {
				try {
					doubleValue = Double.parseDouble(strValue);
				} catch (NumberFormatException e) {
					doubleValue = defaultValue;
				}
			}
		}
		return doubleValue;
	}

	/**
	 * 转为 long 型
	 */
	public static long castLong(Object obj) {
		return CastUtil.castLong(obj, 0);
	}

	/**
	 * 转为 long 型（提供默认值）
	 */
	public static long castLong(Object obj, long defaultValue) {
		long longValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtils.isNotEmpty(strValue)) {
				try {
					longValue = Long.parseLong(strValue);
				} catch (NumberFormatException e) {
					longValue = defaultValue;
				}
			}
		}
		return longValue;
	}

	/**
	 * 转为 int 型
	 */
	public static int castInt(Object obj) {
		return CastUtil.castInt(obj, 0);
	}

	/**
	 * 转为 int 型（提供默认值）
	 */
	public static int castInt(Object obj, int defaultValue) {
		int intValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtils.isNotEmpty(strValue)) {
				try {
					intValue = Integer.parseInt(strValue);
				} catch (NumberFormatException e) {
					intValue = defaultValue;
				}
			}
		}
		return intValue;
	}

	/**
	 * 转为 boolean 型
	 */
	public static boolean castBoolean(Object obj) {
		return CastUtil.castBoolean(obj, false);
	}

	/**
	 * 转为 boolean 型（提供默认值）
	 */
	public static boolean castBoolean(Object obj, boolean defaultValue) {
		boolean booleanValue = defaultValue;
		if (obj != null) {
			booleanValue = Boolean.parseBoolean(castString(obj));
		}
		return booleanValue;
	}

	/**
	 * 命名转换--驼峰→下划线
	 * 
	 * @param param
	 * @return
	 */
	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 命名转换--帕斯卡→下划线
	 * 
	 * @param param
	 * @return
	 */
	public static String pascalToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				if (i > 0) {
					sb.append(UNDERLINE);
				}
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 命名转换--下划线→驼峰(一)
	 * 
	 * @param param
	 * @return
	 */
	public static String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == UNDERLINE) {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 命名转换--下划线→驼峰 （二）
	 * 
	 * @param param
	 * @return
	 */
	public static String underlineToCamel2(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		StringBuilder sb = new StringBuilder(param);
		Matcher mc = Pattern.compile("_").matcher(param);
		int i = 0;
		while (mc.find()) {
			int position = mc.end() - (i++);
			// String.valueOf(Character.toUpperCase(sb.charAt(position)));
			sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
		}
		return sb.toString();
	}
}
