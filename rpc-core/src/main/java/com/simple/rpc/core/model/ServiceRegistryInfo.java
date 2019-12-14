package com.simple.rpc.core.model;

import lombok.Data;

import java.util.List;

@Data
public class ServiceRegistryInfo {
    private String serviceName;

    private String interfaceName;

    private List<String> methods;

    private String host;

    private int port;
}
