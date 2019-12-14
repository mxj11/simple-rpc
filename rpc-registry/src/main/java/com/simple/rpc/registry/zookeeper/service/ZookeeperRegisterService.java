package com.simple.rpc.registry.zookeeper.service;

import com.simple.rpc.core.Registry;
import com.simple.rpc.core.model.ServiceRegistryInfo;

import java.util.List;

public class ZookeeperRegisterService implements Registry {
    @Override
    public boolean register(String registerUrl, ServiceRegistryInfo serviceRegistryInfo) {
        return new ZookeeperServiceRegistryImpl().register(registerUrl, serviceRegistryInfo);
    }

    @Override
    public List<ServiceRegistryInfo> discover(String registerUrl) {
        return new ZookeeperServiceDiscoveryImpl().discover(registerUrl);
    }
}
