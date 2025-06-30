package com.shivam.thread.threadlocal.solution;

public class ThreadLocalDemo {

    public static void main(String[] args) {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            ThreadLocalUserContext.setUserId("user_" + threadName);

            try {
                Thread.sleep(100); // simulate delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.printf("[%s] Got userId: %s%n", threadName, ThreadLocalUserContext.getUserId());
            ThreadLocalUserContext.clear(); // 🔑 clean up
        };

        for (int i = 1; i <= 5; i++) {
            new Thread(task, "T" + i).start();
        }
    }
}
