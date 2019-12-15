package com.simple.rpc.core.model;

import lombok.Data;

@Data
public class RpcResponse {
    private int code;

    private String msg;
}
