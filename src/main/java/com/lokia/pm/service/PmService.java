package com.lokia.pm.service;

import java.util.Queue;
import java.util.Random;

import com.lokia.pm.task.PmTask;

/**
 * @author gushu
 * @date 2017/10/30
 */
public class PmService {

	private Queue<String>  queue ;
	
	
	public void test(int threadNum){
		
		initData();
		for(int i = 0;i<threadNum;i++){
			PmTask pmTask = new PmTask();
			pmTask.setQueue(queue);
			Thread thread = new Thread(pmTask);
			thread.start();
		}
	}

	private void initData() {
		Random random = new Random();
		for(int index = 0;index< 50;index++){
			queue.offer(random.nextInt(3000147)+"");
		}
	}

	public Queue<String> getQueue() {
		return queue;
	}

	public void setQueue(Queue<String> queue) {
		this.queue = queue;
	}
}
