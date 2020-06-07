package com.ajin.concurrency.thread;

import com.ajin.concurrency.anotations.NotThreadSafe;

/**
 * 线程不安全Demo
 *
 * @author ajin
 **/
@NotThreadSafe
public class ThreadNotSafeDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new TestRunnable());
        Thread t2 = new Thread(new TestRunnable());
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        // 945796
        System.out.println(TestRunnable.i);
    }

    static class TestRunnable implements Runnable {

        static volatile int i = 0;

        static final int count = 1000000;

        public static void increase() {
            i++;
        }

        @Override
        public void run() {
            for (int j = 0; j < count; j++) {
                increase();
            }
        }
    }
}
