package com.simple.rpc.core.model;

import lombok.Data;

@Data
public class ClientConfig {
    /**
     * 接口Class字节码对象
     */
    private Class clz;

    public ClientConfig(Class clz) {
        this.clz = clz;
    }
}
