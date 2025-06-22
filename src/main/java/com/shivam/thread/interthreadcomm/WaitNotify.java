package com.shivam.thread.interthreadcomm;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class WaitNotify {

    private static final List<Integer> sharedList = new LinkedList<>();
    private static final Object lock = new Object();

    public static void main(String[] args) {

        Thread producer = new Thread(() -> {
            for (int i=1; i<=5; i++){
                synchronized (lock) {
                    sharedList.add(i);
                    System.out.println("Producer produced: " + i);
                    lock.notify(); //wake up consumer
                }

                sleep(500);
            }
        });


        Thread consumer = new Thread(() -> {

            for (int i=1; i<=5; i++){
                synchronized (lock) {
                    while (sharedList.isEmpty()){   //use while
                        try {
                            System.out.println("Consumer waiting...");
                            lock.wait(); //wait until notified
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }

                    int value = sharedList.remove(0);
                    System.out.println("Consumer consumed: " + value);
                }

                sleep(300);
            }
        });

        producer.start();
        consumer.start();

    }


    public static void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
