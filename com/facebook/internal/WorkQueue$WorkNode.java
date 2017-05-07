// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import com.facebook.Settings;
import java.util.concurrent.Executor;

class WorkQueue$WorkNode implements WorkQueue$WorkItem
{
    private final Runnable callback;
    private boolean isRunning;
    private WorkQueue$WorkNode next;
    private WorkQueue$WorkNode prev;
    final /* synthetic */ WorkQueue this$0;
    
    static {
        $assertionsDisabled = !WorkQueue.class.desiredAssertionStatus();
    }
    
    WorkQueue$WorkNode(final WorkQueue this$0, final Runnable callback) {
        this.this$0 = this$0;
        this.callback = callback;
    }
    
    WorkQueue$WorkNode addToList(WorkQueue$WorkNode next, final boolean b) {
        assert this.next == null;
        assert this.prev == null;
        if (next == null) {
            this.prev = this;
            this.next = this;
            next = this;
        }
        else {
            this.next = next;
            this.prev = next.prev;
            final WorkQueue$WorkNode next2 = this.next;
            this.prev.next = this;
            next2.prev = this;
        }
        if (b) {
            return this;
        }
        return next;
    }
    
    @Override
    public boolean cancel() {
        synchronized (this.this$0.workLock) {
            if (!this.isRunning()) {
                this.this$0.pendingJobs = this.removeFromList(this.this$0.pendingJobs);
                return true;
            }
            return false;
        }
    }
    
    Runnable getCallback() {
        return this.callback;
    }
    
    WorkQueue$WorkNode getNext() {
        return this.next;
    }
    
    @Override
    public boolean isRunning() {
        return this.isRunning;
    }
    
    @Override
    public void moveToFront() {
        synchronized (this.this$0.workLock) {
            if (!this.isRunning()) {
                this.this$0.pendingJobs = this.removeFromList(this.this$0.pendingJobs);
                this.this$0.pendingJobs = this.addToList(this.this$0.pendingJobs, true);
            }
        }
    }
    
    WorkQueue$WorkNode removeFromList(final WorkQueue$WorkNode workQueue$WorkNode) {
        assert this.next != null;
        assert this.prev != null;
        WorkQueue$WorkNode next;
        if ((next = workQueue$WorkNode) == this) {
            if (this.next == this) {
                next = null;
            }
            else {
                next = this.next;
            }
        }
        this.next.prev = this.prev;
        this.prev.next = this.next;
        this.prev = null;
        this.next = null;
        return next;
    }
    
    void setIsRunning(final boolean isRunning) {
        this.isRunning = isRunning;
    }
    
    void verify(final boolean b) {
        assert this.prev.next == this;
        assert this.next.prev == this;
        assert this.isRunning() == b;
    }
}
