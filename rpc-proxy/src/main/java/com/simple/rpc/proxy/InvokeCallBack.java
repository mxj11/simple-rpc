package com.simple.rpc.proxy;

import java.lang.reflect.Method;

public interface InvokeCallBack {
    Object callBack(Object proxy, Method method, Object[] args);
}
