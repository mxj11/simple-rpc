package com.simple.rpc.registry;

import com.simple.rpc.core.model.ServiceRegistryInfo;
import com.simple.rpc.registry.zookeeper.service.ServiceRegistryImpl;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        ServiceRegistryInfo serviceRegistryInfo = new ServiceRegistryInfo();
        serviceRegistryInfo.setInterfaceName("com.lxz.api.UserService");
        serviceRegistryInfo.setServiceName("app-service");
        serviceRegistryInfo.setMethodName("hello");
        serviceRegistryInfo.setHost("192.168.149.132");
        serviceRegistryInfo.setPort(8085);
        new ServiceRegistryImpl().register(serviceRegistryInfo);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
