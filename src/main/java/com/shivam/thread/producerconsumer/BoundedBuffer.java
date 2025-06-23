package com.shivam.thread.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer<T> {

    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public BoundedBuffer(int capacity){
        this.capacity = capacity;
    }

    public void put(T item) throws InterruptedException{
        lock.lock();
        try {
            while (queue.size() == capacity){
                System.out.println("[Producer] Buffer full, waiting...");
                notFull.await();
            }
            queue.add(item);
            System.out.println("[Producer] Added: " + item);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException{
        lock.lock();
        try {
            while (queue.isEmpty()){
                System.out.println("[Consumer] Buffer empty, waiting...");
                notEmpty.await();
            }

            T item = queue.remove();
            System.out.println("[Consumer] Consumed: " + item);
            notFull.signal(); // Notify waiting producers
            return item;
        } finally {
            lock.unlock();
        }
    }
}
