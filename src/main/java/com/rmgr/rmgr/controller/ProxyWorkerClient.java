package com.rmgr.rmgr.controller;

public class ProxyWorkerClient {

    AwsInstance instance;
    Integer Duration;

    ProxyWorkerClient(AwsInstance instance,Integer Duration)
    {
        this.instance = instance;
        this.Duration = Duration;

    }
    void doJob() {
        WorkerNode access = new ProxyWorker(Duration, instance);
        instance = access.getInstanceAccess();

    }


}
