package com.simple.rpc.registry.zookeeper.config;


import lombok.Data;

@Data
public class ZookeeperConfig {
    private int retryIntervalTime;

    private int maxRetries;

    private String zookeeperConnectInfo;

    private int sessionTimeoutMs;

    private int connectTimeoutMs;
}
