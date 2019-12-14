package com.simple.rpc.registry.zookeeper.service;

import com.alibaba.fastjson.JSON;
import com.simple.rpc.common.utils.StringUtils;
import com.simple.rpc.core.model.ServiceRegistryInfo;
import com.simple.rpc.registry.zookeeper.curator.ZookeeperOperator;
import com.simple.rpc.registry.zookeeper.curator.ZookeeperService;

public class ZookeeperServiceRegistryImpl {
    public boolean register(String registerUrl, ServiceRegistryInfo serviceRegistryInfo) {
        registerUrl = registerUrl.replace("zookeeper://", "");
        ZookeeperOperator zookeeperOperator = new ZookeeperService(registerUrl);
        try {
            String path = "/rpc/" + serviceRegistryInfo.getInterfaceName();
            String ephemeralNode = zookeeperOperator.createEphemeralNode(path, JSON.toJSONString(serviceRegistryInfo));
            if (StringUtils.isEmpty(ephemeralNode)) {
                retryRegister(registerUrl, serviceRegistryInfo);
                return false;
            }
        } catch (Exception e) {
            retryRegister(registerUrl, serviceRegistryInfo);
            return false;
        }
        System.out.println("|register success|registry info|" + serviceRegistryInfo);
        return true;
    }

    private void retryRegister(String registerUrl, ServiceRegistryInfo serviceRegistryInfo) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("|register failed|try to reRegister");
        register(registerUrl, serviceRegistryInfo);
    }
}
