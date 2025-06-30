package com.shivam.thread.threadlocal.problem;

// without ThreadLocal
public class SharedUserContext {

    private static String userId;  // shared variable

    public static void setUserId(String id){
        userId = id;
    }

    public static String getUserId() {
        return userId;
    }
}
