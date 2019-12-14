package com.simple.rpc.network;

import com.simple.rpc.core.model.NetworkConfig;
import com.simple.rpc.netty.client.CallBack;
import com.simple.rpc.netty.client.NettyClient;
import com.simple.rpc.netty.server.NettyServer;

public class NetworkManager {
    private NetworkManager() {
    }

    private static class NetworkHolder {
        private static final NetworkManager INSTANCE = new NetworkManager();
    }

    public static NetworkManager getInstance() {
        return NetworkHolder.INSTANCE;
    }

    public void initServer(NetworkConfig networkConfig) {
        try {
            NettyServer nettyServer = new NettyServer();
            nettyServer.bind(networkConfig.getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initClient(String host, int port, CallBack callBack) {
        try {
            NettyClient nettyClient = new NettyClient();
            nettyClient.connect(host, port, callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
