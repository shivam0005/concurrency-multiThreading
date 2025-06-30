package com.shivam.thread.semaphore;

import java.util.concurrent.Semaphore;

public class PrinterAccessDemo {

    public static void main(String[] args) {

        Semaphore printerSemaphore = new Semaphore(2);  // 2 printers

        for (int i=1; i <= 5; i++){
            int employeeId = i;
            new Thread(() -> {
                System.out.println("[Employee-" + employeeId + "] Waiting to use printer...");

                try {
                    printerSemaphore.acquire();
                    System.out.println("[Employee-" + employeeId + "] Printing...");
                    Thread.sleep(2000); // simulate printing time
                    System.out.println("[Employee-" + employeeId + "] Done printing.");

                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                } finally {
                    printerSemaphore.release(); // ✅ free up a printer
                }
            }).start();
        }


    }
}
