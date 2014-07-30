package pac.testcase.basic.thread;

import java.util.HashSet;
import java.util.Set;

public class ConcurrentModify {
	public static void main(String[] args) {
		final Set<Integer> set = new HashSet<>();
		for (int i = 0; i < 10000; i++) {
			set.add(i);
		}

		Thread t1 = new Thread(new Runnable() {

			@Override
			// if remove one of synchronized block ,
			// ConcurrentModificationException occurs
			public void run() {
				synchronized (set) {
					for (Integer i : set) {
						if (i == 36) {
							Thread t2 = new Thread(new Runnable() {

								@Override
								public void run() {
									synchronized (set) {
										set.remove(6);
									}
								}
							});
							t2.start();
						}
					}
				}

			}
		});
		t1.start();

	}
}