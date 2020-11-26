package threadlocal;

public class OrderDao {
    public void saveOrder(){
        String name= Thread.currentThread().getName();
//        System.out.println("orderDao 当前线程【"+ Thread.currentThread().getName() +"】保存到数据是：" + ThreadLocalTest.data.get(name));
        System.out.println("orderDao 当前线程【"+ Thread.currentThread().getName() +"】保存到数据是：" + ThreadLocalTest.threadLocal.get());
    }
}
