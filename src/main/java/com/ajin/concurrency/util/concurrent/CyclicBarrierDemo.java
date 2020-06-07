package com.ajin.concurrency.util.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * {@link CyclicBarrier} Demo
 *
 * @author ajin
 **/
public class CyclicBarrierDemo {

    public static class Soldier implements Runnable {

        private String solder;
        private final CyclicBarrier cyclics;

        public Soldier(String solder, CyclicBarrier cyclics) {
            this.solder = solder;
            this.cyclics = cyclics;
        }

        @Override
        public void run() {
            try {
                // 等待所有士兵到齐
                cyclics.await();
                doWork();
                // 等待所有士兵完成工作
                cyclics.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private void doWork() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("soldier finish task");
        }


    }

    public static class BarrierRun implements Runnable {
        boolean flag;
        int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("士兵 " + N + "个任务完成");
            } else {
                System.out.println("士兵 " + N + "个集合完毕");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int N = 10;
        Thread[] threads = new Thread[N];
        boolean flag = false;
        CyclicBarrier barrier = new CyclicBarrier(N, new BarrierRun(flag, N));

        System.out.println("集合队伍!");
        for (int i = 0; i < N; i++) {
            System.out.println("士兵" + i + "报道");
            threads[i] = new Thread(new Soldier("士兵" + i, barrier));
            threads[i].start();
        }
    }
}
