package com.ajin.concurrency.thread;

import com.ajin.concurrency.common.entity.User;

/**
 * {@link Thread#stop()} Demo
 *
 * @author ajin
 */
public class ThreadStopDemo {
    public static User user = new User();

    public static void main(String[] args) {

    }

    public static class ChangeObjectThread extends Thread {

        @Override
        public void run() {
            while (true) {
                synchronized (user) {
                    int v = (int) (System.currentTimeMillis() / 1000);
                    user.setId(v);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    user.setName(String.valueOf(v));
                }

                Thread.yield();
            }
        }
    }
}
