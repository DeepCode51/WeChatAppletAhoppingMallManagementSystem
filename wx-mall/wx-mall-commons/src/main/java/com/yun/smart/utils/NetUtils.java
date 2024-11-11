package com.yun.smart.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yun.smart.enums.StringToken;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Limq7
 * @createTime 2017年08月03日
 * @email manquan.li@midea.com
 * @description
 */
public class NetUtils {

    private static Logger logger = LoggerFactory.getLogger(NetUtils.class);

    private NetUtils() {super();}

    /**
     * @param request 请求对象
     * @return ip地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * @param ip ip
     * @return 网段
     */
    public static String getSegment(String ip) {
        int dotIndex = ip.lastIndexOf(StringToken.DOT.value());
        return ip.substring(0, dotIndex + 1).concat("0");
    }

    /**
     * @param ipArr ip数组
     * @return 网段
     */
    public static Map<String, String> getSegment(String... ipArr) {
        Map<String, String> ret = new HashMap<>();
        for (String ip : ipArr) {
            ret.put(ip, getSegment(ip));
        }
        return ret;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * 读取url json
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String readJsonFromUrl(String url)  {
        InputStream is = null;
        String jsonText = "";
        try {
            is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            jsonText = readAll(rd);
            is.close();
        } catch(Exception e){

        }
        return jsonText;
    }

    /**
     * 获取请求Body
     */
    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error(e.getLocalizedMessage());
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error(e.getLocalizedMessage());
                }
            }
        }
        return sb.toString();
    }

    public static boolean ipIsInRangeArr(String ip, String...ipRangeArr) {
        boolean flag = false ;
        for(String ipRange:ipRangeArr){
            flag = ipIsInRange(ip,ipRange);
            if(flag){
                break;
            }
        }
        return flag;
    }

    /**
     * 判断IP是否在指定IP段内，方法一（推荐）
     * ipRange IP段（以'-'分隔）
     *
     * @param ipRange
     * @param ip
     * @return boolean
     */
    public static boolean ipIsInRange(String ip, String ipRange) {
        if (ipRange == null)
            throw new NullPointerException("IP段不能为空！");
        if (ip == null)
            throw new NullPointerException("IP不能为空！");
        ipRange = ipRange.trim();
        ip = ip.trim();
        final String REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";
        final String REGX_IPB = REGX_IP + "\\-" + REGX_IP;
        if (!ipRange.matches(REGX_IPB) || !ip.matches(REGX_IP))
            return false;
        int idx = ipRange.indexOf('-');
        String[] sips = ipRange.substring(0, idx).split("\\.");
        String[] sipe = ipRange.substring(idx + 1).split("\\.");
        String[] sipt = ip.split("\\.");
        long ips = 0L, ipe = 0L, ipt = 0L;
        for (int i = 0; i < 4; ++i) {
            ips = ips << 8 | Integer.parseInt(sips[i]);
            ipe = ipe << 8 | Integer.parseInt(sipe[i]);
            ipt = ipt << 8 | Integer.parseInt(sipt[i]);
        }
        if (ips > ipe) {
            long t = ips;
            ips = ipe;
            ipe = t;
        }
        return ips <= ipt && ipt <= ipe;
    }

    /*
     * 判断IP是否在指定IP段内，方法二
     * ipRange IP段（以'-'分隔）
     *
     * @param ipRange
     * @param ip
     * @return boolean
     */
    public static boolean ipInRange(String ip, String ipRange) {
        int idx = ipRange.indexOf('-');
        String beginIP = ipRange.substring(0, idx);
        String endIP = ipRange.substring(idx + 1);
        return getIp2long(beginIP) <= getIp2long(ip)
                && getIp2long(ip) <= getIp2long(endIP);
    }

    public static long getIp2long(String ip) {
        String[] ips = ip.split("\\.");
        long ip2long = 0L;
        for (int i = 0; i < 4; ++i) {
            ip2long = ip2long << 8 | Integer.parseInt(ips[i]);
        }
        return ip2long;
    }

}
