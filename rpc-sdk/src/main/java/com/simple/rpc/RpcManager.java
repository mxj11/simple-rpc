package com.simple.rpc;

import com.simple.rpc.core.model.ServiceConfig;
import com.simple.rpc.network.NetworkManager;
import com.simple.rpc.registry.RegisterManager;

public class RpcManager {
    private RpcManager() {
    }

    private static class RpcHolder {
        private static final RpcManager INSTANCE = new RpcManager();
    }

    public static RpcManager getInstance() {
        return RpcHolder.INSTANCE;
    }

    public void init(ServiceConfig serviceConfig) {
        // 服务注册初始化
        RegisterManager.getInstance().initRegistry(serviceConfig.getRegisterUrl(), serviceConfig.getServiceRegistryInfo());

        // 底层网络框架初始化
        NetworkManager.getInstance().initNetwork(serviceConfig.getNetworkConfig());
    }
}
