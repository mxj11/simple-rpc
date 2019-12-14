package com.simple.rpc.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());

    private int counter;

    private byte[] req;

    private CallBack callBack;

    public TimeClientHandler(CallBack callBack) {
        this.callBack = callBack;
        req = ("QUERY TIME ORDER" + System.getProperty("line.separator"))
                .getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext context) throws Exception {
        if (callBack != null) {
            callBack.channelActive(context);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object msg) throws Exception {
        if (callBack != null) {
            callBack.channelRead(context, msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) throws Exception {
        // 释放资源
        logger.warning("Unexpected exception from downstream : "
                + cause.getMessage());
        if (callBack != null) {
            callBack.exceptionCaught(context, cause);
        }
        context.close();
    }
}