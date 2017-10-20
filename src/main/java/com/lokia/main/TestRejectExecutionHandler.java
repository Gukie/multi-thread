package com.lokia.main;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestRejectExecutionHandler {

	public static void main(String[] args) {
		
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 2, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>() );
		poolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

		
	}

}
