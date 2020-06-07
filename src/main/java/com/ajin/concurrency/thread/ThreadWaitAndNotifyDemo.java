package com.ajin.concurrency.thread;

/**
 * {@link Object#wait()} 等待 && {@link Object##notify()} 通知
 *
 * @author ajin
 */
public class ThreadWaitAndNotifyDemo {

    final static Object object = new Object();

    public static class Thread1 extends Thread {

        @Override
        public void run() {

            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":Thread 1 Start!");
                try {
                    // wait的前提 是 获取了对象的monitor锁(通过synchronized)
                    System.out.println(System.currentTimeMillis() + ":Thread 1 wait for object !");
                    // wait 会释放 synchronized锁
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":Thread 1  End !");
            }
        }
    }

    public static class Thread2 extends Thread {
        @Override
        public void run() {
            // 线程1 释放锁后，线程2获取锁
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":Thread 2 start to Notify Thread 1");
                // 随机唤醒 其他线程， 在当前上下文中 就是 唤醒 Thread 1  让其竞争锁
                object.notify();
                System.out.println(System.currentTimeMillis() + ":Thread 2 End!");
                try {
                    // 这两秒之内 线程1 都在等待着锁的释放
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 当某一个对象实例调用wait方法时，当前执行线程会在该对象上等待

        // 一个线程调用了Object.wait方法后，当前线程就会进入Object对象的等待队列
        // 若多个线程等待一个对象，当object.notify对象调用时，会随机唤醒其中一个线程（随机唤醒是随机行为）
        // 与此同时，object.notifyAll方法调用时，会唤醒在该对象上等待的全部线程

        // wait方法不是随便调用的，需要在获取目标对象的监视器
        // sleep方法也是如此
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        thread1.start();
        thread2.start();
    }
}
