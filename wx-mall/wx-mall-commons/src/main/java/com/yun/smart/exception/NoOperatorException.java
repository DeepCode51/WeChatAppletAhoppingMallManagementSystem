package com.yun.smart.exception;

/**
 * token异常
 */
public class NoOperatorException extends RuntimeException {

	private static final long serialVersionUID = -7859763020067126153L;

	public NoOperatorException(Exception e) {
        super(e.getLocalizedMessage(), e);
    }

    public NoOperatorException() {
        super("Operator is Missing");
    }
}
