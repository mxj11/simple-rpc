package com.simple.rpc.registry;

import com.simple.rpc.core.model.ServiceRegistryInfo;
import com.simple.rpc.registry.zookeeper.service.ServiceDiscoveryImpl;
import com.simple.rpc.registry.zookeeper.service.ServiceRegistryImpl;

import java.io.IOException;

public class Test1 {
    public static void main(String[] args) {
        register();
//        discover();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void discover() {
        ServiceDiscoveryImpl serviceDiscovery = new ServiceDiscoveryImpl();
        serviceDiscovery.discover();
    }

    private static void register() {
        ServiceRegistryInfo serviceRegistryInfo = new ServiceRegistryInfo();
        serviceRegistryInfo.setInterfaceName("com.lxz.api.AdminService");
        serviceRegistryInfo.setServiceName("app-service");
        serviceRegistryInfo.setMethodName("say");
        serviceRegistryInfo.setHost("192.168.149.133");
        serviceRegistryInfo.setPort(8095);
        new ServiceRegistryImpl().register(serviceRegistryInfo);
    }
}