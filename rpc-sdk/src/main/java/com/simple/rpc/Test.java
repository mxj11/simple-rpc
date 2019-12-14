package com.simple.rpc;

import com.simple.rpc.core.model.ServiceConfig;

public class Test {
    public static void main(String[] args) {
        ServiceConfig serviceConfig = new ServiceConfig();

        RpcManager.getInstance().init(serviceConfig);
    }
}
