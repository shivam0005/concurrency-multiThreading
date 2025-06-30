package com.shivam.thread.semaphore;

import java.util.concurrent.*;

public class RateLimiterDemo {

    // Allow only 3 requests per second
    private static final int PERMITS = 3;
    private static final Semaphore rateLimiter = new Semaphore(PERMITS);

    public static void main(String[] args) throws InterruptedException {

        int taskCount = 20;
        CountDownLatch latch = new CountDownLatch(taskCount);
        // Scheduled task to refill permits every 1 second
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            int released = PERMITS - rateLimiter.availablePermits();
            if(released > 0){
                rateLimiter.release(released);  // refill permits
                System.out.println("[Limiter] Refilled " + released + " permits");
            }
        }, 0, 1, TimeUnit.SECONDS);

        // Simulate 10 client requests per second

        ExecutorService clients = Executors.newFixedThreadPool(10);

        for (int i=1; i<=20; i++){
            int requestId = i;
//            try {
//                Thread.sleep(100); // simulate bursty traffic
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            clients.submit(() ->{
//               if (rateLimiter.tryAcquire()){   // tryAcquire() is non-blocking
//                   System.out.println("[Request-" + requestId + "] Allowed ✅");
                   try {
                       rateLimiter.acquire();
                       System.out.println("[Request-" + requestId + "] Allowed ✅");
                       Thread.sleep(300);
                   } catch (InterruptedException e){
                       Thread.currentThread().interrupt();
                   } finally {
                       // No release here — we refill in scheduler
                       latch.countDown(); // mark this task as completed
                   }
//               }else{
//                   // If a permit isn’t available, the thread immediately fails (denied request)
//                   System.out.println("[Request-" + requestId + "] Denied ❌ - Rate limit exceeded");
//               }
            });
        }

        try {
            latch.await(); // wait for all 20 tasks to finish
            System.out.println("✅ All tasks completed.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        clients.shutdown();
        scheduler.schedule(scheduler::shutdown, 5, TimeUnit.SECONDS); // stop after 5s

    }
}
