package com.rmgr.rmgr.controller;

public interface WorkerNode {

    String workerId = null;
    //public int nuOfResources;
    Integer duration = null;

    public AwsInstance getInstanceAccess();

}
