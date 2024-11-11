package com.yun.smart.utils;

import java.util.UUID;

/**
 * Created by blue on 2017/5/31.
 * <p>
 * UUID生成类
 */
public class UUIDGenerator {

    public static String generate() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
