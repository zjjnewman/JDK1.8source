package concurrenttest.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 对线程callable启动方式的测试
 */
public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //callable启动方式
        NumCount numCount = new NumCount();
        //这里必须每个线程new一个futureTask
        FutureTask<Integer> futureTask1 = new FutureTask(numCount);
        FutureTask<Integer> futureTask2 = new FutureTask(numCount);
        Thread thread1 = new Thread(futureTask1,"a");
        Thread thread2 = new Thread(futureTask2, "b");
        thread1.start();
        thread2.start();
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());

//        NumReduce numReduce = new NumReduce();
//        Thread thread3 = new Thread(numReduce);
//        Thread thread4 = new Thread(numReduce);
//        thread3.start();
//        thread4.start();

    }
}

class NumCount implements Callable<Integer> {
    int cnt = 0;
    boolean b = false;
    @Override
    public Integer call() throws Exception {
        for(int i = 0; i < 10; i++){
            cnt++;
            Thread.sleep(1000);
            System.out.println(Thread.currentThread()+" : "+cnt);
        }
        return cnt;
    }
}

class NumReduce implements Runnable{
    private int cnt = 10;
    @Override
    public void run() {
        for(int i = 0; i < 10; i--){
            if (cnt > 0){
                cnt--;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()+" : "+cnt);
            }
        }

    }
}