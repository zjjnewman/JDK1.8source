package concurrenttest.juc;

import java.util.concurrent.*;

public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        //线程组和线程池的联系
//        ThreadGroup threadGroup = new ThreadGroup("tgt");
//        Thread thread;
//        for (int i = 0; i < 10; i++) {
//            new Thread(threadGroup, ()->{
//                System.out.println(Thread.currentThread());
//                System.out.println(threadGroup.activeCount());
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
//        System.out.println(Thread.currentThread()+" : ");
//        threadGroup.list();

        //callable() 启动方式回顾
//        NumCnt2 numCnt2 = new NumCnt2();
//        FutureTask futureTask = new FutureTask(numCnt2);
//        Thread thread = new Thread(futureTask);
//        thread.start();

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);


        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new NumCnt1());
        executorService.execute(new NumCnt1());
        executorService.execute(new NumCnt1());
        executorService.execute(new NumCnt1());
        executorService.execute(new NumCnt1());

        executorService.shutdown();
    }
}

class NumCnt1 implements Runnable{
    static int cnt;
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            cnt++;
            System.out.println(Thread.currentThread()+" : "+cnt);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class NumCnt2 implements Callable{
    private int cnt;
    @Override
    public Object call() throws Exception {
        for (int i = 0; i < 20; i++) {
            cnt++;
            System.out.println(cnt);
            Thread.sleep(1000);
        }
        return null;
    }
}