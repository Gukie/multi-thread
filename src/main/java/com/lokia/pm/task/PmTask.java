package com.lokia.pm.task;
import java.util.Queue;

/**
 * @author gushu
 * @date 2017/10/30
 */
public class PmTask implements Runnable{

	private Queue<String> queue;
	
	@Override
	public void run() {
		while(true){
			String item = queue.poll();
//			if(queue.isEmpty()){
//				queue.offer(item);
//			}
			if(item != null && item.length()!=0){
				queue.offer(item);
			}
		}
	}

	public Queue<String> getQueue() {
		return queue;
	}


	public void setQueue(Queue<String> queue) {
		this.queue = queue;
	}

}
