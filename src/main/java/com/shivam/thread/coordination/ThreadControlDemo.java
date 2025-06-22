package com.shivam.thread.coordination;

public class ThreadControlDemo {

    public static void main(String[] args) {

        Thread runnerA = new Thread(() ->{
            for (int i=1; i<=5; i++){
                System.out.printf("[RunnerA] Iteration %d (Thread: %s)%n", i, Thread.currentThread().getName());
                sleep(300);
            }
        }, "RunnerA");

        Thread runnerB = new Thread(() -> {
            for (int i=1; i<=5; i++){
                if (i == 2) {
                    System.out.println("[RunnerB] Yielding at iteration 2");
                    Thread.yield(); // Suggests letting RunnerA go
                }
                System.out.printf("[RunnerB] Iteration %d (Thread: %s)%n", i, Thread.currentThread().getName());
                sleep(300);
            }
        }, "RunnerB");


        System.out.println("Race starts!");

        runnerA.start();
        runnerB.start();

        // Main thread waits for both to complete
        try {
            runnerA.join();
            runnerB.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Race ends! All threads finished.");

    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
