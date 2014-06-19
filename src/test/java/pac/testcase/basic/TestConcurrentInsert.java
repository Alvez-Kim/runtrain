package pac.testcase.basic;

import java.util.concurrent.atomic.AtomicInteger;

public class TestConcurrentInsert {
	
	AtomicInteger primaryKey = new AtomicInteger(0);
	int key = 0;
	public static void main(String[] args) {
		TestConcurrentInsert t = new TestConcurrentInsert();
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			new Worker(t).start();
//			new Slave(t).start();
//			new BufferedSlave(t).start();
		}
		System.out.println("used::"+(System.currentTimeMillis()-startTime));
	}
}

class Worker extends Thread{
	private TestConcurrentInsert t;
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName()+":::"+t.primaryKey.addAndGet(1));
		}
	}
	public Worker(TestConcurrentInsert t) {
		super();
		this.t = t;
	}
	
}

class Slave extends Thread{
	TestConcurrentInsert t;
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName()+":::"+(++t.key));
		}
	}
	public Slave(TestConcurrentInsert t) {
		super();
		this.t = t;
	}
}

class BufferedSlave extends Thread{
	TestConcurrentInsert t;
	@Override
	public void run() {
		synchronized (t) {
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName()+":::"+(++t.key));
			}
		}
	}
	public BufferedSlave(TestConcurrentInsert t) {
		super();
		this.t = t;
	}
	
}