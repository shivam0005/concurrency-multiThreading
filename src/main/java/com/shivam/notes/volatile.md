
# 🔍 What is volatile in Java?

The volatile keyword in Java is used to mark a variable as stored in main memory, and ensures that 
reads/writes to that variable are always directly visible to all threads.


# 📌 Key Concepts

**1. 🔄 Memory Visibility Problem**

In Java, each thread can cache variables in its own local memory (thread stack or CPU cache). 
This may cause one thread’s update to not be visible to another.

    // Thread A
    flag = true;

    // Thread B
    while (!flag) {
    // might loop forever if it doesn't see updated value!
    }

Without volatile, Thread B may never see the update to flag.


**2. ✅ What volatile does**

When a variable is declared volatile:

    It guarantees visibility, not atomicity.

    When one thread updates a volatile variable, other threads see the change immediately.

    Reads/writes go straight to and from main memory, avoiding thread-local caches.


# ⚠️ Important Limitations

❌ Volatile is not for atomic operations:

    volatile int count = 0;

    public void increment() {
        count++; // NOT atomic! (read → modify → write)
    }

Use AtomicInteger instead for atomic updates.


# ✅ When to use volatile?

Use it when:

* One thread writes, many threads read 
* You only need visibility, not atomicity 
* You’re using a "status flag", e.g., stop, done, running


# ❌ When not to use:

* When multiple threads both read and update a variable 
* When operation involves compound actions (like check then act)


# 🧠 Under the Hood (Happens-Before Guarantee)

The Java Memory Model ensures:

* A write to a volatile variable happens-before every subsequent read of that same variable.

So once one thread updates a volatile field, all other threads reading it will observe the updated value.


# ✅ Summary Table

| Feature            | `volatile`                  |
| ------------------ | --------------------------- |
| Visibility         | ✅ Guaranteed                |
| Atomicity          | ❌ Not guaranteed            |
| Use Case           | Flags, status variables     |
| Synchronization    | Light-weight memory barrier |
| Performance        | Faster than `synchronized`  |
| Multi-thread Write | ❌ Avoid if multiple writers |







