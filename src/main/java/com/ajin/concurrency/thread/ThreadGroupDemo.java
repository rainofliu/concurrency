package com.ajin.concurrency.thread;

import lombok.SneakyThrows;

/**
 * {@link ThreadGroup} 线程组Demo
 *
 * @author ajin
 **/
public class ThreadGroupDemo {

    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("threadGroup");

        Thread t1 = new Thread(threadGroup, new MyThread(), "t1");
        Thread t2 = new Thread(threadGroup, new MyThread(), "t2");

        t1.start();
        t2.start();

        System.out.println(threadGroup.activeCount());

        threadGroup.list();
    }

    private static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.printf("Thread %s is running!\n", Thread.currentThread().getName());
            
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {

            }
        }
    }
}
