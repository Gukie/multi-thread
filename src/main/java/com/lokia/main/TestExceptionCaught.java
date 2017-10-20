package com.lokia.main;

public class TestExceptionCaught {

	public static void main(String[] args) {
	
		String var = null;
		testExceptionCaught(var);
	}

	private static void testExceptionCaught(String var) {
		
		try {
			var.lastIndexOf(1);
		} 
		catch (Throwable t) {
			System.err.println(t.getCause());
		}
		finally {
			System.out.println("finally");
		}
		
	}

}
