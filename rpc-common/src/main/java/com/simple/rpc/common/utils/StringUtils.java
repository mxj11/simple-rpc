package com.simple.rpc.common.utils;

public class StringUtils {
    public static boolean isEmpty(String content) {
        return "".equals(content) || content == null;
    }

    private StringUtils() {
    }
}
