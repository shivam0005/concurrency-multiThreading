
# 🧠 What is a Semaphore?

    A concurrency control tool that restricts the number of threads that can access a 
    shared resource at the same time.

It’s like a parking lot with 3 spots: only 3 cars (threads) can park at once. 
The rest must wait until a spot frees up.

**🔧 Class: java.util.concurrent.Semaphore**

`Semaphore semaphore = new Semaphore(int permits);`

| Method               | Purpose                                             |
| -------------------- | --------------------------------------------------- |
| `acquire()`          | Acquires a permit, blocks if none available         |
| `release()`          | Releases a permit, allows waiting thread to proceed |
| `tryAcquire()`       | Attempts to acquire without blocking                |
| `availablePermits()` | Returns number of permits available                 |


# ✅ Use Cases


| Scenario                               | How Semaphore Helps            |
| -------------------------------------- | ------------------------------ |
| Limit concurrent DB connections        | Only N threads allowed at once |
| Limit access to thread pool, file, etc | Prevent resource exhaustion    |
| Implement object pool / rate limiter   | One in, one out access         |


# ⚖️ When to Use Which?

| Pattern                       | When to Use                                                              |
| ----------------------------- | ------------------------------------------------------------------------ |
| `tryAcquire()` (non-blocking) | When you want to **reject or fallback** quickly (e.g., API throttling)   |
| `acquire()` (blocking)        | When you want to **throttle but still serve** (e.g., DB connection pool) |



