package com.ajin.concurrency.beauty.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * {@link Thread#sleep(long)}实例
 *
 * @author ajin
 **/
public class ThreadSleepDemo {

    public static void main(String[] args) throws InterruptedException{
//        sleepNotReleaseLock();
        interruptedExceptionDemo();
    }

    /**
     * 非公平锁
     */
    static ReentrantLock lock = new ReentrantLock();

    private static void sleepNotReleaseLock() {
        Thread threadOne = new Thread(() -> {
            // 获取锁
            lock.lock();
            try {
                System.out.println("thread 1 is sleep!");
                Thread.sleep(1000L);
                System.out.println("thread 1 is awake!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }

        });

        Thread threadTwo = new Thread(() -> {
            // 获取锁
            lock.lock();
            try {
                System.out.println("thread 2 is sleep!");
                Thread.sleep(1000L);
                System.out.println("thread 2 is awake!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }

        });

        threadOne.start();
        threadTwo.start();
    }

    private static void interruptedExceptionDemo() throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 启动子线程
        threadOne.start();
        // 主线程休眠
        Thread.sleep(1000L);
        // 中断子线程
        threadOne.interrupt();

    }
}
