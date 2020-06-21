package com.ajin.concurrency.beauty.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程创建Demo
 *
 * @author ajin
 **/
public class ThreadCreateDemo {
//    public static void main(String[] args) {
//        Thread thread = new MyThread();
//        thread.start();
//
//        Thread runnableTask1 = new Thread(new RunnableTask());
//        Thread runnableTask2 = new Thread(new RunnableTask());
//        runnableTask1.start();
//        runnableTask2.start();
//    }

    /**
     * 方式1：继承{@link Thread}，重写run方法
     */
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.printf("thread[%s] is running\n", Thread.currentThread().getId());
        }
    }

    /**
     * 方式2：实现{@link Runnable}接口，实现其run方法
     */
    static class RunnableTask implements Runnable {

        @Override
        public void run() {
            System.out.println("RunnableTask is running!");
        }
    }

    public static class CallableTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "hello";
        }
    }

    public static void main(String[] args) {
        // 创建异步任务
        // FutureTask <-   RunnableFuture  -< Runnable
        FutureTask<String> futureTask = new FutureTask<>(new CallableTask());
        new Thread(futureTask).start();

        try {
            String result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
