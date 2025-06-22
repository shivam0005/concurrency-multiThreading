# 🔄 wait(), notify(), notifyAll()

Threads often need to coordinate with each other.

For example:

    A consumer must wait until a producer puts something in the queue.
    Without wait()/notify(), you’d either use busy-waiting (CPU waste) or complex flags/polling.

Java provides built-in mechanisms to avoid that, using:

`| Method        | Purpose                             |
| ------------- | ----------------------------------- |
| `wait()`      | Waits until another thread notifies |
| `notify()`    | Wakes one waiting thread            |
| `notifyAll()` | Wakes all waiting threads           |
`


# 🔐 2. Basic Rules

✅ Must be called on the shared object 

✅ Must be inside a synchronized block or method

✅ wait() → releases the lock and waits

✅ notify() → wakes one thread (random choice)

✅ notifyAll() → wakes all waiting threads



# 🧱 3. Method Descriptions

**🔸 wait()**

`obj.wait();
`

* Puts the current thread into WAITING state
* Releases the lock so another thread can take action
* The thread stays paused until notify() or notifyAll() is called


**🔸 notify()**

`obj.notify();
`

* Wakes up one random thread waiting on the object
* That thread must re-acquire the lock before continuing


**🔸 notifyAll()**

`obj.notifyAll();
`

* Wakes all waiting threads — only one can proceed at a time


# ✅ 4. Real Example Use Case

Producer-Consumer Problem

Let’s say:
    
    A producer thread adds data to a list
    A consumer thread waits until data is present

Using wait() and notify(), we can coordinate them.

