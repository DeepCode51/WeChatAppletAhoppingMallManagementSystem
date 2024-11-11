package com.yun.smart.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.yun.smart.consts.SessionConsts;
import com.yun.smart.enums.BussinessType;
import com.yun.smart.enums.ReturnCode;
import com.yun.smart.exception.AppException;
import com.yun.smart.log.BussinessLogger;
import com.yun.smart.log.BussinessLoggerPool;
import com.yun.smart.model.UserInfo;
import com.yun.smart.service.AuthService;
import com.yun.smart.service.UserInfoService;
import com.yun.smart.service.redis.RedisService;
import com.yun.smart.token.UserLoginParams;
import com.yun.smart.token.UserToken;
import com.yun.smart.token.WeixinLoginParams;
import com.yun.smart.token.WeixinToken;
import com.yun.smart.utils.HttpRequest;
import com.yun.smart.utils.JsonUtils;
import com.yun.smart.utils.SecurityUtil;
import com.yun.smart.utils.UUIDGenerator;

/**
 * ServiceImpl - 访问日志
 * @author qihh
 * @version 0.0.1
 */
@Service("authService")
public class AuthServiceImpl implements AuthService {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.ACCESSLOG);

	@Resource
	private RedisService redisService;
	@Resource
	private UserInfoService userInfoService;

	/**
	 * 检查用户是否已经登录
	 * @return 是否已经登录
	 */
	@Override
	public boolean isLogin(String authToken) {
	    return getUserToken(authToken) != null;
	}

	/**
	 * 登录保存用户信息
	 * @param userToken
	 * @return
	 */
	@Override
	public boolean setUserToken(String authToken, UserToken userToken) {
		redisService.put(authToken, userToken);
		return redisService.get(authToken) != null;
	}

	/**
	 * 获取用户登录信息
	 * @return 用户登录信息
	 */
	@Override
	public UserToken getUserToken(String authToken) {
	    if (StringUtils.isBlank(authToken)) {
	    	return null;
	    }
		// 从redis中取用户信息
	    return redisService.get(authToken,UserToken.class);
	}

	/**
     * 获取已经登录的用户ID
     * @return 返回用户ID
     */
	@Override
    public Long getUserId(String authToken) {
        UserToken userToken = getUserToken(authToken);
        if (userToken == null) {
            logger.warn("UserToken is null");
            return null;
        }
        return userToken.getUserId();
    }

    /**
     * 获取已经登录的用户名称
     * @return 返回用户名称
     */
	@Override
    public String getUserName(String authToken) {
       UserToken userToken = getUserToken(authToken);
        if (userToken == null) {
            logger.warn("UserToken is null");
            return null;
        }
        return userToken.getUserName();
    }
    
    /**
     * 获取已经登录的用户上下文
     * @return 返回用户上下文
     */
	@Override
    public Map<String,Object> getUserContext(String authToken) {
    	UserToken userToken = getUserToken(authToken);
    	if (userToken == null) {
    		logger.warn("UserToken is null");
    		return null;
    	}
    	return userToken.getUserContext();
    }

	/**
	 * 用户登录
	 */
	@Override
	public String login(UserLoginParams params) {
		UserInfo userInfo = new UserInfo();
		userInfo.setPhone(params.getAccount());
		userInfo.setPasswd(SecurityUtil.encrypt(params.getPasswd()));
		userInfo = userInfoService.getDetail(userInfo);
		if (null == userInfo) {
			throw new AppException(ReturnCode.ERROR_LOGIN);
		}
		
		UserToken userToken = new UserToken();
		userToken.setUserId(userInfo.getId());
		userToken.setUserName(userInfo.getUserName());
		HashMap<String,Object> userContext = new HashMap<>();
		userContext.put("userInfo", userInfo);
		userToken.setUserContext(userContext);
		String authToken = UUIDGenerator.generate();
		if (setUserToken(authToken, userToken)) {
			return authToken;
		}

		throw new AppException(ReturnCode.FAIL_LOGIN);
	}

	@Override
	public String weAppLogin(WeixinLoginParams params) {
		/*
		 * 获取微信openID
		 * https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
		 */
		String param = "appid="+SessionConsts.APP_ID+"&secret="+SessionConsts.APP_SECRET+"&js_code="+params.getJscode()+"&grant_type=authorization_code";
		try {
//			String result = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", param);
//			WeixinToken weixinToken = JsonUtils.jsonToObject(result, WeixinToken.class);
//			// 校验openid是否已绑定用户
			UserInfo userInfo = new UserInfo();
//			userInfo.setWxOpenid(weixinToken.getOpenid());
			userInfo.setWxOpenid("123456");
			userInfo = userInfoService.getDetail(userInfo);
			if (null == userInfo) {
				userInfo = new UserInfo();
//				userInfo.setWxOpenid(weixinToken.getOpenid());
			userInfo.setWxOpenid("123456");

				userInfoService.addModel(userInfo, null);
			}
			
			UserToken userToken = new UserToken();
			userToken.setUserId(userInfo.getId());
			String authToken = UUIDGenerator.generate();
			if (setUserToken(authToken, userToken)) {
				return authToken;
			}
		} catch (Exception e) {
			throw new AppException(ReturnCode.FAIL_LOGIN);
		}
		
		throw new AppException(ReturnCode.FAIL_LOGIN);
	}
	
}
