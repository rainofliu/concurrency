package com.ajin.concurrency.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ajin
 **/
public class HashMapMultiThreadDemo {

    static Map<String, String> map = new HashMap<>();

    public static class AddThread implements Runnable {
        int start = 0;

        public AddThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < 100000; i++) {
                map.put(Integer.toString(i), Integer.toBinaryString(i));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new HashMapMultiThreadDemo.AddThread(0));
        Thread thread2 = new Thread(new HashMapMultiThreadDemo.AddThread(1));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(map.size());

    }
}
