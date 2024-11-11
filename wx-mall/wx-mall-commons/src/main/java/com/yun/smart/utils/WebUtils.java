package com.yun.smart.utils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 网络相关工具类
 */
public class WebUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebUtils.class);

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return (ServletRequestAttributes) requestAttributes;
        }
        LOGGER.warn("Not a servlet request attributes");
        return null;
    }

    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes sra = getRequestAttributes();
        if (sra == null) {
            LOGGER.warn("ServletRequestAttributes is null");
            return null;
        }
        return sra.getRequest();
    }

    public static HttpServletResponse getHttpServletResponse() {
        ServletRequestAttributes sra = getRequestAttributes();
        if (sra == null) {
            LOGGER.warn("ServletRequestAttributes is null");
            return null;
        }
        return sra.getResponse();
    }

    public static HttpSession getHttpSession() {
        HttpServletRequest request = getHttpServletRequest();
        if (request != null) {
            HttpSession session = request.getSession();
            LOGGER.debug("Session Id = " + session.getId());
            return session;
        }
        LOGGER.warn("HttpServletRequest is null");
        return null;
    }

    /**
     * 在通过了Apache,Squid等反向代理软件就不能获取到客户端的真实IP地址,用request.getRemoteAddr()方法获取的IP地址是：127.0.0.1
     * 或 192.168.1.110，而并不是客户端的真实ＩＰ.
     * 经过代理以后，由于在客户端和服务之间增加了中间层，因此服务器无法直接拿到客户端的IP，服务器端应用也无法直接通过转发请求的地址返回给客户端。但是在转发请求的HTTP头信息中，增加了X－FORWARDED－FOR信息。用以跟踪原有的客户端IP地址和原来客户端请求的服务器地址
     * 如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串Ｉｐ值，究竟哪个才是真正的用户端的真实IP呢？答案是取X-Forwarded-For中第一个非unknown的有效IP字符串
     * squid的设置问题会导致x-forwarded-for为unknown
     *
     * @param request
     * @return
     */
    public static String getRemoteAddr(ServletRequest request) {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String ip = httpServletRequest.getHeader("x-forwarded-for"); // apache的代理
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = httpServletRequest.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = httpServletRequest.getHeader("WL-Proxy-Client-IP"); // weblogic的代理
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = httpServletRequest.getRemoteAddr();
            }
            return ip;
        }
        return null;
    }

    /**
     * 在通过了Apache,Squid等反向代理软件就不能获取到客户端的真实IP地址,用request.getRemoteAddr()方法获取的IP地址是：127.0.0.1
     * 或 192.168.1.110，而并不是客户端的真实ＩＰ.
     * 经过代理以后，由于在客户端和服务之间增加了中间层，因此服务器无法直接拿到客户端的IP，服务器端应用也无法直接通过转发请求的地址返回给客户端。但是在转发请求的HTTP头信息中，增加了X－FORWARDED－FOR信息。用以跟踪原有的客户端IP地址和原来客户端请求的服务器地址
     * 如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串Ｉｐ值，究竟哪个才是真正的用户端的真实IP呢？答案是取X-Forwarded-For中第一个非unknown的有效IP字符串
     * squid的设置问题会导致x-forwarded-for为unknown
     *
     * @return
     */
    public static String getRemoteAddr() {
        return getRemoteAddr(getHttpServletRequest());
    }

    /**
     * 获取Request中的User-Agent的Header属性
     *
     * @return User-Agent属性值
     */
    public static String getUserAgent() {
        HttpServletRequest request = getHttpServletRequest();
        if (request == null) {
            return StringUtils.EMPTY;
        }
        return request.getHeader("User-Agent");
    }
}
