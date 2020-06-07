package com.ajin.concurrency.thread;

/**
 * @author ajin
 **/
public class ThreadJoinYieldDemo {

    // Join就是等待之前的线程执行完再执行
    // yield 就是觉得自己执行完了重要的任务，可以让出CPU资源，但是还是会去竞争CPU
    // 一种降低线程优先级的方式

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("执行！");
        });
        thread.start();
        // 无限等待，一直阻塞当前线程，直到其执行完成
        thread.join();
        // 在给定时间内 阻塞当前线程，直到其执行完成（如果给定时间内仍然没有执行完成，那么就不等了）
        thread.join(3000);
    }
}
