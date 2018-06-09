package com.hd.api.utils.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 字符串工具类
 *
 * @author Hoda
 * @since 1.0.0
 */
public final class StringUtil {

	private StringUtil() {
	}

	/**
	 * 判断字符串是否为空
	 */
	public static boolean isEmpty(String str) {
		if (str != null) {
			str = str.trim();
		}
		return StringUtils.isEmpty(str);
	}

	/**
	 * 判断字符串是否非空
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 判断字符串是否为数字
	 */
	public static boolean isNumeric(String str) {
		return NumberUtils.isDigits(str);
	}

	/**
	 * 正向查找指定字符串
	 */
	public static int indexOf(String str, String searchStr, boolean ignoreCase) {
		if (ignoreCase) {
			return StringUtils.indexOfIgnoreCase(str, searchStr);
		} else {
			return str.indexOf(searchStr);
		}
	}

	/**
	 * 反向查找指定字符串
	 */
	public static int lastIndexOf(String str, String searchStr, boolean ignoreCase) {
		if (ignoreCase) {
			return StringUtils.lastIndexOfIgnoreCase(str, searchStr);
		} else {
			return str.lastIndexOf(searchStr);
		}
	}

	/**
	 * 
	 * @param obj
	 * @param nullDefaultValue
	 *            obj为空时，返回nullDefaultValue的值
	 * @return
	 */
	public static String safeToString(Object obj, String nullDefaultValue) {
		return obj == null ? nullDefaultValue : obj.toString();
	}

	/**
	 * 字符串中去除Tab字符
	 * 
	 * @param strValue
	 * @return
	 */
	public static String trimTAB(String strValue) {
		String strResult = "";

		for (int i = 0; i < strValue.length(); i++) {

			char charTemp = strValue.charAt(i);

			if (charTemp != ' ' && charTemp != '	' && charTemp != '\t') {
				strResult = strValue.substring(i);
				break;
			}
		}

		for (int i = strResult.length() - 1; i >= 0; i--) {

			char charTemp = strResult.charAt(i);

			if (charTemp != ' ' && charTemp != '	' && charTemp != '\t') {
				strResult = strResult.substring(0, i + 1);
				break;
			}
		}

		return strResult;
	}

	/**
	 * 取左、右特征字符串之间的字符串
	 * 
	 * @param originalStr
	 *            <a>gg</a>
	 * @param left
	 *            <a>
	 * @param right
	 *            </a>
	 * @return gg
	 */
	public static String subString(String originalStr, String left, String right) {
		if (originalStr == null || left == null || right == null)
			return originalStr;
		int irigth = originalStr.lastIndexOf(right);
		if (irigth >= 0) {
			String s = originalStr.substring(0, irigth);
			int ileft = s.lastIndexOf(left);
			if (ileft > 0) {
				s = s.substring(ileft + left.length());
				return s;
			} else {
				return originalStr;
			}
		}
		return originalStr;
	}
}
