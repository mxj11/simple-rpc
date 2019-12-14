package com.simple.rpc;

import com.simple.rpc.common.utils.CollectionUtils;
import com.simple.rpc.common.utils.IpUtils;
import com.simple.rpc.core.model.ClientConfig;
import com.simple.rpc.core.model.ServiceConfig;
import com.simple.rpc.core.model.ServiceRegistryInfo;
import com.simple.rpc.netty.client.CallBack;
import com.simple.rpc.network.NetworkManager;
import com.simple.rpc.registry.RegisterManager;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

@Data
public class ApplicationContext<T> {
    private ServiceConfig serviceConfig;

    private ClientConfig clientConfig;

    public ApplicationContext(String registerUrl, ServiceConfig serviceConfig, ClientConfig clientConfig) {
        this.serviceConfig = serviceConfig;
        this.clientConfig = clientConfig;
        if (serviceConfig != null) {
            // 服务注册
            RegisterManager.getInstance().register(registerUrl, getServiceRegistryInfo(serviceConfig));
        }

        // 获取服务注册列表
        List<ServiceRegistryInfo> serviceRegistryInfoList = RegisterManager.getInstance().discover(registerUrl);
        System.out.println("|discovery service|service info list|" + serviceRegistryInfoList);

        if (clientConfig != null) {
            if (!CollectionUtils.isEmpty(serviceRegistryInfoList)) {
                NetworkManager.getInstance().initClient(serviceRegistryInfoList.get(0).getHost(), 8850, new CallBack() {
                    public void channelActive(ChannelHandlerContext context) {
                        byte[] message = ("hello" + System.getProperty("line.separator")).getBytes();
                        ByteBuf buffer = Unpooled.buffer(message.length);
                        buffer.writeBytes(message);
                        context.writeAndFlush(buffer);
                    }

                    public void channelRead(ChannelHandlerContext context, Object msg) {
                        System.out.println("msg = " + (String) msg);
                    }

                    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {

                    }
                });
            }
        }

        if (serviceConfig != null) {
            // 底层网络框架服务端初始化
            NetworkManager.getInstance().initServer(serviceConfig.getNetworkConfig());
        }
    }

    public T getService(Class clazz) {
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            public T invoke(Object proxy, Method method, Object[] args) throws Throwable {

                return null;
            }
        });
    }

    private ServiceRegistryInfo getServiceRegistryInfo(ServiceConfig serviceConfig) {
        ServiceRegistryInfo serviceRegistryInfo = new ServiceRegistryInfo();
        Class clz = serviceConfig.getClz();
        String interfaceName = clz.getName();
        serviceRegistryInfo.setInterfaceName(interfaceName);
        List<String> methods = new ArrayList<String>();
        Method[] declaredMethods = clz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            methods.add(method.getName());
        }
        serviceRegistryInfo.setMethods(methods);
        serviceRegistryInfo.setHost(IpUtils.getHostIp());
        // TODO
        Object service = serviceConfig.getService();
        return serviceRegistryInfo;
    }
}
