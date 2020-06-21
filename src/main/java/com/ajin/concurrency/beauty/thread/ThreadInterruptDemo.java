package com.ajin.concurrency.beauty.thread;

/**
 * @author ajin
 **/
public class ThreadInterruptDemo {

    public static void main(String[] args) throws InterruptedException {
//        feelInterrupt();

        sleep();
    }

    private static final int MAX_COUNT = 1000;

    private static void feelInterrupt() throws InterruptedException {
        // 创建子线程
        Thread thread = new Thread(new StopThread());
        // 启动子线程
        thread.start();
        // 主线程睡眠5ms
        Thread.sleep(5);
        // 中断子线程
        thread.interrupt();
    }

    private static class StopThread implements Runnable {

        @Override
        public void run() {
            int count = 0;
            // 1. 判断线程是否中断
            // 2. 判断count是否小于1000
            // 3. 如果1和2都满足，则打印count
            while (!Thread.currentThread().isInterrupted() && count < MAX_COUNT) {
                System.out.printf("count=%s\n", count);
                count++;
            }
        }
    }

    private static void doMoreWork() {
        boolean needToDoMoreWork = true;
        while (!Thread.currentThread().isInterrupted() && needToDoMoreWork) {
            doMoreWork();
        }
    }

    private static void sleep() throws InterruptedException {

        Runnable runnable = () -> {
            int num = 0;
            try {
                while (!Thread.currentThread().isInterrupted() && num <= MAX_COUNT) {
                    System.out.println(num);
                    num++;
                    Thread.sleep(1000000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(3000L);
    }
}
