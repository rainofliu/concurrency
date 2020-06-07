package com.ajin.concurrency.thread;

/**
 * {@link Thread#suspend()} 挂起  && {@link Thread#resume()}继续执行 Demo
 * 被挂起的线程 需要 resume方法才能继续执行
 * suspend()方法在导致线程暂停的同时，不会释放锁（State:Runnable）
 *
 * @author ajin
 */
public class ThreadSuspendAndResumeDemo {

    public static void main(String[] args) {

    }
}
