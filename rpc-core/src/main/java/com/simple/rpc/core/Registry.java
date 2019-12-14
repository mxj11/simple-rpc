package com.simple.rpc.core;

import com.simple.rpc.core.model.ServiceRegistryInfo;

import java.util.List;

public interface Registry {
    /**
     * 服务注册
     *
     * @param registerUrl 注册的url
     * @param serviceRegistryInfo ServiceRegistryInfo
     * @return 服务注册是否成功
     */
    boolean register(String registerUrl, ServiceRegistryInfo serviceRegistryInfo);

    /**
     * 服务发现
     *
     * @param registerUrl 注册的url
     * @return 注册列表
     */
    List<ServiceRegistryInfo> discover(String registerUrl);
}
