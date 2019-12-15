package com.simple.rpc.netty.server;

import com.alibaba.fastjson.JSON;
import com.simple.rpc.core.model.RpcRequest;
import com.simple.rpc.core.model.RpcResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext context, Object msg) {
        String body = (String) msg;
        System.out.println("receive msg = " + body);
        RpcRequest rpcRequest = JSON.parseObject(body, RpcRequest.class);
        String interfaceName = rpcRequest.getInterfaceName();
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setCode(200);
        rpcResponse.setMsg("success");
        String json = JSON.toJSONString(rpcResponse);
        json = json + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(json.getBytes());
        context.writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}