package com.ajin.concurrency.beauty.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link ThreadLocal} Demo
 *
 * @author ajin
 **/
public class ThreadLocalDemo {

    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId =
            new ThreadLocal<Integer>() {
                @Override
                protected Integer initialValue() {
                    return nextId.getAndIncrement();
                }
            };

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println(get());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println(get());
        });

        thread1.start();

        thread2.start();



    }
}
