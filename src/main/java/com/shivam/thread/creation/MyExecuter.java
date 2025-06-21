package com.shivam.thread.creation;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyExecuter {

    public void runTask(){

        ExecutorService executor = Executors.newFixedThreadPool(1);

        Runnable task = () -> {
            Thread current = Thread.currentThread();
            System.out.println("Thread: " + current.getName() + ", State: " + current.getState());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Exiting Thread: " + current.getName() + ", State: " + current.getState());
        };

        executor.submit(task); // Runnable



//        Future<Integer> future = executor.submit(() -> 42); // Callable
//        try {
//            Integer result = future.get(); // Blocking call to get result
//            System.out.println(result);
//        } catch (InterruptedException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }

        executor.shutdown();
    }

}
