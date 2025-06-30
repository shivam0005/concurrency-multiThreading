package com.shivam.thread.threadlocal.solution;

public class ThreadLocalUserContext {

    private static final ThreadLocal<String> userId = new ThreadLocal<>();

    public static void setUserId(String id) {
        userId.set(id);
    }

    public static String getUserId() {
        return userId.get();
    }

    public static void clear() {
        userId.remove(); // prevent memory leaks
    }
}
