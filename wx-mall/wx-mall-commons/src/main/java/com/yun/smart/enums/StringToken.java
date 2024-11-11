package com.yun.smart.enums;

/**
 * 字符串标点常量
 */
public enum StringToken {

    /**
     * 说明符号
     */
    COLON(":"),
    /**
     * 点
     */
    DOT("."),
    /**
     * 分号
     */
    SEMICOLON(";"),
    /**
     * 逗号
     */
    COMMA(","),
    /**
     * 下划线
     */
    UNDERLINE("_"),
    /**
     * 中划线
     */
    MIDLINE("-"),
    /**
     * 空格
     */
    BLANK(" "),
    /**
     * 等号
     */
    EQUAL("="),

    /**
     * 加号
     */
    PLUS("+"),
	
    /**
     * 井号
     */
    HASHTAG("#"),

    /**
     * 空字符串
     */
    EMPTY("");

    String value;

    private StringToken(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
