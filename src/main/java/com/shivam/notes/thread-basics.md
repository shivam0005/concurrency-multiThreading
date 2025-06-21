# 🧵 1. What is a Thread?

`A Thread is a lightweight unit of execution within a process. In Java, 
every application has at least one thread: the main thread.
Threads allow concurrent execution of two or more parts of a program to maximize CPU utilization and performance.`

# 🧵 Ways to Create a Thread in Java:

1. Extending Thread class
2. Implementing Runnable interface
3. Using ExecutorService (preferred for modern Java)


# 🔁 2. Thread Lifecycle

A thread in Java goes through various states defined in java.lang.Thread.State enum.

`New → Runnable → Running → (Waiting/Timed Waiting/Blocked) → Terminated`


**🔹 NEW**
Thread object is created but start() hasn’t been called.

No system resources allocated yet.

`Thread t = new Thread();` // NEW


**🔹 RUNNABLE**
start() is called.

Thread is ready to run and is waiting to be picked by the CPU scheduler.

It's not actually running yet.

`t.start();` // moves to RUNNABLE


**🔹 RUNNING**
The thread scheduler picks this thread from the runnable pool.

Executes the run() method.

Java does not provide control to move thread explicitly to RUNNING.


**🔹 BLOCKED**
Thread wants to access a synchronized block/method but the lock is held by another thread.

Stays blocked until lock is released.


**🔹 WAITING**
Thread is waiting indefinitely for another thread to perform a specific action.

E.g., Object.wait(), Thread.join() (without timeout)


**🔹 TIMED_WAITING**
Thread waits for a specified amount of time.

E.g., sleep(time), join(time), wait(time)

`Thread.sleep(1000);` // TIMED_WAITING for 1 second


**🔹 TERMINATED (DEAD)**
Thread has completed execution or was aborted due to an error/exception.

Cannot be started again.


# 🔍 How to Get Thread State:

`Thread t = new Thread();`
`System.out.println(t.getState());` // Output: NEW

`t.start();`
`System.out.println(t.getState());` // Output: RUNNABLE or RUNNING


# ⚠️ Important Rules:

    ✅ setDaemon(true) must be called before start()
    ✅ Once started, a thread’s daemon status can’t be changed
    ✅ Daemon threads are terminated when the last non-daemon thread finishes
   


