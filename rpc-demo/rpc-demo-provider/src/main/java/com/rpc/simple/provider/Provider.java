package com.rpc.simple.provider;

import com.rpc.demo.service.UserService;
import com.simple.rpc.ApplicationContext;
import com.simple.rpc.core.model.NetworkConfig;
import com.simple.rpc.core.model.ServiceConfig;

public class Provider {
    public static void main(String[] args) {
        ServiceConfig<UserService> serviceConfig = new ServiceConfig<>();
        NetworkConfig networkConfig = new NetworkConfig();
        networkConfig.setPort(8850);
        serviceConfig.setNetworkConfig(networkConfig);
        UserService userService = new UserServiceImpl();
        serviceConfig.setClz(UserService.class);
        serviceConfig.setService(userService);
        ApplicationContext applicationContext = new ApplicationContext("zookeeper://localhost:2181", serviceConfig, null);
    }
}
