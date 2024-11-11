package com.yun.smart.utils;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.xeustechnologies.jcl.JarClassLoader;
import org.xeustechnologies.jcl.JclObjectFactory;
import org.xeustechnologies.jcl.proxy.CglibProxyProvider;
import org.xeustechnologies.jcl.proxy.ProxyProviderFactory;

public class BeanUtils {

    /**
     * 获取ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        ServletContext sc = WebUtils.getHttpServletRequest().getServletContext();
        return WebApplicationContextUtils.getWebApplicationContext(sc);
    }

    /**
     * 获取Bean
     *
     * @param name Bean名称
     */
    public static <T> T getBean(String name) {
        return (T) getApplicationContext().getBean(name);
    }

    /**
     * 获取Bean
     *
     * @param clazz 对象class
     */
    public static <T> T getBean(Class<?> clazz) {
        return (T) getApplicationContext().getBean(clazz);
    }

    public static <T> T getInstance(String url) {
        if(StringUtils.isBlank(url)) {
            return null;
        }
        JarClassLoader jc = new JarClassLoader();
        ProxyProviderFactory.setDefaultProxyProvider(new CglibProxyProvider());

        JclObjectFactory factory = JclObjectFactory.getInstance(true);
        return (T) factory.create(jc, url);
    }
}
