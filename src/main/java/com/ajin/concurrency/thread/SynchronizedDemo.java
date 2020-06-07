package com.ajin.concurrency.thread;

/**
 * synchronized Demo
 * <p>
 * 在使用synchronized关键字时 一定要注意被锁的对象是谁
 *
 * @author ajin
 **/
public class SynchronizedDemo {

    /**
     * 锁的是当前SynchronizedDemo对象实例
     */
    public synchronized void lockInstance() {

    }

    /**
     * 锁的是当前SynchronizedDemo.class 类对象
     */
    public static synchronized void lockClass() {

    }

    public void lockCertainObject() {
        int i = 0;
        synchronized (Object.class) {
            
        }
        // compile error
//        synchronized (i.class) {
//
//        }
    }
}
