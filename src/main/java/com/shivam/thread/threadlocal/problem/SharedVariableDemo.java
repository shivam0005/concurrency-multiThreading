package com.shivam.thread.threadlocal.problem;

public class SharedVariableDemo {

    public static void main(String[] args) {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            SharedUserContext.setUserId("user_" + threadName);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }

            System.out.printf("[%s] Got userId: %s%n", threadName, SharedUserContext.getUserId());
        };

        for (int i = 1; i <= 5; i++) {
            new Thread(task, "T" + i).start();
        }
    }
}
