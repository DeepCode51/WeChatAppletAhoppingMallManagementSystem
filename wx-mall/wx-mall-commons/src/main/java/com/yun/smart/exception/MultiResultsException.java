package com.yun.smart.exception;

/**
 * 查询明细存在多值
 */
public class MultiResultsException extends DatabaseException {

	private static final long serialVersionUID = -188394209599724091L;

	public MultiResultsException(Exception e) {
        super(e);
    }

    public MultiResultsException() {
        super("The Search Result should be one");
    }
}
