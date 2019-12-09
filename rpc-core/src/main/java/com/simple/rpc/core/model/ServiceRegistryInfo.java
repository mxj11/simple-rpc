package com.simple.rpc.core.model;

import lombok.Data;

@Data
public class ServiceRegistryInfo {
    private String serviceName;

    private String interfaceName;

    private String methodName;

    private Object[] parameters;

    private String host;

    private int port;
}
