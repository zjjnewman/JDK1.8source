package threadlocal;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadLocalTest {

    public static Map<String, Object> data = new HashMap<>();
    public static ThreadLocal<Object> threadLocal = new ThreadLocal<>();
    private static Random random = new Random();

    public static class Task implements Runnable{

        @Override
        public void run() {
            // 在run方法中随机生成 变量（线程要关联的数据），以当前线程名为 key，变量为 val 保存在 data中
            Integer i = random.nextInt(1000);

            String threadName = Thread.currentThread().getName();

            System.out.println("线程【" + threadName + "】生成的随机数是：" + i);

//            data.put(threadName, i);
            threadLocal.set(i);


            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new OrderService().createOrder();

            // 在run方法结束之前，用当前线程名 取出变量打印，查看数据是否可以取出
//            Object o = data.get(threadName);
            Object o = threadLocal.get();
            System.out.println("线程【" + threadName + "】快结束时，取出的随机数是：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Task()).start();
        }
    }

}
