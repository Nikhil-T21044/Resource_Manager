package com.rmgr.rmgr.controller;

public class ProxyWorker implements WorkerNode {

//    public enum States{
//        RUNNING, FAILED, TERMINATED
//    }


    private int workerID;

    private RealWorker realaccess;
    public Integer Duration;

    AwsInstance AI;


    public ProxyWorker(Integer Duration , AwsInstance AI) {
        this.Duration = Duration;
        this.AI = AI;
        //this.workerID = workerID;
    }


    @Override
    public AwsInstance getInstanceAccess() {
       /* if (getRole(employeeName) > 4) {
            realaccess = new RealInternetAccess(employeeName);
            realaccess.grantInternetAccess();
        } else {
            System.out.println("No Internet access granted. Your job level is below 5");
        }*/

        realaccess = new RealWorker(AI);

        boolean flag = true;

        while (System.currentTimeMillis() < (realaccess.Duration * 1000 + realaccess.AI.startTime))
        {
            if(this.checkHealth())
            {
                continue;
            }
            else
            {
                flag = false;
                break;
            }
        }


        if(!flag)
        {
            //return FAILED;
            AI.S1 = "FAILURE";
        }
        else
        {
            AI.S1 = "TERMINATED";
        }
        //realaccess.getInstanceAccess();

        return AI;

    }


    public boolean checkHealth() {

        return AI.isHealthOk();


        }



    public void KeepRunning() {

    }

    public int checkFailure() {
        return 1;
    }

}
