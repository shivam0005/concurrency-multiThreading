package com.shivam.thread.readwritelock;

public interface SharedMap {
    String read(String key);
    void write(String key, String value);
}
