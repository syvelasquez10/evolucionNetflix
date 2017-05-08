// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

import java.util.PriorityQueue;

public final class NetworkLock
{
    public static final NetworkLock instance;
    private int highestPriority;
    private final Object lock;
    private final PriorityQueue<Integer> queue;
    
    static {
        instance = new NetworkLock();
    }
    
    private NetworkLock() {
        this.lock = new Object();
        this.queue = new PriorityQueue<Integer>();
        this.highestPriority = Integer.MAX_VALUE;
    }
    
    public void add(final int n) {
        synchronized (this.lock) {
            this.queue.add(n);
            this.highestPriority = Math.min(this.highestPriority, n);
        }
    }
    
    public void remove(int intValue) {
        synchronized (this.lock) {
            this.queue.remove(intValue);
            if (this.queue.isEmpty()) {
                intValue = Integer.MAX_VALUE;
            }
            else {
                intValue = this.queue.peek();
            }
            this.highestPriority = intValue;
            this.lock.notifyAll();
        }
    }
}
