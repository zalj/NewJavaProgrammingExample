package chapter30;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountWithoutSync {
	private static Account account = new Account();
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for(int i = 0; i < 100; i++) {
			executor.execute(new AddAPennyTask());
		}
		
		executor.shutdown();
		
		while(!executor.isTerminated()) {
		}
		
		System.out.println("What is balance? " + account.getBalance());
	}
	
	private static class AddAPennyTask implements Runnable{
		public void run() {
				account.deposit(1);
		}
	}
	
	private static class Account{
		private int balance = 0;
		
		public int getBalance() {
			return balance;
		}
		
		//synchronized 关键字使得该方法成为线程安全（thread-safe）的方法
//		public synchronized void deposit(int amount) {
		public void deposit(int amount) {
//			balance += amount;	// 这样不不太可能出现数据破坏的问题
			int newBalance = balance + amount;
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			balance = newBalance;
		}
	}
}
