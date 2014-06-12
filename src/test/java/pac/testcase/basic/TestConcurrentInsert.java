package pac.testcase.basic;

import java.util.concurrent.atomic.AtomicInteger;

public class TestConcurrentInsert {
	AtomicInteger primaryKey = new AtomicInteger(0);
	int key = 0;
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new worker().start();
//			new slave().start();
		}
	}
}

class worker extends Thread{
	TestConcurrentInsert t = new TestConcurrentInsert();
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(this.currentThread().getName()+":::"+t.primaryKey.addAndGet(1));
		}
	}
}

class slave extends Thread{
	TestConcurrentInsert t = new TestConcurrentInsert();
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(this.currentThread().getName()+":::"+(++t.key));
		}
	}
	
}