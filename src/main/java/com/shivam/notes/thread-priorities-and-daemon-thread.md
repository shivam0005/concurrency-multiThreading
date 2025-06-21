# 🧵 1. Thread Priorities
    
`Each Java thread has a priority — an integer from 1 to 10 — that suggests to the thread scheduler 
how important the thread is.`

Java constants for priority:

    Thread.MIN_PRIORITY = 1  
    Thread.NORM_PRIORITY = 5 (default)  
    Thread.MAX_PRIORITY = 10  

The OS and JVM may or may not strictly follow priorities — it's just a hint.


# 🔸 How to Set/Get Priority:

    Thread t = new Thread(() -> System.out.println("Running"));
    t.setPriority(Thread.MAX_PRIORITY);
    System.out.println(t.getPriority()); // 10

📌 Best practice: Set priorities only when you have a strong use case. Don't rely on them for correctness.


# 🔸 Common Use Case

You might give higher priority to tasks that:

* Must run faster than others (e.g., UI thread, logging, alerts)
* Require quick CPU cycles in a compute-heavy environment

But for most backend apps, priority is rarely used.


# 👻 2. Daemon Threads

A daemon thread is a background thread that:

* Runs in the background (e.g., garbage collector, monitoring tasks)
* Doesn’t block JVM shutdown
* Is killed automatically when all user threads finish


**🔸 How to Make a Thread a Daemon**

// see DaemonAndPriority class inside thread.daemon package



