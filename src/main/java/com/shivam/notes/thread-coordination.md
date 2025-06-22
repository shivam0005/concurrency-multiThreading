# ⏱️ Thread.sleep(), Thread.yield(), and Thread.join()

These are coordination methods that help you control how threads behave and interact.

**💤 1. Thread.sleep(milliseconds)**

🔸 What it does:

    Pauses the current thread for the given time.
    The thread moves to TIMED_WAITING state.
    Does NOT release locks if it’s in a synchronized block.

⚠️ Note:

    It's a static method → it always sleeps the currently running thread, not the thread object it’s called on.
    Throws InterruptedException, so you must handle it.

`Thread t = new Thread(() -> System.out.println("Running"));
t.sleep(1000); // ❌ This does NOT pause thread 't'
`

**Why?**

    Even though you write t.sleep(1000), this line of code is being executed by the main thread,
    so main thread sleeps, not thread t.
    
    Even though it's syntactically allowed to write t.sleep(1000), it does not mean thread t sleeps. 
    It’s just like writing Thread.sleep(1000) — and it affects whoever calls it.


# 🔁 2. Thread.yield()

**🔸 What it does:**

    Suggests to the thread scheduler: "I’m willing to pause, let others run."
    The thread moves from RUNNING → RUNNABLE, giving other threads a chance to execute.
    Not guaranteed to do anything — just a hint.

**✅ Useful when:**

    You want to improve fairness or give a chance to low-priority threads.
    But avoid overusing — let the scheduler do its job.


# 🔗 3. Thread.join()

**🔸 What it does:**

    Makes the calling thread wait for the thread it's joining on to finish execution.
    Moves the calling thread into WAITING or TIMED_WAITING (if timeout is given).

**🔧 Usage:**

`Thread t = new Thread(() -> {
// some task
});
t.start();
t.join(); // Main thread waits here until t finishes
`


# 🧠 Summary Table

| Method    | Blocks Current Thread? | State Transition          | Guarantees Execution Order?   |
 | --------- | ---------------------- | ------------------------- | ---------------------------   |
 | `sleep()` | ✅ Yes                 | `RUNNING → TIMED_WAITING` | ❌ No                        |
 | `yield()` | ✅ Maybe               | `RUNNING → RUNNABLE`      | ❌ No                        |
 | `join()`  | ✅ Yes                 | `RUNNING → WAITING`       | ✅ Yes (waits for another)   |


