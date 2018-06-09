package com.hd.api.utils.file;

import com.alibaba.fastjson.JSON;
import com.hd.utils.common.CommonUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 图片工具类
 * 
 * @author Hoda
 * 
 */
public class ImageUtil {

	private static final Logger logger = LoggerFactory.getLogger(Process.class);

	/**
	 * 将字节数组进行Base64编码，返回字符传
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesToBase64(byte[] bytes) {
		return Base64.encodeBase64String(bytes);
	}

	/**
	 * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 * 
	 * @param path
	 *            图片路径
	 * @return base64字符串
	 */
	public static String imageToBase64(String path) throws IOException {
		byte[] data = null;
		// 读取图片字节数组
		InputStream in = null;
		try {
			in = new FileInputStream(path);
			data = new byte[in.available()];
			in.read(data);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error(CommonUtil.getStackTraceStr(e));
				}
			}
		}
		// 返回Base64编码过的字节数组字符串
		return Base64.encodeBase64String(data);
	}

	/**
	 * 处理Base64解码并写图片到指定位置
	 * 
	 * @param base64
	 *            图片Base64数据
	 * @param path
	 *            图片保存路径
	 * @return
	 */
	public static boolean base64ToImageFile(String base64, String path) {
		try {
			File file = new File(path);
			if (!file.getParentFile().exists()) {
				// 会创建所有不存在的目录
				file.getParentFile().mkdirs();
			}
			// 对字节数组字符串进行Base64解码并生成图片
			return createImageFile(base64, path);
		} catch (Exception e) {
			logger.error(CommonUtil.getStackTraceStr(e));
		}
		return false;
	}

	/**
	 * 处理Base64解码并输出流
	 * 
	 * @param base64
	 * @param out
	 * @return
	 */
	public static boolean createImageFile(String base64, String path) throws IOException {
		// 图像数据为空
		if (StringUtils.isEmpty(base64)) {
			return false;
		}
		if (StringUtils.isEmpty(path)) {
			return false;
		}
		OutputStream out = new FileOutputStream(path);
		try {
			// Base64解码
			byte[] bytes = Base64.decodeBase64(base64);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {
					// 调整异常数据
					bytes[i] += 256;
				}
			}
			out.write(bytes);
			out.flush();
			return true;
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				logger.error(CommonUtil.getStackTraceStr(e));
			}
		}
	}

	/**
	 * 生成图片
	 * 
	 * @param img
	 * @return 图片路径List
	 */
	public static String createImage(JSONArray img, String path) {
		if (img == null || img.length() == 0) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String imgDirPath = path + format.format(new Date());
		String appPath = CommonUtil.getWebAppsPath();
		String imgName = null;
		String imgPath = null;
		String imgLocalPath = null;
		JSONArray imgUrlArr = new JSONArray();
		Boolean isCreateImg;
		for (int i = 0; i < img.length(); i++) {
			imgName = CommonUtil.createUUID() + ".jpg";
			imgPath = imgDirPath + "/" + imgName;
			imgLocalPath = appPath + "/" + imgPath;
			// base64编码中的+被替换成空格，需要替换回来
			// 彻底解决此问题需要客户端进行urlencode编码
			isCreateImg = base64ToImageFile(img.getString(i).replaceAll(" ", "+"), imgLocalPath);
			if (isCreateImg) {
				imgUrlArr.put(imgPath);
			}
		}
		return imgUrlArr.toString();
	}

	/**
	 * 生成图片
	 * 
	 * @param request
	 * @param path
	 * @return
	 */
	public static String createImage(HttpServletRequest request, String path) {
		// 1、创建一个DiskFileItemFactory工厂
		FileItemFactory factory = new DiskFileItemFactory();
		// 2、创建一个文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 解决上传文件名的中文乱码
		upload.setHeaderEncoding("UTF-8");
		// 3、判断提交上来的数据是否是上传表单的数据
		if (!ServletFileUpload.isMultipartContent(request)) {
			return null;
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String dirPath = path + format.format(new Date());
		String fileName = null;
		String filePath = null;
		String fileLocalPath = null;
		List<String> filePathArr = new ArrayList<>();
		try {
			// 4、使用ServletFileUpload解析器解析上传数据
			// 解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> items = upload.parseRequest(request);
			if (null == items) {
				return null;
			}
			Iterator<FileItem> itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				// 5、如果fileitem中封装的是普通输入项的数据
				if (item.isFormField()) {
					continue;
				}
				// 如果fileitem中封装的是上传文件
				else {
					fileName = CommonUtil.createUUID() + FileUtil.getExt(item.getName());
					filePath = StringUtils.join(dirPath, "/", fileName);
					fileLocalPath = StringUtils.join(CommonUtil.getWebAppsPath(), "/", filePath);
					File savedFile = new File(fileLocalPath);
					if (!savedFile.getParentFile().exists()) {
						// 会创建所有不存在的目录
						savedFile.getParentFile().mkdirs();
					}
					// 6、将文件写入硬盘中
					item.write(savedFile);
					// 删除处理文件上传时生成的临时文件
					item.delete();
					filePathArr.add(filePath);
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtil.getStackTraceStr(e));
		}
		return filePathArr.size() > 0 ? JSON.toJSONString(filePathArr) : null;
	}

	/**
	 * 删除服务器图片
	 * 
	 * @param imgPath
	 * @return
	 */
	public static void deleteImage(List<String> imgPath) {
		if (CollectionUtils.isEmpty(imgPath))
			return;

		try {
			for (int i = 0; i < imgPath.size(); i++) {
				deleteImage(imgPath.get(i));
			}
		} catch (Exception e) {
			logger.error(CommonUtil.getStackTraceStr(e));
		}

	}

	/**
	 * 删除服务器图片
	 * 
	 * @param imgPath
	 * @return
	 */
	public static void deleteImage(String imgPath) {
		if (StringUtils.isEmpty(imgPath)) {
			return;
		}
		String appPath = CommonUtil.getWebAppsPath();
		String imgLocalPath = appPath + "/" + imgPath;
		File file = new File(imgLocalPath);
		if (file.exists() && file.isFile()) {
			file.delete();
		}
	}
}
