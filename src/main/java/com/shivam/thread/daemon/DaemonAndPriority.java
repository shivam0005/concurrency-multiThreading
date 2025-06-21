package com.shivam.thread.daemon;

public class DaemonAndPriority {

    public static void main(String[] args) {

        Thread userThread = new Thread(() -> {
           for (int i=0; i<10; i++){
               logThreadInfo("User thread loop " + i);
               sleep(300);
           }
        }, "UserThread");

        userThread.setPriority(Thread.MIN_PRIORITY);

        Thread daemonThread = new Thread(() -> {
            int count = 0;
            while (true){
                logThreadInfo("Daemon thread loop " + count++);
                sleep(350);
            }
        }, "DaemonThread");

        daemonThread.setDaemon(true);
        daemonThread.setPriority(Thread.MAX_PRIORITY);

        userThread.start();
        daemonThread.start();


        System.out.println("Main method finished");

    }


    private static void logThreadInfo(String message) {
        Thread t = Thread.currentThread();
        System.out.printf("[%s] (Daemon: %s, Priority: %d): %s%n",
                t.getName(), t.isDaemon(), t.getPriority(), message);
    }


    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}
