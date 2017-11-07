package com.lokia.utils;

public class RandomUtils {

	public static int generateObjectRandom(Object object) {
		int hashCode = 0;
		if(object != null){
			hashCode = object.hashCode();
		}
		int seed = hashCode ^ (int)System.nanoTime();
		return xorShift(seed);
	}
	
	public static int generateFakeRandom(int seed) {
		return xorShift(seed);
	}
	
	private static int xorShift(int seed) {
		seed ^= (seed << 6);
		seed ^= (seed >> 21);
		seed ^= (seed << 7);
		return seed;
	}
	
}
