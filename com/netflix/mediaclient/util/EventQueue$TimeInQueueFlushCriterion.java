// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.util.Iterator;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.os.SystemClock;

class EventQueue$TimeInQueueFlushCriterion implements EventQueue$FlushCriterion
{
    final /* synthetic */ EventQueue this$0;
    
    private EventQueue$TimeInQueueFlushCriterion(final EventQueue this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean shouldFlushQueue(final int n, final long n2) {
        final long n3 = SystemClock.elapsedRealtime() - n2;
        if (n2 > 0L && n3 > this.this$0.mMaxTimeToStayInQueueInMs) {
            if (Log.isLoggable(this.this$0.TAG, 3)) {
                Log.d(this.this$0.TAG, "Posting events: older event in queue was posted [ms] " + n3 + " and that triggers time of stay in queue criteria of " + this.this$0.mMaxTimeToStayInQueueInMs);
            }
            return true;
        }
        if (Log.isLoggable(this.this$0.TAG, 3)) {
            Log.d(this.this$0.TAG, "Posting events: older event in queue was posted [ms] " + n3 + " and that does NOT triggers time of stay in queue criteria of " + this.this$0.mMaxTimeToStayInQueueInMs);
        }
        return false;
    }
}
