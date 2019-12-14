package com.simple.rpc.network;

import com.simple.rpc.core.model.NetworkConfig;
import com.simple.rpc.netty.server.NettyServer;

public class NetworkManager {
    NettyServer nettyServer = null;

    private NetworkManager() {
        nettyServer = new NettyServer();
    }

    private static class NetworkHolder {
        private static final NetworkManager INSTANCE = new NetworkManager();
    }

    public static NetworkManager getInstance() {
        return NetworkHolder.INSTANCE;
    }

    public void initNetwork(NetworkConfig networkConfig) {
        try {
            nettyServer.bind(networkConfig.getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
