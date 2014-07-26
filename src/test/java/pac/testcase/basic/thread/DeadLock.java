package pac.testcase.basic.thread;

public class DeadLock  {
	public static void main(String[] args) {
		final Integer a = new Integer(1);
		final Integer b = new Integer(1);
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (a) {
					try {
						System.out.println("t1a");
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (b) {
						System.out.println("t1b");
					}
				}
			}

		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (b) {
					System.out.println("t2b");
					synchronized (a) {
						System.out.println("t2a");
					}
				}
			}
		});
		t1.start();
		t2.start();
	}
}