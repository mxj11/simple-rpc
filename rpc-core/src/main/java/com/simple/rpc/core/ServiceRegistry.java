package com.simple.rpc.core;


import com.simple.rpc.core.model.ServiceRegistryInfo;

/**
 * 服务注册
 */
public interface ServiceRegistry {
    boolean register(ServiceRegistryInfo serviceRegistryInfo);
}
