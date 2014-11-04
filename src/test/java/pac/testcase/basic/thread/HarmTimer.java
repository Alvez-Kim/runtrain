package pac.testcase.basic.thread;

import org.junit.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class HarmTimer {
    /**
     * even if i catched runtimeexception , tasks after first task just failed
     * @throws InterruptedException
     */
    @Test
    public void go() throws InterruptedException {
        Timer timer = new Timer("timer-1",false);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try{
                    //throw new RuntimeException();
                }catch (Exception e){
                    System.out.println("catched");
                }
            }
        };
        timer.schedule(task,1);
        Thread.sleep(1l);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("task 2");
            }
        },1);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("task 3");
            }
        },1);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("task 4");
            }
        },1);
    }
}
