package com.rmgr.rmgr.controller;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.InstanceType;
import software.amazon.awssdk.services.ec2.model.RunInstancesRequest;
import software.amazon.awssdk.services.ec2.model.RunInstancesResponse;
import software.amazon.awssdk.services.ec2.model.Tag;
import software.amazon.awssdk.services.ec2.model.TerminateInstancesRequest;
import software.amazon.awssdk.services.ec2.model.TerminateInstancesResponse;
import software.amazon.awssdk.services.ec2.model.CreateTagsRequest;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;
import software.amazon.awssdk.services.ec2.model.InstanceStateChange;

public class AwsResource {
	//TODO: changed private to public because of error in ephemeralNode line 36 can't access private error
	public AwsResource(){};
//	private AwsResource(){};

	private static Ec2Client ec2;
	static AwsResource r;
	public static AwsResource getInstance() {
		if(ec2 == null) {
			synchronized(AwsResource.class) {
				if(ec2 == null) {
					r = new AwsResource();
					getClient();
					return r;
				}else return r;
			}
		}
		return r;
	}
	
	private static Ec2Client getClient() {
		AwsBasicCredentials awsCreds = AwsBasicCredentials.create(
				  "ACCESS_KEY",
				  "SECRET_KEY");

	    Region region = Region.US_EAST_1;
	    Ec2Client ec2 = Ec2Client.builder()
	            .region(region)
	            .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
	            .build();
	    
	    return AwsResource.ec2 = ec2;
		
	}
	
	public AwsInstance createInstance() {
		String name = "demo101";
	    String amiId = "ami-0022f774911c1d690";
		return new AwsInstance(ec2, name, amiId);
	}
	
	public void terminateinstance(AwsInstance i) {
		i.terminate();
	}
	
	public void close() {
		ec2.close();
	}
}
