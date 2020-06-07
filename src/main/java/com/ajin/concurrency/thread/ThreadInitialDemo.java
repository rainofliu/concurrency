package com.ajin.concurrency.thread;

/**
 * 线程初始化Demo
 *
 * @author ajin
 */
public class ThreadInitialDemo {

    public static void main(String[] args) {
        // 创建一个线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello,I am a thread");
            }
        });
        // 启动线程
        thread.start();
    }
}
