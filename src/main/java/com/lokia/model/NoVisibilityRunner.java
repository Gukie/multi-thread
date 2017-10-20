package com.lokia.model;

public class NoVisibilityRunner implements Runnable {

	
	public  boolean isReadable;
	
	public  int number;
	
	public void run() {
		while(!isReadable) {
			Thread.yield();
		}
//		if(number!=20) {
			System.out.println(Thread.currentThread().getName()+"-number:"+number);
//		}
	}

	public boolean isReadable() {
		return isReadable;
	}

	public void setReadable(boolean isReadable) {
		this.isReadable = isReadable;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
}
