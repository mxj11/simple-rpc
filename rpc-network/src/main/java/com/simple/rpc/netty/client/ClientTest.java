package com.simple.rpc.netty.client;

public class ClientTest {
    public static void main(String[] args) throws InterruptedException {
        int port = 5000;
        new NettyClient().connect(port, "localhost");
    }
}
