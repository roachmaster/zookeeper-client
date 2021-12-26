package com.leonardo.rocha.zookeeperclient.cluster.management;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ZooKeeperFactory {
    @Value("${zookeeper.address}")
    private String zkAddress;

    @Value("${zookeeper.port}")
    private int zkPort;

    @Value("${zookeeper.sessionTimeout}")
    private int zkTimeout;

    public ZooKeeper create(Watcher watcher) throws IOException {
        return new ZooKeeper(zkAddress + ":" + zkPort, zkTimeout, watcher);
    }
}
