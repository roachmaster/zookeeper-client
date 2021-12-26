package com.leonardo.rocha.zookeeperclient.cluster.management;

import org.apache.zookeeper.data.Stat;

import java.util.Objects;

public class ReelectData {
    private boolean isLeader;
    private Stat predecessorStat;

    public ReelectData() {
        this.isLeader = false;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public void setLeader(boolean leader) {
        isLeader = leader;
    }

    public Stat getPredecessorStat() {
        return predecessorStat;
    }

    public void setPredecessorStat(Stat predecessorStat) {
        this.predecessorStat = predecessorStat;
    }

    public boolean reelect(){
        return Objects.isNull(predecessorStat) && !isLeader;
    }
}
