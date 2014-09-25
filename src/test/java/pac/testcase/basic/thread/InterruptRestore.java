package pac.testcase.basic.thread;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Alvez on 14-9-25.
 */
public class InterruptRestore {

    BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);

    public static void main(String[] args) throws IOException {
        final InterruptRestore nioTest = new InterruptRestore();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(nioTest.test());
                System.out.println(Thread.currentThread().isInterrupted());
            }
        });

        t1.start();
        t1.interrupt();

        try {
            nioTest.queue.put("alvez");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    String test() {
        boolean inter = false;
        try {
            while (true)
                try {
                    System.out.println("retake:::" + Thread.currentThread().isInterrupted());
                    return queue.take();
                } catch (InterruptedException e) {
                    System.out.println("try again");
                    inter = true;
                }
        } finally {
            if (inter) {
                System.out.println("request interrupt");
                Thread.currentThread().interrupt();
            }
        }
    }

}
