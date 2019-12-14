package com.simple.rpc.registry.multicast.service;

import com.simple.rpc.core.Registry;
import com.simple.rpc.core.model.ServiceRegistryInfo;

import java.util.List;

public class MulticastService implements Registry {
    @Override
    public boolean register(String registerUrl, ServiceRegistryInfo serviceRegistryInfo) {
        return false;
    }

    @Override
    public List<ServiceRegistryInfo> discover(String registerUrl) {
        return null;
    }
}
