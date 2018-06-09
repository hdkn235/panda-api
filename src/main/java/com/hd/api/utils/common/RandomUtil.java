package com.hd.api.utils.common;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

/**
 * 随机数工具类
 *
 * @author huangyong
 * @since 1.0.0
 */
public final class RandomUtil {

    private RandomUtil() {
    }

    /**
     * 生成随机数
     */
    public static String getRandom(int count) {
        return RandomStringUtils.randomNumeric(count);
    }
    
	/**
	 * 获取随机整数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomInt(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}

	/**
	 * 获取随机浮点数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static double getRandomDouble(double min, double max) {
		Random random = new Random();
		return random.nextDouble() * (max - min) + min;
	}
}
