// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.components;

import com.facebook.common.internal.Preconditions;
import android.os.Looper;
import java.util.HashSet;
import android.os.Handler;
import java.util.Set;

public class DeferredReleaser
{
    private static DeferredReleaser sInstance;
    private final Set<DeferredReleaser$Releasable> mPendingReleasables;
    private final Handler mUiHandler;
    private final Runnable releaseRunnable;
    
    static {
        DeferredReleaser.sInstance = null;
    }
    
    public DeferredReleaser() {
        this.releaseRunnable = new DeferredReleaser$1(this);
        this.mPendingReleasables = new HashSet<DeferredReleaser$Releasable>();
        this.mUiHandler = new Handler(Looper.getMainLooper());
    }
    
    private static void ensureOnUiThread() {
        Preconditions.checkState(Looper.getMainLooper().getThread() == Thread.currentThread());
    }
    
    public static DeferredReleaser getInstance() {
        synchronized (DeferredReleaser.class) {
            if (DeferredReleaser.sInstance == null) {
                DeferredReleaser.sInstance = new DeferredReleaser();
            }
            return DeferredReleaser.sInstance;
        }
    }
    
    public void cancelDeferredRelease(final DeferredReleaser$Releasable deferredReleaser$Releasable) {
        ensureOnUiThread();
        this.mPendingReleasables.remove(deferredReleaser$Releasable);
    }
    
    public void scheduleDeferredRelease(final DeferredReleaser$Releasable deferredReleaser$Releasable) {
        ensureOnUiThread();
        if (this.mPendingReleasables.add(deferredReleaser$Releasable) && this.mPendingReleasables.size() == 1) {
            this.mUiHandler.post(this.releaseRunnable);
        }
    }
}
