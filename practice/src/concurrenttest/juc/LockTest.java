package concurrenttest.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    /**
     * LockSupport() park() unPark()
     *
     * ReentrantLock() lock() unlock()
     * condition.await() condition.signalAll()
     *
     * Object() wait() notify() notifyAll()
     *
     *
     */
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();

        Condition condition = reentrantLock.newCondition();
        condition.await();
        condition.signal();
        condition.signalAll();

        Object o = new Object();
        o.wait();
        o.notify();
        o.notifyAll();


    }

}
