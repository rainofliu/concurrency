package com.ajin.concurrency.thread;

/**
 * 守护线程 Demo
 *
 * @author ajin
 **/
public class DaemonThreadDemo {

    public static class DaemonThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("I am alive!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new DaemonThread();
        // 设置为守护线程
        thread.setDaemon(true);
//        thread.setDaemon(false); 设置为非守护线程

        thread.start();
        // 守护线程在main线程退出后 也随之退出；如果不是守护线程，则会一直执行下去
        Thread.sleep(2000);
    }
}
