package com.lokia.main;

import java.util.concurrent.Executors;

public class TestMain {

	
	public static void main(String[] args) throws InterruptedException {
		
		
		Executors.newCachedThreadPool();
		
//		while(true) {
//			if(Thread.currentThread().isInterrupted()) {
//				throw new InterruptedException();
//			}
//			
//			for(int i =0;i<4;i++) {
//				
//				NoVisibilityRunner target = new NoVisibilityRunner();
//				Thread reader = new Thread(target, "no-visibility-"+i);
//				reader.start();
//				
//				target.setNumber(20);
//				target.setReadable(true);
//				
//				
//			}
//		}
		
		
		Object object = new Object();
		runData(object);
		
		while(true) {
			if(Thread.currentThread().isInterrupted()) {
				break;
			}
			Thread.sleep(1);
		}
		
		System.err.println("lll");
		
	}

	private static void runData(Object object) throws InterruptedException {
		synchronized (object) {
		
			while(true) {
				object.wait();
			}
		}
	}

}
