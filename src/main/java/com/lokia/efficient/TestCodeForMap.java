package com.lokia.efficient;

import java.util.Map;
import java.util.Random;

public class TestCodeForMap {

	public void test(int threadNum,final int roundNum, final Map<Integer,Integer> map) {
		final Random random = new Random();
		for(int i = 0;i<threadNum;i++) {
			Thread thread = new Thread(new Runnable() {
				
				public void run() {
					for(int round = 0;round<roundNum;round++) {
						Integer key = random.nextInt(190099);
						Integer value =  map.get(key);
						if(value==null) {
							// 60% possibility to put to map
							if(isWithinPossibility(60)) {
								map.put(key, value);
							}
						}else {
							// 2% possibility to remote it from map
							if(isWithinPossibility(2)) {
								map.remove(key);
							}
						}
					}
				}
			});
			
			thread.start();
		}
	}

	protected boolean isWithinPossibility(int range) {
		Random random = new Random();
		int seed = random.nextInt(1000000);
		int mod = seed % 100;
		if(mod<=range) {
			return true;
		}
		return false;
	}
	
	
}
