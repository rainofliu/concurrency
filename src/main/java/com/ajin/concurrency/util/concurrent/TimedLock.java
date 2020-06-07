package com.ajin.concurrency.util.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 我们可以通过 限时等待 来避免 死锁 DeadLock
 * synchronized产生的锁 是非公平的，而ReentrantLock可以设置锁的公平性
 *
 * @author ajin
 **/
public class TimedLock implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    //  lock.tryLock();该方法获取锁 如果有线程持有锁，则返回false；否则成功获取锁，并返回true

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                // 6> 5 第二个线程一定获取不到锁
                Thread.sleep(6000);
            } else {
                System.out.println("get lock failed, cause reason : timeout");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TimedLock timedLock = new TimedLock();

        Thread t1 = new Thread(timedLock);
        Thread t2 = new Thread(timedLock);
        // 成功获取锁
        t1.start();
        // get lock failed, cause reason : timeout
        t2.start();


    }
}
