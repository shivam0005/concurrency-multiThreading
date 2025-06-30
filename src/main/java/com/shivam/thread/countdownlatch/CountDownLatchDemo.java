package com.shivam.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        int workerCount = 5;
        CountDownLatch latch = new CountDownLatch(workerCount);

        System.out.println("Manager: waiting for all workers to finish...");

        for (int i=1; i <= workerCount; i++){
            int workerId = i;
            new Thread(() -> {
                try {
                    System.out.println("worker-" + workerId + ": working...");
                    Thread.sleep(1000 + workerId * 100); //simulate different duration
                    System.out.println("worker-" + workerId + ": Done!");

                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();  // signal completion
                }
            }).start();
        }

        latch.await(); // block until all workers call countDown()
        System.out.println("Manager: All workers done. Proceeding to next step!");
    }
}
