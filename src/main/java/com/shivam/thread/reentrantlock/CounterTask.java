package com.shivam.thread.reentrantlock;

public class CounterTask implements Runnable{

    private final SafeCounter counter;

    public CounterTask(SafeCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            counter.increment();
            sleep(100); // Simulate work
        }
    }

    public void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
