package com.rpc.simple.consumer;

import com.rpc.demo.model.User;
import com.rpc.demo.service.UserService;
import com.simple.rpc.ApplicationContext;
import com.simple.rpc.core.model.ClientConfig;
import com.simple.rpc.proxy.jdk.JdkRpcProxy;

import java.lang.reflect.Proxy;
import java.util.List;

public class Consumer {
    public static void main(String[] args) {
        ClientConfig clientConfig = new ClientConfig(UserService.class);
        ApplicationContext applicationContext = new ApplicationContext("zookeeper://localhost:2181", null, clientConfig);
        UserService service = applicationContext.getService(UserService.class);
        service.findById(1);
        System.out.println();

    }

}
