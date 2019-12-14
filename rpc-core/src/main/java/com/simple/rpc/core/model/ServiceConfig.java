package com.simple.rpc.core.model;

import lombok.Data;

@Data
public class ServiceConfig<T> {
    private Class<T> clz;

    private T service;

    /**
     * 底层网络框架信息
     */
    private NetworkConfig networkConfig;
}
