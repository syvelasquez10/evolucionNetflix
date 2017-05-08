// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.components;

import com.facebook.common.internal.Preconditions;
import android.os.Looper;
import java.util.HashSet;
import android.os.Handler;
import java.util.Set;
import java.util.Iterator;

class DeferredReleaser$1 implements Runnable
{
    final /* synthetic */ DeferredReleaser this$0;
    
    DeferredReleaser$1(final DeferredReleaser this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        ensureOnUiThread();
        final Iterator<DeferredReleaser$Releasable> iterator = this.this$0.mPendingReleasables.iterator();
        while (iterator.hasNext()) {
            iterator.next().release();
        }
        this.this$0.mPendingReleasables.clear();
    }
}
