package com.rmgr.rmgr.controller;

public class RealWorker implements WorkerNode {

    //private int workerId;
    public String workerId;
    //public int nuOfResources;
    public Integer Duration;

    AwsInstance AI;


    public RealWorker(AwsInstance AIn) {
        //this.workerId = workerId;
        this.AI = AIn;
        //this.Duration = Duration;
    }

    @Override
    public AwsInstance getInstanceAccess() {

        return AI;
    }

}
