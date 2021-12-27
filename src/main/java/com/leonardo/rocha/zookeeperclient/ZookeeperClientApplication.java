package com.leonardo.rocha.zookeeperclient;

import com.leonardo.rocha.zookeeperclient.cluster.management.LeaderElection;
import com.leonardo.rocha.zookeeperclient.cluster.management.ZooKeeperWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZookeeperClientApplication implements CommandLineRunner {
	private static final Logger LOG = LoggerFactory
			.getLogger(ZookeeperClientApplication.class);

	@Autowired
	private LeaderElection leaderElection;

	@Autowired
	private ZooKeeperWatcher zooKeeperWatcher;

	public static void main(String[] args) {
		LOG.info("STARTING THE APPLICATION");
		SpringApplication.run(ZookeeperClientApplication.class, args);
		LOG.info("APPLICATION FINISHED");
	}

	@Override
	public void run(String... args) throws Exception {
		leaderElection.volunteerForLeadership();
		leaderElection.reelectLeader();
	}
}
