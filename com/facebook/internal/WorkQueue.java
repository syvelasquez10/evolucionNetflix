// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import com.facebook.Settings;
import java.util.concurrent.Executor;

class WorkQueue
{
    private final Executor executor;
    private final int maxConcurrent;
    private int runningCount;
    private WorkQueue$WorkNode runningJobs;
    private final Object workLock;
    
    WorkQueue() {
        this(8);
    }
    
    WorkQueue(final int n) {
        this(n, Settings.getExecutor());
    }
    
    WorkQueue(final int maxConcurrent, final Executor executor) {
        this.workLock = new Object();
        this.runningJobs = null;
        this.runningCount = 0;
        this.maxConcurrent = maxConcurrent;
        this.executor = executor;
    }
}
