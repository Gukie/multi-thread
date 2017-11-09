package com.lokia.pm.main;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import com.lokia.pm.bean.BoundedBuffer;
import com.lokia.utils.RandomUtils;

public class PutTakeTest {

	private ExecutorService executorService = Executors.newCachedThreadPool();
	private CyclicBarrier barrier;
	private int pairNum;
	private int trialNum;
	private int bufferCapacity;
	private BoundedBuffer<Integer> buffer;
	
	private AtomicInteger putSum = new AtomicInteger(0);
	private AtomicInteger takeSum = new AtomicInteger(0);

	public static void main(String[] args) {

		int pairs = 77;
		int trials = 154855;
		int capacity = 2;
		PutTakeTest putTakeTest = new PutTakeTest(pairs, trials, capacity);
		long start = System.currentTimeMillis();
		putTakeTest.doWork();
		long end = System.currentTimeMillis();
		System.out.println("time consumed:"+(end-start));
		putTakeTest.assertResult();
	}

	public PutTakeTest(int pairs, int trials,int capacity){
		pairNum = pairs;
		trialNum = trials;
		bufferCapacity = capacity;
		barrier = new CyclicBarrier(pairNum * 2+1);
		buffer = new BoundedBuffer<>(bufferCapacity);
	}

	public void doWork() {
		try {
			for (int i = 0; i < pairNum; i++) {
				executorService.execute(new Consumer());
				executorService.execute(new Producer());
			}
			System.err.println("starting awaiting");
			barrier.await(); // wait all threads ready.
			barrier.await(); // wait all threads finished. 
			System.err.println("finished awaiting");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			executorService.shutdownNow();
		}
	}
	
	class Consumer implements Runnable {
		@Override
		public void run() {
			try {
				int sum =0;
				barrier.await(); // TODO unsure
				for(int i =0;i<trialNum;i++){
					int seed = buffer.take();
					sum+=seed;
				}
				takeSum.getAndAdd(sum);
				barrier.await(); // TODO unsure
//				System.err.println("consumed:"+sum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	class Producer implements Runnable {

		@Override
		public void run() {
			try {
				int sum = 0;
				barrier.await(); // TODO unsure
				int seed = (int) (this.hashCode() ^ System.nanoTime());
				for (int i = 0; i < trialNum; i++) {
					buffer.put(seed);
					sum += seed;
					seed = RandomUtils.generateFakeRandom(seed);
				}
				putSum.getAndAdd(sum);
				barrier.await(); // TODO unsure
//				System.err.println("produced:"+sum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public void assertResult() {
		if(putSum.get() == takeSum.get()){
			System.out.println("finished: consumed item equals to produced ones");
			return ;
		}
		throw new RuntimeException("consumed item not equals to produced ones");
	}
}
