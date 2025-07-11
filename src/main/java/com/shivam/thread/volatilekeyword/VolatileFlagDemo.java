package com.shivam.thread.volatilekeyword;

public class VolatileFlagDemo {

    private static volatile boolean flag = false;

    public static void main(String[] args) {

        Thread writer = new Thread(() -> {
            try {
                Thread.sleep(1000);
                flag = true;
                System.out.println("Writer updated flag to true");
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        Thread reader = new Thread(() -> {
            while (!flag){
                //busy-wait until flag becomes true
            }
            System.out.println("Reader detected flag = true");
        });


        writer.start();
        reader.start();


    }
}
