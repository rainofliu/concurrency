package com.ajin.concurrency.beauty.thread;

/**
 * 守护线程 Demo
 *
 * @author ajin
 **/
public class DaemonThreadDemo {

    public static void main(String[] args) {
        Thread daemonThread = new Thread(() -> {
            System.out.println("daemond Thread is running");
        });

        daemonThread.setDaemon(true);
        daemonThread.start();
    }
}
