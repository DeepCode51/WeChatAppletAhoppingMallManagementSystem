package com.yun.smart.exception;

/**
 * 数据库异常（Jdbc）
 */
public class DatabaseException extends RuntimeException {

	private static final long serialVersionUID = -5545556640350125384L;

	public DatabaseException(Exception e) {
        super(e.getLocalizedMessage(), e);
    }

    public DatabaseException(String msg) {
        super(msg);
    }
}
