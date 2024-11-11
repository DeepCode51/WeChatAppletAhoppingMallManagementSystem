package com.yun.smart.token;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.yun.smart.utils.JsonUtils;

/**
 * 用户上下文
 */
public class UserToken implements Serializable {

	private static final long serialVersionUID = 4279766463499132930L;

	/**
     * 当前用户帐号
     */
    private Long userId;

    /**
     * 当前用户名称
     */
    private String userName;

    /**
     * 用户存存储容器
     */
    private Map<String, Object> userContext;

    public Long getUserId() {
        return userId;
    }

    public UserToken setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserToken setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Map<String, Object> getUserContext() {
        return userContext;
    }

    public UserToken setUserContext(HashMap<String, Object> userContext) {
        this.userContext = userContext;
        return this;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}