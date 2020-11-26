package threadlocal;

import jdbc.bean.Order;

public class OrderService {

    public void createOrder(){
        String name= Thread.currentThread().getName();
//        System.out.println("orderService 当前线程【"+ Thread.currentThread().getName() +"】保存到数据是：" + ThreadLocalTest.data.get(name));
        System.out.println("orderService 当前线程【"+ Thread.currentThread().getName() +"】保存到数据是：" + ThreadLocalTest.threadLocal.get());
        new OrderDao().saveOrder();
    }
}
