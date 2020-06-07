package com.ajin.concurrency.thread;

import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.MIN_PRIORITY;

/**
 * 线程优先级Demo
 *
 * @author ajin
 **/
public class ThreadPriorityDemo {

    public static void main(String[] args) {
        // 创建两个线程
        Thread high = new HighPriority();
        Thread low = new LowPriority();

        // 分别设置线程优先级
        high.setPriority(MAX_PRIORITY);
        low.setPriority(MIN_PRIORITY);

        // 启动线程
        low.start();
        high.start();

    }

    private static class LowPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (ThreadPriorityDemo.class) {
                    count++;
                    if (count > 1000000) {
                        System.out.println("LowPriority线程执行完毕");
                        break;
                    }
                }
            }
        }
    }


    private static class HighPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (ThreadPriorityDemo.class) {
                    count++;
                    if (count > 1000000) {
                        System.out.println("HighPriority线程执行完毕");
                        break;
                    }
                }
            }
        }
    }
}
