package com.simple.rpc.core.model;

import lombok.Data;

@Data
public class ServiceConfig {
    /**
     * 注册中心url
     */
    private String registerUrl;

    /**
     * 注册信息
     */
    private ServiceRegistryInfo serviceRegistryInfo;

    /**
     * 底层网络框架信息
     */
    private NetworkConfig networkConfig;
}
