package com.simple.rpc.registry.zookeeper.curator;


import com.simple.rpc.registry.zookeeper.config.GlobalConfig;
import com.simple.rpc.registry.zookeeper.config.ZookeeperConfig;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.text.MessageFormat;
import java.util.List;

public class ZookeeperService implements ZookeeperOperator {
    private Logger logger = LogManager.getLogger(ZookeeperService.class);

    //创建连接实例
    private CuratorFramework client = null;

    public ZookeeperService(String registerUrl) {
        ZookeeperConfig zookeeperConfig = GlobalConfig.getInstance().getZookeeperConfig();
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(zookeeperConfig.getRetryIntervalTime(),
                zookeeperConfig.getMaxRetries());
        client = CuratorFrameworkFactory.builder()
                .connectString(registerUrl)
                .sessionTimeoutMs(zookeeperConfig.getSessionTimeoutMs())
                .connectionTimeoutMs(zookeeperConfig.getConnectTimeoutMs())
                .retryPolicy(retryPolicy)
                .build();
        client.start();
    }


    @Override
    public CuratorFramework getClient() {
        return client;
    }

    @Override
    public String createPersistentNode(String nodePath, String nodeValue) {
        try {
            return getClient().create().creatingParentsIfNeeded()
                    .forPath(nodePath, nodeValue.getBytes());
        } catch (Exception e) {
            logger.error(MessageFormat.format("创建永久Zookeeper节点失败,nodePath:{0},nodeValue:{1}", nodePath, nodeValue), e);
            return null;
        }
    }

    @Override
    public String createPersistentNode(String nodePath) {
        try {
            return getClient().create().creatingParentsIfNeeded()
                    .forPath(nodePath);
        } catch (Exception e) {
            logger.error(MessageFormat.format("创建永久Zookeeper节点失败,nodePath:{0}", nodePath), e);
            return null;
        }
    }

    @Override
    public String createSequentialPersistentNode(String nodePath, String nodeValue) {
        try {
            return getClient().create().creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    .forPath(nodePath, nodeValue.getBytes());
        } catch (Exception e) {
            logger.error(MessageFormat.format("创建永久有序Zookeeper节点失败,nodePath:{0},nodeValue:{1}", nodePath, nodeValue), e);
            return null;
        }
    }

    @Override
    public String createSequentialPersistentNode(String nodePath) {
        try {
            return getClient().create().creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    .forPath(nodePath);
        } catch (Exception e) {
            logger.error(MessageFormat.format("创建永久有序Zookeeper节点失败,nodePath:{0}", nodePath), e);
            return null;
        }
    }

    @Override
    public String createEphemeralNode(String nodePath, String nodeValue) {
        try {
            return getClient().create().creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(nodePath, nodeValue.getBytes("utf-8"));
        } catch (Exception e) {
            logger.error(MessageFormat.format("创建临时Zookeeper节点失败,nodePath:{0},nodeValue:{1}", nodePath, nodeValue), e);
            return null;
        }
    }

    @Override
    public String createEphemeralNode(String nodePath) {
        try {
            return getClient().create().creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(nodePath);
        } catch (Exception e) {
            logger.error(MessageFormat.format("创建临时Zookeeper节点失败,nodePath:{0}", nodePath), e);
            return null;
        }
    }

    @Override
    public String createSequentialEphemeralNode(String nodePath, String nodeValue) {
        try {
            return getClient().create().creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(nodePath, nodeValue.getBytes());
        } catch (Exception e) {
            logger.error(MessageFormat.format("创建临时有序Zookeeper节点失败,nodePath:{0},nodeValue:{1}", nodePath, nodeValue), e);
            return null;
        }
    }

    @Override
    public String createSequentialEphemeralNode(String nodePath) {
        try {
            return getClient().create().creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(nodePath);
        } catch (Exception e) {
            logger.error(MessageFormat.format("创建临时有序Zookeeper节点失败,nodePath:{0},cause:{1}", nodePath), e);
            return null;
        }
    }

    @Override
    public boolean checkExists(String nodePath) {
        try {
            Stat stat = getClient().checkExists().forPath(nodePath);

            return stat != null;
        } catch (Exception e) {
            logger.error(MessageFormat.format("检查Zookeeper节点是否存在出现异常,nodePath:{0}", nodePath), e);
        }
        return false;
    }

    @Override
    public List<String> getChildren(String nodePath) {
        try {
            return getClient().getChildren().forPath(nodePath);
        } catch (Exception e) {
            logger.error(MessageFormat.format("获取某个Zookeeper节点的所有子节点出现异常,nodePath:{0}", nodePath), e);
        }
        return null;
    }

    @Override
    public String getData(String nodePath) {
        try {
            return new String(getClient().getData().forPath(nodePath));
        } catch (Exception e) {
            logger.error(MessageFormat.format("获取某个Zookeeper节点的数据出现异常,nodePath:{0}", nodePath), e);
        }
        return null;
    }

    @Override
    public void setData(String nodePath, String newNodeValue) {
        try {
            getClient().setData().forPath(nodePath, newNodeValue.getBytes());
        } catch (Exception e) {
            logger.error(MessageFormat.format("设置某个Zookeeper节点的数据出现异常,nodePath:{0}", nodePath), e);
        }
    }

    @Override
    public void delete(String nodePath) {
        try {
            getClient().delete().guaranteed().forPath(nodePath);
        } catch (Exception e) {
            logger.error(MessageFormat.format("删除某个Zookeeper节点出现异常,nodePath:{0}", nodePath), e);
        }
    }

    @Override
    public void deleteChildrenIfNeeded(String nodePath) {
        try {
            getClient().delete().guaranteed().deletingChildrenIfNeeded().forPath(nodePath);
        } catch (Exception e) {
            logger.error(MessageFormat.format("级联删除某个Zookeeper节点及其子节点出现异常,nodePath:{0}", nodePath), e);
        }
    }

    @Override
    public NodeCache registerNodeCacheListener(String nodePath) {
        try {
            //1. 创建一个NodeCache
            NodeCache nodeCache = new NodeCache(getClient(), nodePath);

            //2. 添加节点监听器
            nodeCache.getListenable().addListener(() -> {
                ChildData childData = nodeCache.getCurrentData();
                if (childData != null) {
                    System.out.println("Path: " + childData.getPath());
                    System.out.println("Stat:" + childData.getStat());
                    System.out.println("Data: " + new String(childData.getData()));
                }
            });

            //3. 启动监听器
            nodeCache.start();

            //4. 返回NodeCache
            return nodeCache;
        } catch (Exception e) {
            logger.error(MessageFormat.format("注册节点监听器出现异常,nodePath:{0}", nodePath), e);
        }
        return null;
    }

    @Override
    public PathChildrenCache registerPathChildListener(String nodePath, PathChildrenCacheListener listener) {
        try {
            //1. 创建一个PathChildrenCache
            PathChildrenCache pathChildrenCache = new PathChildrenCache(getClient(), nodePath, true);

            //2. 添加目录监听器
            pathChildrenCache.getListenable().addListener(listener);

            //3. 启动监听器
            pathChildrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);

            //4. 返回PathChildrenCache
            return pathChildrenCache;
        } catch (Exception e) {
            logger.error(MessageFormat.format("注册子目录监听器出现异常,nodePath:{0}", nodePath), e);
        }
        return null;
    }

    @Override
    public TreeCache registerTreeCacheListener(String nodePath, int maxDepth, TreeCacheListener listener) {
        try {
            //1. 创建一个TreeCache
            TreeCache treeCache = TreeCache.newBuilder(getClient(), nodePath)
                    .setCacheData(true)
                    .setMaxDepth(maxDepth)
                    .build();

            //2. 添加目录监听器
            treeCache.getListenable().addListener(listener);

            //3. 启动监听器
            treeCache.start();

            //4. 返回TreeCache
            return treeCache;
        } catch (Exception e) {
            logger.error(MessageFormat.format("注册目录监听器出现异常,nodePath:{0},maxDepth:{1}", nodePath), e);
        }
        return null;
    }
}