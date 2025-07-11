package com.shivam.thread.atomicinteger;

public class WithoutAtomicInteger {

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        Runnable task = () -> {
            for (int i=0; i < 1000; i++){
                count++;                    // Non atomic -> leads to data racea
            }
        };

        Thread[] threads = new Thread[10];

        // Start 10 threads

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }

        // Wait for all to finish

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        System.out.println("Final count (without AtomicInteger): " + count);
    }
}

// 10 threads each increment 1000 times → Final count should be 10000

// Due to race conditions, the result is usually less than 10000
