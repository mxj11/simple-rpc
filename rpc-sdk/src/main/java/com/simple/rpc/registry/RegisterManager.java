package com.simple.rpc.registry;

import com.simple.rpc.common.utils.StringUtils;
import com.simple.rpc.core.Registry;
import com.simple.rpc.core.model.ServiceRegistryInfo;
import com.simple.rpc.exception.NotSupportException;
import com.simple.rpc.registry.multicast.service.MulticastService;
import com.simple.rpc.registry.zookeeper.service.ZookeeperRegisterService;

import java.util.List;

public class RegisterManager {
    private RegisterManager() {
    }

    private static class RegisterHolder {
        private static final RegisterManager INSTANCE = new RegisterManager();
    }

    public static RegisterManager getInstance() {
        return RegisterHolder.INSTANCE;
    }

    public void register(String registerUrl, ServiceRegistryInfo serviceRegistryInfo) {
        getRegistry(registerUrl).register(registerUrl, serviceRegistryInfo);
    }

    public List<ServiceRegistryInfo> discover(String registerUrl) {
        Registry registry = getRegistry(registerUrl);
        return registry.discover(registerUrl);
    }

    private Registry getRegistry(String registerUrl) {
        Registry registerService = null;
        if (registerUrl.startsWith("zookeeper://")) {
            registerService = new ZookeeperRegisterService();
        } else if (registerUrl.startsWith("multicast://")) {
            registerService = new MulticastService();
        } else {
            throw new NotSupportException("not support url register");
        }
        return registerService;
    }
}
