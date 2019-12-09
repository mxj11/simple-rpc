package com.simple.rpc.registry.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;

import java.util.List;

public interface ZookeeperOperator {
    /**
     * 获取Zookeeper连接客户端
     *
     * @return CuratorFramework实例
     */
    CuratorFramework getClient();

    /**
     * 创建永久Zookeeper节点
     *
     * @param nodePath  节点路径（如果父节点不存在则会自动创建父节点），如：/curator
     * @param nodeValue 节点数据
     * @return 返回创建成功的节点路径
     */
    String createPersistentNode(String nodePath, String nodeValue);

    /**
     * 创建永久Zookeeper节点
     *
     * @param nodePath  节点路径如：/curator
     * @return 返回创建成功的节点路径
     */
    String createPersistentNode(String nodePath);

    /**
     * 创建永久有序Zookeeper节点
     *
     * @param nodePath  节点路径（如果父节点不存在则会自动创建父节点），如：/curator
     * @param nodeValue 节点数据
     * @return 返回创建成功的节点路径
     */
    String createSequentialPersistentNode(String nodePath, String nodeValue);

    /**
     * 创建永久有序Zookeeper节点
     *
     * @param nodePath  节点路径（如果父节点不存在则会自动创建父节点），如：/curator
     * @return 返回创建成功的节点路径
     */
    String createSequentialPersistentNode(String nodePath);

    /**
     * 创建临时Zookeeper节点
     *
     * @param nodePath  节点路径（如果父节点不存在则会自动创建父节点），如：/curator
     * @param nodeValue 节点数据
     * @return 返回创建成功的节点路径
     */
    String createEphemeralNode(String nodePath, String nodeValue);

    /**
     * 创建临时Zookeeper节点
     *
     * @param nodePath  节点路径（如果父节点不存在则会自动创建父节点），如：/curator
     * @return 返回创建成功的节点路径
     */
    String createEphemeralNode(String nodePath);

    /**
     * 创建临时有序Zookeeper节点
     *
     * @param nodePath  节点路径（如果父节点不存在则会自动创建父节点），如：/curator
     * @param nodeValue 节点数据
     * @return 返回创建成功的节点路径
     */
    String createSequentialEphemeralNode(String nodePath, String nodeValue);

    /**
     * 创建临时有序Zookeeper节点
     *
     * @param nodePath  节点路径（如果父节点不存在则会自动创建父节点），如：/curator
     * @return 返回创建成功的节点路径
     */
    String createSequentialEphemeralNode(String nodePath);

    /**
     * 检查Zookeeper节点是否存在
     *
     * @param nodePath 节点路径
     * @return 如果存在则返回true
     */
    boolean checkExists(String nodePath);

    /**
     * 获取某个Zookeeper节点的所有子节点
     *
     * @param nodePath 节点路径
     * @return 返回所有子节点的节点名
     */
    List<String> getChildren(String nodePath);

    /**
     * 获取某个Zookeeper节点的数据
     *
     * @param nodePath 节点路径
     * @return 某个节点对应的数据
     */
    String getData(String nodePath);

    /**
     * 设置某个Zookeeper节点的数据
     *
     * @param nodePath     节点路径
     * @param newNodeValue 节点新值
     */
    void setData(String nodePath, String newNodeValue);

    /**
     * 删除某个Zookeeper节点
     *
     * @param nodePath 节点路径
     */
    void delete(String nodePath);

    /**
     * 级联删除某个Zookeeper节点及其子节点
     *
     * @param nodePath 节点路径
     */
    void deleteChildrenIfNeeded(String nodePath);

    /**
     * 注册节点监听器
     * NodeCache: 对一个节点进行监听，监听事件包括指定路径的增删改操作
     *
     * @param nodePath 节点路径
     * @return NodeCache实例
     */
    NodeCache registerNodeCacheListener(String nodePath);


    /**
     * 注册子目录监听器
     * PathChildrenCache：对指定路径节点的一级子目录监听，不对该节点的操作监听，对其子目录的增删改操作监听
     *
     * @param nodePath 节点路径
     * @param listener 监控事件的回调接口
     * @return PathChildrenCache实例
     */
    PathChildrenCache registerPathChildListener(String nodePath, PathChildrenCacheListener listener);

    /**
     * 注册目录监听器
     * TreeCache：综合NodeCache和PathChildrenCahce的特性，可以对整个目录进行监听，同时还可以设置监听深度
     *
     * @param nodePath 节点路径
     * @param maxDepth 自定义监控深度
     * @param listener 监控事件的回调接口
     * @return TreeCache实例
     */
    TreeCache registerTreeCacheListener(String nodePath, int maxDepth, TreeCacheListener listener);
}
