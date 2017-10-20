package com.lokia.model;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {

	public Thread newThread(Runnable r) {
		return new Thread(r);
	}

}
