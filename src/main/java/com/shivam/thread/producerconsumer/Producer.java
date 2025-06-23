package com.shivam.thread.producerconsumer;

public class Producer implements Runnable{

    private final BoundedBuffer<Integer> buffer;

    public Producer(BoundedBuffer<Integer> buffer) {
        this.buffer = buffer;
    }


    @Override
    public void run() {
        int value = 1;
        while (true){
            try {
                buffer.put(value++);
                Thread.sleep(500);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
