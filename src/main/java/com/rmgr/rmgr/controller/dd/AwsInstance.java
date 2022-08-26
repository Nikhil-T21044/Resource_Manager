import java.util.List;

import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.CreateTagsRequest;
import software.amazon.awssdk.services.ec2.model.DescribeInstanceStatusRequest;
import software.amazon.awssdk.services.ec2.model.DescribeInstanceStatusResponse;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesRequest;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;
import software.amazon.awssdk.services.ec2.model.Filter;
import software.amazon.awssdk.services.ec2.model.InstanceStateChange;
import software.amazon.awssdk.services.ec2.model.InstanceStateName;
import software.amazon.awssdk.services.ec2.model.InstanceStatus;
import software.amazon.awssdk.services.ec2.model.InstanceType;
import software.amazon.awssdk.services.ec2.model.RunInstancesRequest;
import software.amazon.awssdk.services.ec2.model.RunInstancesResponse;
import software.amazon.awssdk.services.ec2.model.Tag;
import software.amazon.awssdk.services.ec2.model.TerminateInstancesRequest;
import software.amazon.awssdk.services.ec2.model.TerminateInstancesResponse;



public class AwsInstance extends Instance {

//	enum States{
//		RUNNING, FAILED, TERMINATED
//	}

	String instanceId;
	Ec2Client ec2;
	long startTime;
	String S1;
	AwsInstance(Ec2Client ec2, String name, String amiId ) {
	    RunInstancesRequest runRequest = RunInstancesRequest.builder()
	            .imageId(amiId)
	            .instanceType(InstanceType.T1_MICRO)
	            .maxCount(1)
	            .minCount(1)
	            .build();

	    RunInstancesResponse response = ec2.runInstances(runRequest);
	    String instanceId = response.instances().get(0).instanceId();

	    Tag tag = Tag.builder()
	            .key("Name")
	            .value(name)
	            .build();

	    CreateTagsRequest tagRequest = CreateTagsRequest.builder()
	            .resources(instanceId)
	            .tags(tag)
	            .build();

	    try {
	        ec2.createTags(tagRequest);
	        System.out.printf(
	                "Successfully started EC2 Instance %s based on AMI %s \n",
	                instanceId, amiId);

	        this.instanceId = instanceId;
	        this.ec2 = ec2;
	        this.startTime   = System.currentTimeMillis();
	    } catch (Ec2Exception e) {
	        System.err.println(e.awsErrorDetails().errorMessage());
	        System.exit(1);
	    }
	}


	public void terminate() {

	    try{
	         TerminateInstancesRequest ti = TerminateInstancesRequest.builder()
	             .instanceIds(instanceId)
	             .build();

	         TerminateInstancesResponse response = ec2.terminateInstances(ti);
	         List<InstanceStateChange> list = response.terminatingInstances();
	         for (InstanceStateChange sc : list) {
	            System.out.println("The ID of the terminated instance is " + sc.instanceId());
	         }

	     } catch (Ec2Exception e) {
	         System.err.println(e.awsErrorDetails().errorMessage());
	         System.exit(1);
	     }
	  }

	public boolean isHealthOk() {
		Filter filter = Filter.builder()
                .name("instance-id")
                .values(instanceId)
                .build();
		DescribeInstancesRequest request = DescribeInstancesRequest.builder()
                .filters(filter)
                .build();

        DescribeInstancesResponse response = ec2.describeInstances(request);

        String status =  response.reservations().get(0).instances().get(0).state().name().toString();

        if(status == "pending") { this.startTime = System.currentTimeMillis(); return true; }
        if(status == "running") return true;
		return false;
	}

	String getInstanceType() {
		return "AWS";
	}
}
