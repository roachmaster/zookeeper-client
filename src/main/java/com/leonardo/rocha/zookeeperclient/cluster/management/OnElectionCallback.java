package com.leonardo.rocha.zookeeperclient.cluster.management;

public interface OnElectionCallback {

    void onElectedToBeLeader();

    void onWorker();
}