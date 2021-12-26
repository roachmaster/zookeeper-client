package com.leonardo.rocha.zookeeperclient.cluster.management.config;

import com.leonardo.rocha.zookeeperclient.cluster.management.OnElectionAction;
import com.leonardo.rocha.zookeeperclient.cluster.management.OnElectionCallback;
import com.leonardo.rocha.zookeeperclient.cluster.management.ServiceRegistry;
import com.leonardo.rocha.zookeeperclient.cluster.management.ZooKeeperWatcher;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZookeeperConfig {
    @Value("${server.port}")
    private int serverPort;

    @Bean
    public Object zooKeeperMonitor(){
        return new Object();
    }

    @Bean
    public ZooKeeper zooKeeper(@Qualifier("zooKeeperWatcher") ZooKeeperWatcher zooKeeperWatcher){
        return zooKeeperWatcher.getZooKeeper();
    }

    @Bean
    public OnElectionCallback onElectionCallback(ServiceRegistry serviceRegistry){
        return new OnElectionAction(serviceRegistry, serverPort);
    }
}
