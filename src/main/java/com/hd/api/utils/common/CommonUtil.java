package com.hd.api.utils.common;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Random;
import java.util.UUID;

/**
 * 常用工具类
 *
 * @author Hoda
 * @version 1.0
 * @since 2016-7-20
 */
public class CommonUtil {

    /**
     * 创建UUID
     *
     * @return
     */
    public static String createUUID() {
        UUID ranId = UUID.randomUUID();
        return ranId.toString().replace("-", "");
    }

    /**
     * 获取异常堆栈信息
     *
     * @param e
     * @return
     */
    public static String getStackTraceStr(Exception e) {
        // StringWriter将包含堆栈信息
        StringWriter stringWriter = new StringWriter();
        // 必须将StringWriter封装成PrintWriter对象，
        // 以满足printStackTrace的要求
        PrintWriter printWriter = new PrintWriter(stringWriter);
        // 获取堆栈信息
        e.printStackTrace(printWriter);
        // 转换成String，并返回该String
        StringBuffer error = stringWriter.getBuffer();
        return error.toString();
    }

    /**
     * 获取WebRoot目录
     *
     * @return
     */
    public static String getWebRootPath() {
        URL urlpath = Thread.currentThread().getContextClassLoader().getResource("");
        String path = urlpath.toString();
        if (path.startsWith("file")) {
            path = path.substring(5);
        }
        if (path.indexOf("WEB-INF") > 0) {
            path = path.substring(0, path.indexOf("WEB-INF") - 1);
        }
        path.replace("/", File.separator);
        try {
            // 关键啊 ！主要转换文件地址，避免空格问题
            path = URLDecoder.decode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 获取WebApps目录
     *
     * @return
     */
    public static String getWebAppsPath() {
        String rootPath = getWebRootPath();
        String appPath = rootPath.substring(0, rootPath.lastIndexOf("/"));
        return appPath;
    }

    /**
     * 使用反射获取类实例
     *
     * @return 实例
     */
    public static Object GetInstance(String className) {
        Object obj = null;
        try {
            // 获取类
            Class<?> myclass = Class.forName(className);
            // 实例化类
            obj = myclass.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    /**
     * 生成六位验证码
     *
     * @return
     */
    public static String createVcode() {
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int) (Math.random() * 9);
        }
        return vcode;
    }

    /*
     * 获取邀请码
     *
     * @param length
     *
     * @return
     */
    public static String getInviteCode(int length) {
        String[] items = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f",
                "g", "h", "i", "g", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(items[new Random().nextInt(items.length - 1)]);
        }
        return sb.toString();
    }
}
