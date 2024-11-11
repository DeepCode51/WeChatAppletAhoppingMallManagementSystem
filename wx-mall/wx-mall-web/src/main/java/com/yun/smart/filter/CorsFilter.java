package com.yun.smart.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CorsFilter implements Filter {

	private final static Logger LOGGER = LoggerFactory.getLogger(CorsFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.info("CorsFilter init ...");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		LOGGER.info("CorsFilter doFilter ...");
		
		HttpServletResponse resp = (HttpServletResponse) response;
		// 设置跨域访问请求头
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,PUT,DELETE");
		resp.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type,access_token,channel");
		resp.setHeader("Access-Control-Max-Age", "86400");
				
		
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		LOGGER.info("CorsFilter destroy ...");
		
	}

}
