package com.ajin.concurrency.thread;

import com.ajin.concurrency.anotations.NotThreadSafe;

import java.util.ArrayList;
import java.util.Vector;

/**
 * {@link java.util.ArrayList} 多线程场景下的Demo
 *
 * @author ajin
 **/

@NotThreadSafe
public class ArrayListMultiThreadDemo {

    static ArrayList<Integer> list = new ArrayList<>(10);

    static Vector<Integer> vector = new Vector<>();

    private static class AddThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
//                list.add(i);
                vector.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new AddThread();
        Thread t2 = new AddThread();

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(list.size());
        System.out.println(vector.size());
    }
}
