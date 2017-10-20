package com.lokia.main;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestDeadLock {
	
	private ExecutorService service = Executors.newSingleThreadExecutor();
	
	class Render implements Callable<String>{

		public String call() throws Exception {
			
			Future<String> one, two;
			one = service.submit(new Cmd("one"));
			two = service.submit(new Cmd("two"));
			
			return one.get()+two.get()
			;
		}
		
	}
	
	
	
	class Cmd implements Callable<String>{

		private String name;
		public Cmd(String name ) {
			this.name  = name;
		}
		public String call() throws Exception {
			return this.name;
		}
		
	}

	public static void main(String[] args) {
		TestDeadLock testDeadLock = new TestDeadLock();
		Render render = testDeadLock.new Render();
		Future<String> result = testDeadLock.service.submit(render);
		
		try {
			System.out.println(result.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
