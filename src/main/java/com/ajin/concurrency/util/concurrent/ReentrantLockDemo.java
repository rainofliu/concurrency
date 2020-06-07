package com.ajin.concurrency.util.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * {@link ReentrantLock} 可重入锁 Demo
 *
 * @author ajin
 **/
public class ReentrantLockDemo implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static int value = 0;

    @Override
    public void run() {
        for (int i = 0; i < 100000000; i++) {
            lock.lock();
            try {
                value++;
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * 与synchronized 相比  ReentrantLock使用起来更灵活 提供了一些API的支持 手动上锁 手动释放锁
     *
     * 可重入锁 加几次锁 就需要释放几次锁
     */
    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo lockDemo = new ReentrantLockDemo();
        Thread t1 = new Thread(lockDemo);
        Thread t2 = new Thread(lockDemo);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        // 200000000
        System.out.println(value);
    }
}
