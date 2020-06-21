package com.ajin.concurrency.beauty.thread;

/**
 * {@link InheritableThreadLocal} Demo
 *
 * @author ajin
 **/
public class InheritableThreadLocalTest {

    static ThreadLocal<String> inheritableThreadLocal =
            new InheritableThreadLocal<>();

    public static void main(String[] args) {
        Thread subThread = new Thread(() -> {

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(inheritableThreadLocal.get());
        });


        subThread.start();

        inheritableThreadLocal.set("main thread value");

    }
}
