package com.ajin.concurrency.beauty.thread;

/**
 * {@link Thread#join()} Demo
 *
 * @author ajin
 **/
public class ThreadJoinDemo {

    public static void main(String[] args) throws InterruptedException {
//        simpleJoinDemo();
        interruptedExceptionDemo();
    }

    /**
     * {@link InterruptedException}
     */
    private static void interruptedExceptionDemo() {
        Thread threadOne = new Thread(() -> {
            System.out.println("Thread1 begin run!");
            for (; ; ) {

            }

        });
        // 获取主线程
        final Thread mainThread = Thread.currentThread();

        Thread threadTwo = new Thread(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 中断主线程
            mainThread.interrupt();
        });

        // 启动子线程1
        threadOne.start();

        // 延迟启动子线程2
        threadTwo.start();

        // 等待线程1执行结束
        try {
            threadOne.join();
        } catch (InterruptedException e) {
            System.out.println("main thread:" + e);
        }
    }

    /**
     * 简单的{@link Thread#join()} Demo
     */
    private static void simpleJoinDemo() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("child thread1 process over!");
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("child thread2 process over!");
        });

        thread1.start();
        thread2.start();
        System.out.println("waiting for child threads over!");
        thread1.join();
        thread2.join();

        System.out.println("all child threads over!");
    }
}
