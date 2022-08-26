public class ProxyWorkerClient {

    Awsinstance instance;
    Integer Duration;

    ProxyWorkerClient(Awsinstance instance,Integer Duration)
    {
        this.instance = instance;
        this.Duration = Duration;

    }
    void doJob(S) {
        WorkerNode access = new ProxyWorker(instance,Duration);
        access.getInstanceAccess()

    }


}
