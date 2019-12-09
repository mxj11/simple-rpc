package com.simple.rpc.registry.zookeeper.service;

import com.alibaba.fastjson.JSON;
import com.simple.rpc.common.utils.CollectionUtils;
import com.simple.rpc.core.ServiceDiscovery;
import com.simple.rpc.core.model.ServiceRegistryInfo;
import com.simple.rpc.registry.zookeeper.curator.ZookeeperOperator;
import com.simple.rpc.registry.zookeeper.curator.ZookeeperService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServiceDiscoveryImpl implements ServiceDiscovery {

    @Override
    public List<ServiceRegistryInfo> discover() {
        List<ServiceRegistryInfo> serviceRegistryInfoList = new ArrayList<>();
        ZookeeperOperator zookeeperOperator = new ZookeeperService();
        try {
            List<String> list = zookeeperOperator.getChildren("/rpc");
            System.out.println("list=" + list);
            if (CollectionUtils.isEmpty(list)) {
                return serviceRegistryInfoList;
            }
            for (String childPath : list) {
                List<String> grandChildPathList = zookeeperOperator.getChildren("/rpc/" + childPath);
                if (CollectionUtils.isEmpty(grandChildPathList)) {
                    return serviceRegistryInfoList;
                }
                for (String grandChildPath : grandChildPathList) {
                    String data = zookeeperOperator.getData("/rpc/" + childPath + "/" + grandChildPath);
                    ServiceRegistryInfo serviceRegistryInfo = JSON.parseObject(data, ServiceRegistryInfo.class);
                    serviceRegistryInfoList.add(serviceRegistryInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return serviceRegistryInfoList;
        }
        System.out.println("serviceRegistryInfoList=" + serviceRegistryInfoList);
        return serviceRegistryInfoList;
    }
}
