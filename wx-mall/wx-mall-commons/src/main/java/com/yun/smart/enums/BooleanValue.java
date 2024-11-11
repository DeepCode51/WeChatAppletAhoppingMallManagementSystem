package com.yun.smart.enums;

/**
 * boolean.
 */
public enum BooleanValue {

	/** intValue = 1 */
    TRUE(1),
    /** intValue = 0 */
    FALSE(0);

    private int value;

    private BooleanValue(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
