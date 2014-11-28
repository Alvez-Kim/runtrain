package pac.testcase.basic.thread;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

public class HarmTimer {
    /**
     * tasks scheduled after first task just missed
     * @throws InterruptedException
     */
    @Test
    public void go() throws InterruptedException {
        Timer timer = new Timer("timer-1",false);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                throw new RuntimeException();
            }
        };
        timer.schedule(task,100);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("task 2");
            }
        },1000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("task 3");
            }
        },1000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("task 4");
            }
        },1000);

        Thread.sleep(5000);
    }
}
