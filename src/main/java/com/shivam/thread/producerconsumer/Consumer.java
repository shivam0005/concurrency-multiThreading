package com.shivam.thread.producerconsumer;

public class Consumer implements Runnable{

    private final BoundedBuffer<Integer> buffer;

    public Consumer(BoundedBuffer<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {

        while (true){
            try {
                buffer.take();
                Thread.sleep(800);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
