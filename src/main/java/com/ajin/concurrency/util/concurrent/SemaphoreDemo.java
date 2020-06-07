package com.ajin.concurrency.util.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * {@link Semaphore} Demo
 * 信号量 允许多个线程同时访问
 *
 * @author ajin
 **/
public class SemaphoreDemo implements Runnable {
    // 定义一个信号量
    final Semaphore semaphore = new Semaphore(5);


    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(200);
            System.out.println(Thread.currentThread().getId() + ":done");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        final SemaphoreDemo semaphoreDemo = new SemaphoreDemo();

        for (int i = 0; i < 20; i++) {
            executorService.submit(semaphoreDemo);
        }
    }
}
