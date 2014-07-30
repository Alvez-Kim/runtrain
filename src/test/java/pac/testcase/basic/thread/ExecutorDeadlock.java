package pac.testcase.basic.thread;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDeadlock {
	public static void main(String[] args) {
		final Set<Integer> set =new HashSet<>();
		
		for (int i = 0; i < 10000; i++) {
			set.add(i);
		}
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (set) {
					for (Integer i : set) {
						if(i==23){
							
							//needless background thread leads to deadlock
							ExecutorService e = Executors.newSingleThreadExecutor();
							try {
								e.submit(new Runnable() {
									
									@Override
									public void run() {
										synchronized (set) {
											set.remove(1);
										}
									}
								}).get();
							} catch (InterruptedException | ExecutionException e1) {
								e1.printStackTrace();
							}finally{
								e.shutdown();
							}
							
						}
					}
				}
			}
		});
		t1.start();
	}
}
