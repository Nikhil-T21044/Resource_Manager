import java.util.ArrayList;
import java.util.List;

public class ephemeralNode {
	private String Description;
	private Integer numResources;
	private Integer duration;
	private List<Node> resourceList;

	// Construction creating the ephemeral node
	public ephemeralNode(String description, Integer numResources, Integer duration) {
		Description = description;
		this.numResources = numResources;
		this.duration = duration;
		resourceList=new ArrayList<Node>();
	}
	// creating the job according to number of resources
	void startJob()
	{
		for(int i=0;i<numResources;i++)
		{

			resourceList.add(createSingleJob());

		}
	this.RecoverInstance();

	}


	// creating the single job
	Node createSingleJob()
	{
		AwsResource aws=new AwsResource();
		AwsInstance instance=aws.createInstance();
		instance.S1 = "RUNNING";

		//RealWorker realWorker = new RealWorker(instance);
		ProxyWorkerClient proxyworker=new ProxyWorkerClient(instance,duration);
		Node node=new Node(instance,proxyworker);
//
//		if((AI.S1) == "FAILURE")
//		{
//			this.recoverInstance();
//		}
		return node;
	}
	// recover the instance by using proxyworker
	void RecoverInstance()
	{
		for(int i=0;i<numResources;i++)
		{
			ProxyWorker realWorker=resourceList.get(i).getRealworker();
			resourceList.get(i).
			if(realWorker.AI.S1 == "FAILURE")
			{
				resourceList.set(i, createSingleJob());
			}

		}
	}



}
