package com.simple.rpc.core;

import com.simple.rpc.core.model.ServiceRegistryInfo;

import java.util.List;

public interface ServiceDiscovery {
    List<ServiceRegistryInfo> discover();
}
