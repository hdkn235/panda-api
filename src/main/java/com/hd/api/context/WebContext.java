package com.hd.api.context;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于封装 Request 与 Response 对象
 *
 * @author huangyong
 * @since 1.0.0
 */
public class WebContext {

	private static ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();
	private static ThreadLocal<HttpServletResponse> responseHolder = new ThreadLocal<>();

	/**
	 * 初始化
	 */
	public static void init(HttpServletRequest request,
			HttpServletResponse response) {
		requestHolder.set(request);
		responseHolder.set(response);
	}

	/**
	 * 销毁
	 */
	public static void destroy() {
		requestHolder.remove();
		responseHolder.remove();
	}

	/**
	 * 获取 Request 对象
	 */
	public static HttpServletRequest getRequest() {
		return requestHolder.get();
	}

	/**
	 * 获取 Response 对象
	 */
	public static HttpServletResponse getResponse() {
		return responseHolder.get();
	}

	/**
	 * 获取请求信息
	 */
	public static String getRequestInfo() {
		HttpServletRequest request = getRequest();
		String url = request.getRequestURL().toString();
		String method = request.getMethod();
		Map<String, String[]> params = request.getParameterMap();
		String queryString = "";
		for (String key : params.keySet()) {
			String[] values = params.get(key);
			for (int i = 0; i < values.length; i++) {
				String value = values[i];
				queryString += key + "=" + value + "&";
			}
		}
		return String.format("Url:%s, Method:%s, Params:%s", url, method,
				queryString);
	}
}
