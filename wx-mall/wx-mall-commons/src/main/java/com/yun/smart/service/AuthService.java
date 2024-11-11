package com.yun.smart.service;

import java.util.Map;

import com.yun.smart.token.UserLoginParams;
import com.yun.smart.token.UserToken;
import com.yun.smart.token.WeixinLoginParams;

public interface AuthService {

	/**
	 * 检查用户是否已经登录
	 * @return 是否已经登录
	 */
	public boolean isLogin(String authToken);

	/**
	 * 登录保存用户信息
	 * @param userToken
	 * @return
	 */
	public boolean setUserToken(String authToken, UserToken userToken);

	/**
	 * 获取用户登录信息
	 * @return 用户登录信息
	 */
	public UserToken getUserToken(String authToken);

	/**
     * 获取已经登录的用户ID
     * @return 返回用户ID
     */
    public Long getUserId(String authToken);

    /**
     * 获取已经登录的用户名称
     * @return 返回用户名称
     */
    public String getUserName(String authToken);
    
    /**
     * 获取已经登录的用户上下文
     * @return 返回用户上下文
     */
    public Map<String,Object> getUserContext(String authToken);

    /**
     * 用户登录
     * @param params
     * @return
     */
	public String login(UserLoginParams params);

	/**
	 * 小程序登录
	 * @param params
	 * @return
	 */
	public String weAppLogin(WeixinLoginParams params);

}
