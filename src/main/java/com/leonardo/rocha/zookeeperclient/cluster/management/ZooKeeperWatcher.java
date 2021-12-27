package com.leonardo.rocha.zookeeperclient.cluster.management;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("zooKeeperWatcher")
public class ZooKeeperWatcher implements Watcher {
    private static final Logger LOG = LoggerFactory
            .getLogger(ZooKeeperWatcher.class);

    private final ZooKeeper zooKeeper;

    public ZooKeeperWatcher(ZooKeeperFactory zooKeeperFactory) throws IOException {
        zooKeeper = zooKeeperFactory.create(this);
    }

    public ZooKeeper getZooKeeper(){
        return this.zooKeeper;
    }

    public void run() throws InterruptedException {
        synchronized (zooKeeper) {
            zooKeeper.wait(10000);
        }
    }

    public void close() throws InterruptedException {
        zooKeeper.close();
    }

    @Override
    public void process(WatchedEvent event) {
        switch (event.getType()) {
            case None:
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    LOG.info("Successfully connected to Zookeeper");
                } else {
                    synchronized (zooKeeper) {
                        LOG.info("Disconnected from Zookeeper event");
                        zooKeeper.notifyAll();
                    }
                }
        }
    }
}