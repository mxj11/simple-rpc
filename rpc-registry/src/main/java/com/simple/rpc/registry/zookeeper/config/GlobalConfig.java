package com.simple.rpc.registry.zookeeper.config;

import com.simple.rpc.common.utils.PropertiesUtils;

import java.util.Properties;

public class GlobalConfig {
    private Properties config;

    private ZookeeperConfig zookeeperConfig;

    private GlobalConfig() {
        initZookeeperConfig();
    }

    private void initZookeeperConfig() {
        config = PropertiesUtils.getConfig("/zookeeperConfig.properties");
        zookeeperConfig = new ZookeeperConfig();
        zookeeperConfig.setRetryIntervalTime(Integer.parseInt(getConfig().getProperty("zookeeper.retryIntervalTime")));
        zookeeperConfig.setMaxRetries(Integer.parseInt(getConfig().getProperty("zookeeper.maxRetries")));
        zookeeperConfig.setZookeeperConnectInfo(getConfig().getProperty("zookeeper.zookeeperConnectInfo"));
        zookeeperConfig.setSessionTimeoutMs(Integer.parseInt(getConfig().getProperty("zookeeper.sessionTimeoutMs")));
        zookeeperConfig.setConnectTimeoutMs(Integer.parseInt(getConfig().getProperty("zookeeper.connectTimeoutMs")));
    }

    private static class Holder {
        private static final GlobalConfig INSTANCE = new GlobalConfig();
    }

    public static GlobalConfig getInstance() {
        return Holder.INSTANCE;
    }

    private Properties getConfig() {
        return config;
    }

    public ZookeeperConfig getZookeeperConfig() {
        return zookeeperConfig;
    }
}
