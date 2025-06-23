# 🔐 ReadWriteLock

ReadWriteLock is a concurrency utility that allows:

* Multiple threads to read simultaneously. 
* But only one thread to write, and when writing:
  * All readers and other writers are blocked

This is useful for read-heavy applications, like caches, configuration stores, or data registries — 
where reads are frequent and writes are rare.


# 🔁 Why Not Just Use synchronized or ReentrantLock?

With synchronized or ReentrantLock:

| Operation | Lock Held         | Concurrent Readers Allowed?      |
| --------- | ----------------- | -------------------------------- |
| Read      | ✅ Yes (shared)    | ❌ No — readers block each other  |
| Write     | ✅ Yes (exclusive) | ❌ No — only one thread at a time |

    So even if 10 threads only want to read — they'll still block each other.


# ✅ ReadWriteLock Solves This

With ReadWriteLock:

| Operation | Lock Acquired              | Can Multiple Threads Do This? |
| --------- | -------------------------- | ----------------------------- |
| Read      | **Read lock (shared)**     | ✅ Yes (if no writer active)   |
| Write     | **Write lock (exclusive)** | ❌ No — exclusive access       |



# 🔧 Interface and Implementation

* ReadWriteLock is an interface.
* ReentrantReadWriteLock is its most common implementation.


`ReadWriteLock rwLock = new ReentrantReadWriteLock();`

`Lock readLock = rwLock.readLock();`

`Lock writeLock = rwLock.writeLock();`


# 🔍 How It Works

* Many readers can hold readLock at the same time. 
* Only one writer can hold writeLock. 
* If a writer is waiting:
  * New readers are blocked (so writer doesn’t starve). 
* If readers are active:
  * Writer has to wait until all readers release readLock


# 🧠 Key Advantages

| Feature                 | Benefit                                    |
| ----------------------- | ------------------------------------------ |
| Concurrency for readers | Improves performance in read-heavy apps    |
| Fairness option         | Can configure to prevent writer starvation |
| Separates read/write    | Clean code structure                       |



# ⚠️ When Not to Use It

* If your reads/writes are very short-lived, the overhead may not be worth it.
* If writes are frequent, there's little benefit — you're still mostly blocking.



# ✅ Summary Table

| Lock Type     | Threads Allowed Concurrently | When Blocked                              |
| ------------- | ---------------------------- | ----------------------------------------- |
| `readLock()`  | ✅ Multiple readers           | Blocked if a writer is writing or waiting |
| `writeLock()` | ❌ One writer only            | Blocked if any readers or writer present  |


