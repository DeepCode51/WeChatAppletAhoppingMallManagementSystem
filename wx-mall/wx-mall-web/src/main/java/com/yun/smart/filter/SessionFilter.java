package com.yun.smart.filter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yun.smart.consts.SessionConsts;
import com.yun.smart.enums.ReturnCode;
import com.yun.smart.result.JsonResult;
import com.yun.smart.service.AuthService;
import com.yun.smart.token.UserToken;
import com.yun.smart.utils.JsonUtils;

@Component
public class SessionFilter implements Filter{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(SessionFilter.class);

	private Set<String> whiteUrls = new HashSet<>();
	
	@Resource
	private AuthService authService;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.info("Session Filter init......");
		// 加载白名单信息
		String path = SessionFilter.class.getResource("/").getFile();
		this.installWhiteUrls(path + "whiteUrls.txt");
	}

	/**
	 * 登录校验
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURL().toString();// 获取请求url地址
		//白名单请求不做token校验
		if (this.isWhiteReq(url)) {
            filterChain.doFilter(request, response);
            return;
        } 
		
		String authToken = getHeader(req, SessionConsts.AUTH_TOKEN_NAME);
		if (StringUtils.isNotBlank(authToken) && authService.isLogin(authToken)) {
			UserToken userToken = authService.getUserToken(authToken);
			LOGGER.info("[SessionFilter]用户已登录，当前登录用户为【{},{}】",userToken.getUserId(),userToken.getUserName() );
			filterChain.doFilter(request, response);
			return;
		}
        
        LOGGER.info("[SessionFilter]用户未登录，返回当前用户未登录");
        JsonResult result = new JsonResult(ReturnCode.USER_NOT_LOGIN);
        if (null != result) {
            response.setContentType("application/json");
            ServletOutputStream out = response.getOutputStream();
            out.write(JsonUtils.toJson(result).getBytes());
            out.close();
        }
	}

	@Override
	public void destroy() {
		LOGGER.info("Session Filter destroy......");
	}
	
	/**
	 * 从文件加载白名单
	 * @param string
	 */
	private void installWhiteUrls(String fileName) {
		BufferedReader reader = null;
		FileInputStream fis = null;
		try {
			File f = new File(fileName);
			if (f.isFile() && f.exists()) {
				fis = new FileInputStream(f);
				reader = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
				String line;
				while ((line = reader.readLine()) != null) {
					if (!"".equals(line.trim())) {
						whiteUrls.add(line.trim());
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("加载白名单失败", e);
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				LOGGER.error("InputStream关闭异常", e);
			}
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				LOGGER.error("FileInputStream关闭异常", e);
			}
		}
	}
	
	/**
	 * 判断是否是白名单
	 */
	private boolean isWhiteReq(String referUrl) {
		if (StringUtils.isBlank(referUrl)) {
			return true;
		} else {
			String refHost = "";
			referUrl = referUrl.toLowerCase();
			if (referUrl.startsWith("http://")) {
				refHost = referUrl.substring(7);
			} else if (referUrl.startsWith("https://")) {
				refHost = referUrl.substring(8);
			}

			for (String urlTemp : whiteUrls) {
				if (refHost.indexOf(urlTemp.toLowerCase()) > -1) {
					return true;
				}
			}
		}

		return false;
	}
	
	/**
	 * 获取请求头参数
	 * @param req
	 * @param param
	 * @return
	 */
	private String getHeader(HttpServletRequest req, String param) {
		return req.getHeader(param) == null ? "" : req.getHeader(param);
	}

}
