package com.hd.api.utils.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 属性工具类
 * 
 * @author Hongcy
 * 
 */
public class PropsUtil {

	private static Logger logger = LoggerFactory.getLogger(PropsUtil.class);

	private final static Map<String, Properties> propertiesMap = new HashMap<>();

	/**
	 * 加载属性文件
	 */
	public static Properties loadProps(String fileName) {
		Properties props = null;
		InputStream is = null;
		try {
			// 利用类加载器读取配置文件
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			if (is == null) {
				throw new FileNotFoundException(fileName + " file is not found");
			}
			props = new Properties();
			props.load(is);
		} catch (IOException e) {
			logger.error("load properties file failure", e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("close input stream failure", e);
				}
			}
		}
		return props;
	}
	
	/**
	 * 是否包含某属性
	 */
	public static boolean containsKey(Properties props, String key) {
		return props.containsKey(key);
	}

	/**
	 * 获取字符型属性
	 */
	public static String getString(Properties props, String key) {
		String value = "";
		if (props.containsKey(key)) {
			value = props.getProperty(key);
		}
		return value;
	}
	
	/**
	 * 根据属性文件名称和属性名获取属性值
	 * 
	 * @param fileName
	 *            属性文件名称
	 * @param key
	 *            属性名称
	 * @return 属性值
	 */
	public static String getString(String fileName, String key) {
		if (null != fileName && 0 < fileName.trim().length()) {
			if (propertiesMap.containsKey(fileName)) {
				return propertiesMap.get(fileName).getProperty(key);
			}

			Properties properties = loadProps(fileName);
			propertiesMap.put(fileName, properties);
			return PropsUtil.getString(fileName, key);
		}
		return null;
	}

	/**
	 * 获取字符型属性（带有默认值）
	 */
	public static String getString(Properties props, String key, String defaultValue) {
		String value = defaultValue;
		if (props.containsKey(key)) {
			value = props.getProperty(key);
		}
		return value;
	}

	/**
	 * 获取数值型属性
	 */
	public static int getInt(Properties props, String key) {
		int value = 0;
		if (props.containsKey(key)) {
			value = CastUtil.castInt(props.getProperty(key));
		}
		return value;
	}

	// 获取数值型属性（带有默认值）
	public static int getInt(Properties props, String key, int defaultValue) {
		int value = defaultValue;
		if (props.containsKey(key)) {
			value = CastUtil.castInt(props.getProperty(key));
		}
		return value;
	}

	/**
	 * 获取布尔型属性
	 */
	public static boolean getBoolean(Properties props, String key) {
		return getBoolean(props, key, false);
	}

	/**
	 * 获取布尔型属性（带有默认值）
	 */
	public static boolean getBoolean(Properties props, String key, boolean defaultValue) {
		boolean value = defaultValue;
		if (props.containsKey(key)) {
			value = CastUtil.castBoolean(props.getProperty(key));
		}
		return value;
	}

}