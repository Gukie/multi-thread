package com.lokia.pm.bean;

import java.util.concurrent.Semaphore;

public class BoundedBuffer<T> {

	private Semaphore availableItems;
	private Semaphore availableSpaces;
	private T[] items;
	private int putPosition = 0;
	private int takePosition = 0;
	
	public BoundedBuffer(int capacity) {
		availableItems = new Semaphore(0);
		availableSpaces = new Semaphore(capacity);
		items = (T[]) new Object[capacity];
	}
	
	public void put(T item) throws InterruptedException {
//		System.err.println("trying to put:"+item);
		availableSpaces.acquire();
		doInsert(item);
		availableItems.release();
	}

	private synchronized void doInsert(T item) {
		int index = putPosition;
		items[index] = item;
		putPosition = (++index == items.length)?0:index;
	}
	
	public T take() throws InterruptedException {
//		System.err.println("trying to take:");
		availableItems.acquire();
		T item = doExtract();
		availableSpaces.release();
//		System.err.println("succeed:"+item);
		return item;
	}

	private synchronized T doExtract() {
		int index = takePosition;
		T item = items[index];
		items[index] = null;
		index++;
		if(index == items.length){
			takePosition = 0;
		}else{
			takePosition = index;
		}
		return item;
	}
}
