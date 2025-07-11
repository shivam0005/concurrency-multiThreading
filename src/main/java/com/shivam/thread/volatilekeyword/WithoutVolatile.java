package com.shivam.thread.volatilekeyword;

public class WithoutVolatile {

    private static boolean running = true;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            System.out.println("Worker thread started...");
            while (running){
                // empty loop
            }
            System.out.println("Worker thread stopped.");
        });

        thread.start();

        Thread.sleep(2000); //wait 2 second before stopping
        running = false; // Main thread changes the value
        System.out.println("Main thread set running = false");

    }
}


//   Expected behavior: The worker thread should exit after 2 seconds.


//   Actual behavior:  The worker thread might never stop.
    //   It keeps reading true from its thread-local CPU cache.
    //   It never sees the change made by the main thread.
