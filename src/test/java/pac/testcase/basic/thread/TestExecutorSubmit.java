package pac.testcase.basic.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Alvez on 14-9-29.
 */
public class TestExecutorSubmit {
    class MyTask implements Runnable{

        String content;

        MyTask(String content) {
            this.content = content;
        }

        @Override
        public void run() {
            if (Thread.currentThread().isInterrupted())
                return;
            System.out.println("runnin..".concat(this.content));
            submitTask(content);
        }
    }

    private ExecutorService executor;

    public TestExecutorSubmit() {
        executor = Executors.newCachedThreadPool();
    }

    void submitTask(String content){
        executor.execute(new MyTask(content));
    }

    public static void main(String[] args) {
        TestExecutorSubmit testExecutorSubmit = new TestExecutorSubmit();

        testExecutorSubmit.submitTask("all hails alvez ");
    }
}
