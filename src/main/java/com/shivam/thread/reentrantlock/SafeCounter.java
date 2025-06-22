package com.shivam.thread.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class SafeCounter {

    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment(){
        lock.lock();
        try {
            count++;
            sleep(1000);
            System.out.printf("[%s] Count after increment: %d%n",
                    Thread.currentThread().getName(), count);

        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
