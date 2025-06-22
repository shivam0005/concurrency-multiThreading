package com.shivam.thread.reentrantlock;

public class LockDemo {

    public static void main(String[] args) {

        SafeCounter counter = new SafeCounter();

        Thread t1 = new Thread(new CounterTask(counter), "Thread-A");
        Thread t2 = new Thread(new CounterTask(counter), "Thread-B");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Final count = " + counter.getCount());
    }
}
