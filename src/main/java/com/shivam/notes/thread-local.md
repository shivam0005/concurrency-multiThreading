# 🧠 What is ThreadLocal?

ThreadLocal<T> provides thread-local storage:

➡️ Each thread has its own, isolated copy of a variable, even though it's shared across threads.

This means:

    No two threads will see each other's value
    No synchronization needed


# 🔁 How is it different from a static or instance variable?


| Type          | Shared Across Threads? | Needs Sync? | Example Use       |
| ------------- | ---------------------- | ----------- | ----------------- |
| `static`      | ✅ Yes                  | ✅ Often     | Shared config     |
| Instance var  | ✅ Yes (if shared)      | ✅ Often     | Service state     |
| `ThreadLocal` | ❌ No – thread-isolated | ❌ No        | Request-scoped ID |



# ✅ Key Methods

    ThreadLocal<T> tl = ThreadLocal.withInitial(() -> initialValue);
    tl.get();     // Get current thread's value
    tl.set(val);  // Set value for current thread
    tl.remove();  // Clean up (important in thread pools!)


# 📦 Use Cases

| Scenario                    | Why `ThreadLocal` Helps                           |
| --------------------------- | ------------------------------------------------- |
| Logging correlation ID      | Each thread holds a request ID without passing it |
| Web requests in thread pool | Store user/session/request data                   |
| Date formatting             | `SimpleDateFormat` is not thread-safe             |
| DB connection per thread    | Isolate resources                                 |


# 🧬 Under the hood (brief)


Internally:

    Each Thread object has a map of ThreadLocal → value
    So the value is tied to the current thread, not the ThreadLocal object

That’s why it avoids race conditions naturally.



# ⚠️ Pitfall: Memory Leaks in Thread Pools

In long-lived threads (like in servlet containers or executor pools), if you forget to call:

    threadLocal.remove();

Then the thread holds onto stale objects = memory leak.

✅ Always remove() at the end of task if using a thread pool.



