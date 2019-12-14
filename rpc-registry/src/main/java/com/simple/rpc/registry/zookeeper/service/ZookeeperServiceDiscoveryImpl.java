package com.simple.rpc.registry.zookeeper.service;

import com.alibaba.fastjson.JSON;
import com.simple.rpc.common.utils.CollectionUtils;
import com.simple.rpc.core.model.ServiceRegistryInfo;
import com.simple.rpc.registry.zookeeper.curator.ZookeeperOperator;
import com.simple.rpc.registry.zookeeper.curator.ZookeeperService;

import java.util.ArrayList;
import java.util.List;

public class ZookeeperServiceDiscoveryImpl {

    public List<ServiceRegistryInfo> discover(String registerUrl) {
        List<ServiceRegistryInfo> serviceRegistryInfoList = new ArrayList<>();
        registerUrl = registerUrl.replace("zookeeper://", "");
        ZookeeperOperator zookeeperOperator = new ZookeeperService(registerUrl);
        try {
            List<String> list = zookeeperOperator.getChildren("/rpc");
            System.out.println("list=" + list);
            if (CollectionUtils.isEmpty(list)) {
                return serviceRegistryInfoList;
            }
            for (String childPath : list) {
                String data = zookeeperOperator.getData("/rpc/" + childPath);
                ServiceRegistryInfo serviceRegistryInfo = JSON.parseObject(data, ServiceRegistryInfo.class);
                serviceRegistryInfoList.add(serviceRegistryInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return serviceRegistryInfoList;
        }
        System.out.println("serviceRegistryInfoList=" + serviceRegistryInfoList);
        return serviceRegistryInfoList;
    }
}
