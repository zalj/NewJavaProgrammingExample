package chapter30;

import java.util.concurrent.*;

public class ConsumerProducerUsingBlockingQueue {
	private static ArrayBlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(2);
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		executor.execute(new ProducerTask());
		executor.execute(new ConsumerTask());
		
		executor.shutdown();
	}
	
	private static class ProducerTask implements Runnable{
		@Override
		public void run() {
			try {
				int i = 1;
				while(true) {
					System.out.println("Producer writes " + i);
					buffer.put(i++);	// Add any value to the buffer, say, 1
					// Put the thread into sleep
					Thread.sleep((int)(Math.random() * 10000));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static class ConsumerTask implements Runnable{
		@Override
		public void run() {
			try {
				while(true) {
					System.out.println("\t\t\tConsumer reads " + buffer.take());
					
					Thread.sleep((int)(Math.random() * 10000));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
