package com.leonardo.rocha.zookeeperclient.controller;


import com.leonardo.rocha.zookeeperclient.cluster.management.ServiceRegistry;
import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/")
public class ZooKeeperClientController {
    private static final Logger logger = LoggerFactory.getLogger(ZooKeeperClientController.class);

    @Autowired
    ServiceRegistry serviceRegistry;

    @RequestMapping(value = "addresses", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAddresses() throws InterruptedException, KeeperException {
        logger.info("Getting Addresses");
        return new ResponseEntity<>(serviceRegistry.getAllServiceAddresses(), HttpStatus.OK);
    }

}
