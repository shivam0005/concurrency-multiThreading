# ⚙️ Java Concurrency & Multithreading – Notes & Hands-On

This repository is a deep-dive into Java concurrency and multithreading — including **notes, hands-on code and real-world examples**. 
It's meant to serve as a personal learning resource as well as a reference for anyone looking to understand how Java handles multi-threaded programming.

> ✅ Every concept is backed by code.  
> ✅ Focus is on **clarity, intuition, and practical usage**.

---

## 📚 Topics Covered

### 🧵 Thread Basics
- Thread creation: `Thread`, `Runnable`, `ExecutorService`
- Thread lifecycle, states, priorities, and daemon threads
- Thread coordination: `sleep()`, `yield()`, `join()`

### 🔐 Synchronization & Locks
- `synchronized` vs `ReentrantLock`
- Intrinsic lock vs explicit lock
- `wait`, `notify`, `notifyAll` vs `Condition.await()/signal()`
- Producer-consumer using:
  - `synchronized` + wait/notify
  - `Lock` + `Condition`

### ⚙️ Concurrency Utilities
- `CountDownLatch`  
- `Semaphore`  
- `CyclicBarrier` (optional)
- **Rate Limiter** using `Semaphore` + `ScheduledExecutorService`
- `ReadWriteLock` vs `ReentrantLock` (benchmarked)

### 🧬 Thread Safety & Visibility
- `volatile` and memory visibility
- `ThreadLocal` for per-thread data isolation
- Atomic operations:
  - `AtomicInteger`, `AtomicLong`
  - Compare-And-Swap (CAS)
  - `LongAdder` vs `AtomicInteger`

### 🧵 Executor Framework & Thread Pools
- `ExecutorService`, `FixedThreadPool`, `CachedThreadPool`
- `Callable`, `Future`, `CompletionService`
- ForkJoin framework basics
- ThreadPool tuning, rejection policies, queue strategies

