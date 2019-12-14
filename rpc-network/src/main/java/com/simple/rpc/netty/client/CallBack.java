package com.simple.rpc.netty.client;

import io.netty.channel.ChannelHandlerContext;

public interface CallBack {
    void channelActive(ChannelHandlerContext context);

    void channelRead(ChannelHandlerContext context, Object msg);

    void exceptionCaught(ChannelHandlerContext context, Throwable cause);
}
