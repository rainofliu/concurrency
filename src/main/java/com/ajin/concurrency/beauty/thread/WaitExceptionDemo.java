package com.ajin.concurrency.beauty.thread;

/**
 * @author ajin
 **/
public class WaitExceptionDemo {
    static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            synchronized (object) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadA.start();

        Thread.sleep(1000L);

        threadA.interrupt();
    }
}
