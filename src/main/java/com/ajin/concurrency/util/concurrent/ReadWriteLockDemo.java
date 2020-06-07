package com.ajin.concurrency.util.concurrent;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * {@link java.util.concurrent.locks.ReadWriteLock}读写锁Demo
 * 读读不互斥；
 * 读写 互斥;
 * 写写互斥
 *
 * @author ajin
 **/
public class ReadWriteLockDemo {

    private static Lock lock = new ReentrantLock();

    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static Lock readLock = readWriteLock.readLock();

    private static Lock writeLock = readWriteLock.writeLock();

    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {
        lock.lock();
        try {
            Thread.sleep(1000);
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock, int index) throws InterruptedException {

        lock.lock();
        try {
            Thread.sleep(1000);
            value = index;

        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();

        Runnable readRunnable = () -> {
            try {
                demo.handleRead(readLock);
//                    demo.handleRead(lock);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable writeRunnable = () -> {
            try {
                demo.handleWrite(writeLock, new Random().nextInt());
//                    demo.handleRead(lock);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 18; i++) {
            new Thread(readRunnable).start();
        }

        for (int i = 18; i < 20; i++) {
            new Thread(writeRunnable).start();
        }
    }
}
