package com.simple.rpc.proxy;

import com.simple.rpc.proxy.jdk.JdkRpcProxy;

public class ProxyManager {
    public static <T> T getProxy(Class<T> clz, InvokeCallBack invokeCallBack) {
        JdkRpcProxy jdkRpcProxy = new JdkRpcProxy();
        jdkRpcProxy.setInvokeCallBack(invokeCallBack);
        return jdkRpcProxy.getProxy(clz);
    }
}
