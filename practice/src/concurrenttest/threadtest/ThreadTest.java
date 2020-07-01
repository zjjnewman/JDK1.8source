package concurrenttest.threadtest;

public class ThreadTest {
    /**
     * void interrupt()
     * static boolean interrupted()
     *
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("i="+(i+1));
            }
        });
        t1.start();
        t1.interrupt();
        System.out.println("t1   sInterrupted()  "+t1.isInterrupted());
        System.out.println("t1  interrupted()  "+Thread.interrupted());
        System.out.println("t1    "+t1.isInterrupted());
        System.out.println("t1是否存活：     "+t1.isAlive());

        System.out.println("main  isInterrupted()  "+Thread.currentThread().isInterrupted());
        Thread.currentThread().interrupt();
        System.out.println("main  调用interr（）后   "+Thread.currentThread().isInterrupted());
    }
}
