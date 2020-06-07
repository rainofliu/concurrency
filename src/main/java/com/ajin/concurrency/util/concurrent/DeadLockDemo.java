package com.ajin.concurrency.util.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ajin
 **/
public class DeadLockDemo implements Runnable {

    public static ReentrantLock lock1 = new ReentrantLock();

    public static ReentrantLock lock2 = new ReentrantLock();

    int lock;

    public DeadLockDemo(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                Thread.sleep(500);
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                Thread.sleep(500);
                lock1.lockInterruptibly();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }

            System.out.println(Thread.currentThread().getId() + "：线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadLockDemo deadLockDemo1 = new DeadLockDemo(1);
        DeadLockDemo deadLockDemo2 = new DeadLockDemo(2);

        Thread t1 = new Thread(deadLockDemo1);
        Thread t2 = new Thread(deadLockDemo2);
        t1.start();
        t2.start();

        Thread.sleep(500);
        // 中断其中一个线程
//        t2.interrupt();
    }
}
