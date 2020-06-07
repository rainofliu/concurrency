package com.ajin.concurrency.util.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ajin
 * @see Condition
 **/
public class LockConditionDemo implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();

    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            // 释放当前锁
            condition.await();
            System.out.println("thread is going on!");

        } catch (InterruptedException e) {

        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        LockConditionDemo conditionDemo = new LockConditionDemo();

        Thread t1 = new Thread(conditionDemo);
        t1.start();
        Thread.sleep(2000);

        // 通知t1继续执行
        lock.lock();
        // 唤醒线程
        condition.signal();
        lock.unlock();
    }
}
