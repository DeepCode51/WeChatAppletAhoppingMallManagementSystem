package com.yun.smart.utils;

import java.security.MessageDigest;
import java.util.Base64;

import com.yun.smart.exception.BussinessException;

public class SecurityUtil {

	public static String encrypt(String content) {
		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] digest1 = sha256.digest(content.getBytes("utf-8"));
			byte[] base641 = Base64.getEncoder().encode(digest1);
			byte[] digest2 = md5.digest(base641);
			return new String(Base64.getEncoder().encode(digest2));
		} catch (Exception e) {
			throw new BussinessException("密码加密失败");
		}
	}
	
	public static void main(String[] args) {
		System.out.println(SecurityUtil.encrypt("guozl"));;
	}
	
}
