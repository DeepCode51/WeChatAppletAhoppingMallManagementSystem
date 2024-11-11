package com.yun.smart.exception;

/**
 * 数据库查询不到数据时，抛出异常
 */
public class NoResultException extends DatabaseException {

	private static final long serialVersionUID = 6234936934029107686L;

	public NoResultException(Exception e) {
        super(e);
    }

    public NoResultException() {
        super("The Search Result is Empty");
    }
}
