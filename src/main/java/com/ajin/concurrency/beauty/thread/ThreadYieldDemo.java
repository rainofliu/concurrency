package com.ajin.concurrency.beauty.thread;

/**
 * @author ajin
 **/
public class ThreadYieldDemo implements Runnable {
    public static void main(String[] args) {
        new ThreadYieldDemo();
        new ThreadYieldDemo();
        new ThreadYieldDemo();
    }

   private ThreadYieldDemo() {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if ((i % 5) == 0) {
                System.out.println(Thread.currentThread() + "yield CPU");
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread()+"is over");
    }
}
