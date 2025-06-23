package com.shivam.thread.readwritelock;

import com.shivam.thread.readwritelock.impl.ReadWriteLockMap;
import com.shivam.thread.readwritelock.impl.ReentrantLockMap;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BenchmarkTest {

    public static void main(String[] args) throws InterruptedException {

        int readers = 10;
        int writers = 2;

        System.out.println("Benchmarking with ReentrantLock...");

        runTest(new ReentrantLockMap(), readers, writers);

        System.out.println("\nBenchmarking with ReadWriteLock...");
        runTest(new ReadWriteLockMap(), readers, writers);
    }

    private static void runTest(SharedMap map, int readers, int writers) throws InterruptedException{

        ExecutorService executor = Executors.newFixedThreadPool(readers + writers);
        CountDownLatch latch = new CountDownLatch(readers + writers);

        long start = System.currentTimeMillis();


        //start reader threads
        for (int i=0; i<readers; i++){
            executor.submit(() ->{
                map.read("test");
                latch.countDown();
            });
        }

        // Start writer threads
        for (int i = 0; i < writers; i++) {
            executor.submit(() -> {
                map.write("test", "val-" + Thread.currentThread().getName());
                latch.countDown();
            });
        }

        latch.await();

        long end = System.currentTimeMillis();
        executor.shutdown();

        System.out.println("Time taken: " + (end - start) + " ms" );
    }
}
