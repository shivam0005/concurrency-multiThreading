package com.shivam.thread.atomicinteger;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {

    static AtomicInteger count = new AtomicInteger(0); // ✅ Thread-safe

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                count.incrementAndGet(); // ✅ Atomic operation
            }
        };

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        System.out.println("Final count (with AtomicInteger): " + count.get());
    }
}
