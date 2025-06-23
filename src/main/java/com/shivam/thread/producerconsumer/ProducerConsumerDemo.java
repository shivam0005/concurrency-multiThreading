package com.shivam.thread.producerconsumer;

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(5);

        Thread producer = new Thread(new Producer(buffer), "Producer");
        Thread consumer = new Thread(new Consumer(buffer), "Consumer");

        producer.start();
        consumer.start();
    }
}
