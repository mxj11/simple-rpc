package com.simple.rpc.core.model;

import lombok.Data;

@Data
public class RpcRequest {
    private String interfaceName;

    private String methodName;
}
