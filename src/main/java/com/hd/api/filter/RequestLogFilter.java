package com.hd.api.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName="requestLogFilter",urlPatterns="/*")
public class RequestLogFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(Process.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		StopWatch timer = new StopWatch();
		try {
			timer.start();
			chain.doFilter(request, response);
		} finally {
			timer.stop();
			HttpServletRequest in = (HttpServletRequest) request;
			HttpServletResponse out = (HttpServletResponse) response;
			String length = out.getHeader("Content-Length");
			if (length == null || length.length() == 0) {
				length = "-";
			}
			String result = String.format("[%s] [%s] [%s] [%s] [%s]", in.getRemoteAddr(), in.getRequestURI(),
				in.getMethod(), getParamStr((HttpServletRequest) request), timer);
			logger.info(result);
		}

	}

	/**
	 * 记录请求参数到日志中
	 * 
	 * @param request
	 */
	private String getParamStr(HttpServletRequest request) {
		String paraName = null;
		StringBuffer sb = new StringBuffer();
		Enumeration<String> list = request.getParameterNames();
		while (list.hasMoreElements()) {
			paraName = list.nextElement();
			if (StringUtils.isNotEmpty(paraName)) {
				sb.append(String.format("%s:%s,", paraName, request.getParameter(paraName)));
			}
		}
		String paraStr = sb.toString();
		if (StringUtils.isNotEmpty(paraStr)) {
			paraStr = paraStr.substring(0, paraStr.length() - 1);
		}
		return paraStr;
	}

	@Override
	public void destroy() {
	}

}
