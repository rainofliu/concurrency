package com.ajin.concurrency.beauty.thread;

/**
 * synchronized Demo
 *
 * @author ajin
 **/
@SuppressWarnings("all")
public class SynchronizedDemo {
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {
            try {
                synchronized (resourceA) {
                    System.out.println("ThreadA get resourceA lock!");
                    synchronized (resourceB) {
                        System.out.println("ThreadA get resourceB lock!");

                        System.out.println("ThreadA release resourceB lock!");

                        // 线程A 阻塞 并释放获取到的resourceA锁
                        // 此时不会释放resourceB锁
                        resourceA.wait();

                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                Thread.sleep(1000);
                synchronized (resourceA) {
                    System.out.println("ThreadB get resourceA lock!");

                    System.out.println("ThreadB try to get resourceB lock!");
                    // 因为A线程没有释放掉resourceB的锁，所以B线程就无法获取该锁
                    // failed to get resourceB lock
                    synchronized (resourceB) {
                        System.out.println("ThreadB  get resourceB lock!");
                        System.out.println("ThreadB release resourceB lock!");
                        resourceA.wait();
                    }
                }
            } catch (InterruptedException e) {

            }
        });

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();

        System.out.println("main Over");
    }
}
