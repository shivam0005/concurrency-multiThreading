package com.shivam.thread.readwritelock.impl;

import com.shivam.thread.readwritelock.SharedMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockMap implements SharedMap {

    private final Map<String,String> map = new HashMap<>();
    private final Lock lock = new ReentrantLock();

    public ReentrantLockMap() {
        map.put("test", "value");
    }

    @Override
    public String read(String key) {
        lock.lock();
        try {
            log("reading...");
            sleep(100);
            return map.get(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void write(String key, String value) {
        lock.lock();
        try {
            log("writing...");
            sleep(150);
            map.put(key, value);
        } finally {
            lock.unlock();
        }
    }

    private void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored){
        }
    }

    private void log(String msg) {
        System.out.printf("[%s] %s%n", Thread.currentThread().getName(), msg);
    }
}
