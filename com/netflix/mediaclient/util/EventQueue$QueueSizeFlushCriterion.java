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

class EventQueue$QueueSizeFlushCriterion implements EventQueue$FlushCriterion
{
    final /* synthetic */ EventQueue this$0;
    
    private EventQueue$QueueSizeFlushCriterion(final EventQueue this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean shouldFlushQueue(final int n, final long n2) {
        if (this.this$0.mEventQueue.size() >= this.this$0.mMaxNumberOfEvents) {
            if (Log.isLoggable()) {
                Log.d(this.this$0.TAG, "Posting events: Current number of events in outgoing queue is " + this.this$0.mEventQueue.size() + " and it is equal or higher than treshold of " + this.this$0.mMaxNumberOfEvents);
            }
            return true;
        }
        if (Log.isLoggable()) {
            Log.d(this.this$0.TAG, "Current number of events in outgoing queue is " + this.this$0.mEventQueue.size() + " and it is less than treshold of " + this.this$0.mMaxNumberOfEvents);
        }
        return false;
    }
}
