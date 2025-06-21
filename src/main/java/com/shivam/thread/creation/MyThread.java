package com.shivam.thread.creation;

public class MyThread extends Thread{

    public MyThread(String name){
        super(name);
    }

    @Override
    public void run(){
        try {
            System.out.println("Thread is running");
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
