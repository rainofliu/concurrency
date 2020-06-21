package com.ajin.concurrency.beauty.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 生产者——消费者 Demo
 *
 * @author ajin
 **/
@SuppressWarnings("all")
public class ProducerConsumerDemo {

    static int MAX_SIZE = 10;
    /**
     * 共享变量
     */
    static BlockingQueue<String> queue = new ArrayBlockingQueue<>(MAX_SIZE);


    public static void main(String[] args) {

        // 生产者线程
        Thread producerThread = new Thread(() -> {
            synchronized (queue) {
                // 消费队列满，则等待队列空闲
                while (queue.size() == MAX_SIZE) {
                    try {
                        // 消费队列已满，则不需要再生产了，阻塞生产者线程 释放当前锁
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 空闲 则生成元素
                queue.add("test");

                // 唤醒消费者线程 来消费
                queue.notifyAll();
            }
        });
        // 消费者线程
        Thread consumerThread = new Thread(() -> {

            synchronized (queue) {
                // 消费队列为空
                while (queue.size() == 0) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                String take = queue.take();
                System.out.println(take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queue.notifyAll();
        });

    }

}
