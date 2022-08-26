package com.rmgr.rmgr.controller;
public class JobManager implements Runnable {
	String name;
	String duration;
	String numofresources;
	JobManager(String name, String duration, String numofresources) throws Exception {
		this.name = name;
		this.duration = duration;
		this.numofresources = numofresources;
		if(name.equals("sri")){
			throw new Exception("badname");
		}
		System.out.println("printing " + name+duration+numofresources);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t=new Thread();
		t.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		ephemeralNode en=new ephemeralNode("Description", 10, 100);
		ephemeralNode en=new ephemeralNode("Description", 10, 100);
		en.startJob();
	}

}
