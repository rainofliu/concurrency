package com.ajin.concurrency.thread;

public class ThreadSleepDemo {

    public static void main(String[] args) throws Exception {

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("interrupted!");
                        break;
                    }

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        // sleep方法执行期间，调用thread1.interrupt();方法会触发InterruptedException
                        // 中断异常 ，并且会清楚中断标记状态
                        System.out.println("interrupted during sleep!");
                        // 设置中断状态
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };

        thread1.start();
        Thread.sleep(200);
        thread1.interrupt();
        // interrupted during sleep!
        // interrupted!
    }
}
