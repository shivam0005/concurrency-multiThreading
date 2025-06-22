# 🧠 1. What Is ReentrantLock?

ReentrantLock is a class that provides explicit locking, unlike the synchronized 
keyword which uses implicit locking.

`Think of it as a powerful and flexible alternative to synchronized.`

It supports:

* Lock attempts with timeout 
* Interruptible lock acquisition 
* Fairness policy (first-come-first-served)
* Recursion (reentrant behavior)


# 🔁 2. Reentrancy: What Does It Mean?

A lock is reentrant if a thread that already holds the lock can acquire it again without getting blocked.

**🔄 Example:**

`lock.lock();
lock.lock(); // ✅ allowed (same thread)
lock.unlock();
lock.unlock(); // need to unlock as many times as you locked
`

This is helpful when one method calls another synchronized method in the same thread.


# 🔐 3. Fairness vs Non-Fair Lock

**Fair Lock:**

`Lock fairLock = new ReentrantLock(true);`

* Threads acquire lock in the order they requested
* Prevents starvation


**Non-Fair Lock (default):**

`Lock unfairLock = new ReentrantLock();`

* Lock may be acquired out of order
* Higher throughput, but risk of starvation


# ⚠️ 4. Must Always Unlock (Best Practice)


`lock.lock();
try {
// critical section
} finally {
lock.unlock(); // mandatory to avoid deadlock
}
`

    Unlike synchronized, ReentrantLock does not auto-release the lock when an exception occurs — you must use try-finally.



# 🔄 7. tryLock() — Avoid Blocking

`if (lock.tryLock()) {
try {
// do work
} finally {
lock.unlock();
}
} else {
// skip or retry later
}
`

**🔸 Use Case:**

    When you don’t want threads to block — just skip the critical section if busy




# 🔍 synchronized vs ReentrantLock: Real Differences

**✅ 1. Locking Style**

`| Feature         | `synchronized`                   | `ReentrantLock`                               |
| --------------- | -------------------------------- | --------------------------------------------- |
| **Type**        | Implicit lock (built-in keyword) | Explicit object (from `java.util.concurrent`) |
| **Lock/Unlock** | Managed by JVM                   | You must manually `lock()` and `unlock()`     |
`

**⚠️ 2. Manual Unlock Required**

* With synchronized, lock is automatically released even if exceptions occur.
* With ReentrantLock, you must manually unlock in a finally block — if you forget, it can lead to deadlocks.


**⏳ 3. Try Locking / Timeout**

`| Capability         | `synchronized` | `ReentrantLock`      |
| ------------------ | -------------- | -------------------- |
| Try acquiring lock | ❌ No           | ✅ `tryLock()`        |
| Wait with timeout  | ❌ No           | ✅ `tryLock(timeout)` |
`

    ✅ With ReentrantLock, you can attempt a lock, and take alternate action if it fails — very useful in real systems.


**🔕 4. Interruptible Locking**

`| Feature                                         | `synchronized` | `ReentrantLock`                  |
| ----------------------------------------------- | -------------- | -------------------------------- |
| Respond to `interrupt()` while waiting for lock | ❌ No           | ✅ Yes with `lockInterruptibly()` |
`

📌 Real Use:
    
    In thread pools or tasks that may timeout, interruptible locks let you cancel threads cleanly.






