package com.ajin.concurrency.pattern;

/**
 * 单例模式探讨
 *
 * @author ajin
 **/
public class SingletonDemo {

    public static class Singleton {
        private Singleton() {

        }

        private static final Singleton singleton = new Singleton();

        public static Singleton getInstance() {
            return singleton;
        }

    }
}
