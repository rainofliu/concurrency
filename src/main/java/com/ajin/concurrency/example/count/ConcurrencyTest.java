package com.ajin.concurrency.example.count;

import com.ajin.concurrency.anotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 非线程安全的类
 *
 * @author ajin
 */
@SuppressWarnings("all")
@Slf4j
@NotThreadSafe
public class ConcurrencyTest {

    // 5000个请求
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    // 计数
    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        // 定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 定义信号量 ，表明并发执行的线程数
        final Semaphore semaphore = new Semaphore(threadTotal);

        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        // 将请求放入线程池执行
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    // 释放信号量
                    semaphore.release();

                } catch (InterruptedException e) {
                    log.error("exception:{}", e);
                }
                countDownLatch.countDown();
            });

        }

        countDownLatch.await();

        // 关闭线程池
        executorService.shutdown();


        log.info("count:{}", count);


    }

    private static void add() {
        count++;
    }
}
