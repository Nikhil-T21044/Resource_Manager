package com.rmgr.rmgr.controller;

//enum States{
//	RUNNING, FAILED, TERMINATED
//}
public class Node {
	private AwsInstance inst;
	private ProxyWorkerClient realworker;
//    States S1;

	//Constructor
	public Node(AwsInstance inst, ProxyWorkerClient realworker) {

		this.inst = inst;
		this.realworker = realworker;
		//sit = new StateofInstance();
	}

	// Getter and Setter
	public AwsInstance getInst() {
		return inst;
	}

	public void setInst(AwsInstance inst) {
		this.inst = inst;
	}

	public ProxyWorkerClient getRealworker() {
		return realworker;
	}

	public void setRealworker(ProxyWorkerClient realworker) {
		this.realworker = realworker;
	}


	// Destroy instance method
	void destroyInstance(AwsInstance instance)
	{
		instance.terminate();
	}

}
