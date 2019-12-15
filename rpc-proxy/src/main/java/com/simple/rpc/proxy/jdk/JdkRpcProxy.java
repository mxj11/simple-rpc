package com.simple.rpc.proxy.jdk;

import com.simple.rpc.proxy.InvokeCallBack;
import com.simple.rpc.proxy.RpcProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkRpcProxy implements InvocationHandler, RpcProxy {
    private InvokeCallBack invokeCallBack;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return invokeCallBack.callBack(proxy, method, args);
    }

    @Override
    public <T> T getProxy(Class<T> clz) {
        return (T) Proxy.newProxyInstance(clz.getClassLoader(), new Class<?>[]{clz}, this);
    }

    public void setInvokeCallBack(InvokeCallBack invokeCallBack) {
        this.invokeCallBack = invokeCallBack;
    }
}
