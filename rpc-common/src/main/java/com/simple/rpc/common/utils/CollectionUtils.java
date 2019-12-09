package com.simple.rpc.common.utils;

import java.util.Collection;

public class CollectionUtils {
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    private CollectionUtils() {
    }
}
