package com.simple.rpc.proxy;

public interface RpcProxy {
    <T> T getProxy(Class<T> clz);
}
