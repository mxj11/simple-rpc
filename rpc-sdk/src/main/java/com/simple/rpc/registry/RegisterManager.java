package com.simple.rpc.registry;

import com.simple.rpc.common.utils.StringUtils;
import com.simple.rpc.core.Registry;
import com.simple.rpc.core.model.ServiceRegistryInfo;
import com.simple.rpc.exception.NotSupportException;
import com.simple.rpc.registry.multicast.service.MulticastService;
import com.simple.rpc.registry.zookeeper.service.ZookeeperRegisterService;

public class RegisterManager {
    Registry registerService = null;

    private RegisterManager() {

    }

    private static class RegisterHolder {
        private static final RegisterManager INSTANCE = new RegisterManager();
    }

    public static RegisterManager getInstance() {
        return RegisterHolder.INSTANCE;
    }

    public void initRegistry(String registerUrl, ServiceRegistryInfo serviceRegistryInfo) {
        if (StringUtils.isEmpty(registerUrl)) {
            throw new IllegalArgumentException("registerUrl is empty");
        }
        if (registerUrl.startsWith("zookeeper://")) {
            registerService = new ZookeeperRegisterService();
            registerUrl = registerUrl.replace("zookeeper://", "");
        } else if (registerUrl.startsWith("multicast://")) {
            registerService = new MulticastService();
        } else {
            throw new NotSupportException("not support url register");
        }
        registerService.register(registerUrl, serviceRegistryInfo);
    }
}
