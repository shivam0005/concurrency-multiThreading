package com.shivam.thread.readwritelock.impl;

import com.shivam.thread.readwritelock.SharedMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockMap implements SharedMap {

    private final Map<String,String> map = new HashMap<>();
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    public ReadWriteLockMap(){
        map.put("test", "value");
    }
    @Override
    public String read(String key) {
        readLock.lock();
        try {
            log("reading...");
            sleep(100);
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void write(String key, String value) {
        writeLock.lock();
        try {
            log("writing...");
            sleep(150);
            map.put(key, value);
        } finally {
            writeLock.unlock();
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
