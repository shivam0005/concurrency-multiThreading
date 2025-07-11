
# 🔍 What is AtomicInteger?

AtomicInteger is a class in java.util.concurrent.atomic package that provides:

    Lock-free
    Thread-safe
    Atomic operations on int values.

It uses low-level CPU instructions like Compare-And-Swap (CAS) under the hood to ensure 
that operations like increment, decrement, etc., happen atomically.


# 💣 What is an Atomic Operation?

An atomic operation is one that is:

✅ Indivisible

It either happens completely, or not at all — there is no in-between state visible to other threads.


# 🧮 Example: count++ — Is it atomic?

No ❌ — this is not atomic.

Behind the scenes, this single line:

`count++;`

Is actually:

`int temp = count;   // read
temp = temp + 1;    // modify
count = temp;       // write
`

That’s 3 separate operations:

A thread can be interrupted in the middle.

Another thread may read or update count before the first finishes.

That’s how you get race conditions.


# 🔧 Common Methods

| Method                         | Description                                     |
| ------------------------------ | ----------------------------------------------- |
| `get()`                        | Returns the current value                       |
| `set(int newValue)`            | Sets the value                                  |
| `incrementAndGet()`            | Atomically increments and returns result        |
| `decrementAndGet()`            | Atomically decrements and returns result        |
| `addAndGet(int delta)`         | Atomically adds and returns result              |
| `compareAndSet(expected, new)` | Atomically sets to new if value equals expected |


