package com.ajin.concurrency.beauty.thread;

/**
 * {@link Object#wait()}
 *
 * @author ajin
 **/
public class WaitSimpleDemo {
    /**
     * 共享变量
     */
    static Object sharedObjectInstance = new Object();

    public static void main(String[] args) throws InterruptedException {

        synchronized (sharedObjectInstance) {
            System.out.println(Thread.currentThread().getName());
            sharedObjectInstance.wait();
        }
        Runnable runnable = () -> {

        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
    }
}
