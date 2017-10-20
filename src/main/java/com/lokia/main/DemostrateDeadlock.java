package com.lokia.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.lokia.model.Account;

public class DemostrateDeadlock {
	
	static int account_num = 5;
	Random random = new Random();
	static List<Account> accounts = new ArrayList<Account>();
	
	Object tieLock = new Object();

	public static void main(String[] args) {
		
		for(int i =0 ;i<account_num;i++) {
			accounts.add(new Account());
		}
		DemostrateDeadlock deadlock = new DemostrateDeadlock();
		for(int i = 0;i<6;i++) {
			Thread thread = new Thread(deadlock.new TransferThread());
			thread.start();
		}
	}
	
	class TransferThread implements Runnable{

		public void run() {
			for(int i = 0;i<100000;i++) {
				Account from = accounts.get(random.nextInt(account_num));
				Account to = accounts.get(random.nextInt(account_num));
				int amouont = random.nextInt(10000);
//				transferMoney_non_deadlokc(from,to,amouont);
				transferMoney(from,to,amouont);
			}
			
		}
		
	}

	public void transferMoney_non_deadlokc(Account from, Account to, int amouont) {
		
		int fromHash = System.identityHashCode(from);
		int toHash = System.identityHashCode(to);
		
		if(fromHash < toHash) {
			synchronized (from) {
				synchronized (to) {
					System.out.println("from debit to: "+amouont);
				}
			}
		}else if(fromHash > toHash) {
			synchronized (to) {
				synchronized (from) {
					System.out.println("to debit from: "+amouont);
				}
			}
		}else {
			synchronized (tieLock) {
				synchronized (from) {
					synchronized (to) {
						System.out.println("from debit to: "+amouont);
					}
				}
			}
		}
		
	}
	
public void transferMoney(Account from, Account to, int amouont) {
	synchronized (from) {
		synchronized (to) {
			System.out.println("from debit to: "+amouont);
		}
	}
		
	}
}
