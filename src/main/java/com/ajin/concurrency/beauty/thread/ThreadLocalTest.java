package com.ajin.concurrency.beauty.thread;

import java.util.Objects;

/**
 * @author ajin
 **/
public class ThreadLocalTest {


//    static ThreadLocal<String> localVariable = new ThreadLocal<>();
    static ThreadLocal<String> localVariable = new InheritableThreadLocal<>();

    private static void print(String str) {
        System.out.println(str + ": " + localVariable.get());
        localVariable.remove();
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
//            localVariable.set("ThreadA localVariable");
//            localVariable.get()
            print("threadA local variable");
            System.out.println("threadA after remove:" + localVariable.get());
        });

        Thread threadB = new Thread(() -> {
            localVariable.set("ThreadB localVariable");
            print("threadB local variable");
            System.out.println("threadB after remove:" + localVariable.get());
        });

        threadA.start();
        threadB.start();

        localVariable.set("main thread value");

    }

    static final ThreadLocal<String> booleanValue = ThreadLocal.withInitial(() -> "hello, its me!");

}
