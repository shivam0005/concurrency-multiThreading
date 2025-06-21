package com.shivam.thread.creation;

public class ThreadCreationDemo {

    public static void main(String[] args) {

        System.out.println("Main is starting");

        //Thread thread = new MyThread("Extending Thread");

        Thread thread = new Thread(new MyRunnable(), "Runnable Thread");
        System.out.println("Thread name: " + thread.getName() + ", state: " + thread.getState());

        thread.start();
        do {
            System.out.println("Thread name: " + thread.getName() + ", state: " + thread.getState());
        } while (thread.isAlive());

        System.out.println("Thread name: " + thread.getName() + ", state: " + thread.getState());



        MyExecuter myExecuter = new MyExecuter();
        myExecuter.runTask();


        System.out.println("Main is exiting");
    }
}
