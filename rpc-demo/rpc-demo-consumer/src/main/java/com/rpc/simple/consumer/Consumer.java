package com.rpc.simple.consumer;

import com.rpc.demo.service.UserService;
import com.simple.rpc.ApplicationContext;
import com.simple.rpc.core.model.ClientConfig;

public class Consumer {
    public static void main(String[] args) {
        ClientConfig clientConfig = new ClientConfig(UserService.class);
        ApplicationContext applicationContext = new ApplicationContext("zookeeper://localhost:2181", null, clientConfig);
        Object service = applicationContext.getService(UserService.class);

    }

}
