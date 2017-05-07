// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.Settings;
import java.util.concurrent.Executor;

class WorkQueue
{
    public static final int DEFAULT_MAX_CONCURRENT = 8;
    private final Executor executor;
    private final int maxConcurrent;
    private WorkQueue$WorkNode pendingJobs;
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
    
    private void execute(final WorkQueue$WorkNode workQueue$WorkNode) {
        this.executor.execute(new WorkQueue$1(this, workQueue$WorkNode));
    }
    
    private void finishItemAndStartNew(WorkQueue$WorkNode workQueue$WorkNode) {
        final WorkQueue$WorkNode workQueue$WorkNode2 = null;
        final Object workLock = this.workLock;
        // monitorenter(workLock)
        Label_0035: {
            if (workQueue$WorkNode == null) {
                break Label_0035;
            }
            try {
                this.runningJobs = workQueue$WorkNode.removeFromList(this.runningJobs);
                --this.runningCount;
                workQueue$WorkNode = workQueue$WorkNode2;
                if (this.runningCount < this.maxConcurrent) {
                    final WorkQueue$WorkNode pendingJobs = this.pendingJobs;
                    if ((workQueue$WorkNode = pendingJobs) != null) {
                        this.pendingJobs = pendingJobs.removeFromList(this.pendingJobs);
                        this.runningJobs = pendingJobs.addToList(this.runningJobs, false);
                        ++this.runningCount;
                        pendingJobs.setIsRunning(true);
                        workQueue$WorkNode = pendingJobs;
                    }
                }
                // monitorexit(workLock)
                if (workQueue$WorkNode != null) {
                    this.execute(workQueue$WorkNode);
                }
            }
            finally {
            }
            // monitorexit(workLock)
        }
    }
    
    private void startItem() {
        this.finishItemAndStartNew(null);
    }
    
    WorkQueue$WorkItem addActiveWorkItem(final Runnable runnable) {
        return this.addActiveWorkItem(runnable, true);
    }
    
    WorkQueue$WorkItem addActiveWorkItem(final Runnable runnable, final boolean b) {
        final WorkQueue$WorkNode workQueue$WorkNode = new WorkQueue$WorkNode(this, runnable);
        synchronized (this.workLock) {
            this.pendingJobs = workQueue$WorkNode.addToList(this.pendingJobs, b);
            // monitorexit(this.workLock)
            this.startItem();
            return workQueue$WorkNode;
        }
    }
    
    void validate() {
        final Object workLock = this.workLock;
        // monitorenter(workLock)
        int n = 0;
        int n2 = 0;
        try {
            if (this.runningJobs != null) {
                WorkQueue$WorkNode runningJobs = this.runningJobs;
                WorkQueue$WorkNode next;
                do {
                    runningJobs.verify(true);
                    n = n2 + 1;
                    next = runningJobs.getNext();
                    n2 = n;
                } while ((runningJobs = next) != this.runningJobs);
            }
            assert this.runningCount == n;
        }
        finally {
        }
        // monitorexit(workLock)
    }
    // monitorexit(workLock)
}
