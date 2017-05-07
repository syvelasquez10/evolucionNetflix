// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.os.SystemClock;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.List;

public abstract class EventQueue<T>
{
    public static final int MAX_TIME_THAN_EVENT_CAN_STAY_IN_QUEUE_MS = 300000;
    public static final int MIN_NUMBER_OF_EVENTS_TO_POST = 100;
    private String TAG;
    private List<T> mEventQueue;
    private List<FlushCriterion> mFlushCriteria;
    private long mLastTimeEventAddedInMs;
    private int mMaxNumberOfEvents;
    private long mMaxTimeToStayInQueueInMs;
    private AtomicBoolean mPaused;
    
    public EventQueue(final String s) {
        this(s, true, true);
    }
    
    public EventQueue(final String tag, final int mMaxNumberOfEvents, final long mMaxTimeToStayInQueueInMs, final boolean b, final boolean b2) {
        this.TAG = "nf_event";
        this.mMaxNumberOfEvents = 100;
        this.mMaxTimeToStayInQueueInMs = 300000L;
        this.mEventQueue = Collections.synchronizedList(new ArrayList<T>());
        this.mFlushCriteria = Collections.synchronizedList(new ArrayList<FlushCriterion>());
        this.mPaused = new AtomicBoolean(false);
        if (StringUtils.isNotEmpty(tag)) {
            this.TAG = tag;
        }
        if (mMaxNumberOfEvents <= 0) {
            throw new IllegalArgumentException("Number of events must be higher than 0!");
        }
        if (mMaxTimeToStayInQueueInMs <= 0L) {
            throw new IllegalArgumentException("Stay time in queue must be higher than 0!");
        }
        this.mMaxNumberOfEvents = mMaxNumberOfEvents;
        this.mMaxTimeToStayInQueueInMs = mMaxTimeToStayInQueueInMs;
        if (b) {
            this.mFlushCriteria.add((FlushCriterion)new QueueSizeFlushCriterion());
        }
        if (b2) {
            this.mFlushCriteria.add((FlushCriterion)new TimeInQueueFlushCriterion());
        }
    }
    
    public EventQueue(final String tag, final boolean b, final boolean b2) {
        this.TAG = "nf_event";
        this.mMaxNumberOfEvents = 100;
        this.mMaxTimeToStayInQueueInMs = 300000L;
        this.mEventQueue = Collections.synchronizedList(new ArrayList<T>());
        this.mFlushCriteria = Collections.synchronizedList(new ArrayList<FlushCriterion>());
        this.mPaused = new AtomicBoolean(false);
        if (StringUtils.isNotEmpty(tag)) {
            this.TAG = tag;
        }
        if (b) {
            this.mFlushCriteria.add((FlushCriterion)new QueueSizeFlushCriterion());
        }
        if (b2) {
            this.mFlushCriteria.add((FlushCriterion)new TimeInQueueFlushCriterion());
        }
    }
    
    public void addFlushCriterion(final FlushCriterion flushCriterion) {
        if (flushCriterion == null) {
            return;
        }
        this.mFlushCriteria.add(flushCriterion);
    }
    
    protected abstract void doFlush(final List<T> p0);
    
    public void flushEvents() {
        synchronized (this) {
            final ArrayList<Object> list = new ArrayList<Object>();
            synchronized (this.mEventQueue) {
                list.addAll(this.mEventQueue);
                this.mEventQueue.clear();
                this.mLastTimeEventAddedInMs = 0L;
                // monitorexit(this.mEventQueue)
                this.doFlush((List<T>)list);
            }
        }
    }
    
    public boolean flushIfCriteriaIsFulfilled() {
        boolean b = false;
        if (this.shouldFlushQueue()) {
            b = true;
            this.flushEvents();
        }
        return b;
    }
    
    public void pauseDelivery() {
        this.mPaused.set(true);
    }
    
    public final void post(final T p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_1        
        //     3: ifnonnull       9
        //     6: aload_0        
        //     7: monitorexit    
        //     8: return         
        //     9: aload_0        
        //    10: getfield        com/netflix/mediaclient/util/EventQueue.mEventQueue:Ljava/util/List;
        //    13: astore_2       
        //    14: aload_2        
        //    15: monitorenter   
        //    16: aload_0        
        //    17: getfield        com/netflix/mediaclient/util/EventQueue.mEventQueue:Ljava/util/List;
        //    20: invokeinterface java/util/List.isEmpty:()Z
        //    25: ifeq            35
        //    28: aload_0        
        //    29: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //    32: putfield        com/netflix/mediaclient/util/EventQueue.mLastTimeEventAddedInMs:J
        //    35: aload_0        
        //    36: getfield        com/netflix/mediaclient/util/EventQueue.mEventQueue:Ljava/util/List;
        //    39: aload_1        
        //    40: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    45: pop            
        //    46: aload_2        
        //    47: monitorexit    
        //    48: aload_0        
        //    49: invokevirtual   com/netflix/mediaclient/util/EventQueue.shouldFlushQueue:()Z
        //    52: ifeq            6
        //    55: aload_0        
        //    56: invokevirtual   com/netflix/mediaclient/util/EventQueue.flushEvents:()V
        //    59: goto            6
        //    62: astore_1       
        //    63: aload_0        
        //    64: monitorexit    
        //    65: aload_1        
        //    66: athrow         
        //    67: astore_1       
        //    68: aload_2        
        //    69: monitorexit    
        //    70: aload_1        
        //    71: athrow         
        //    Signature:
        //  (TT;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  9      16     62     67     Any
        //  16     35     67     72     Any
        //  35     48     67     72     Any
        //  48     59     62     67     Any
        //  68     70     67     72     Any
        //  70     72     62     67     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0035:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean removeFlushCriterion(final FlushCriterion flushCriterion) {
        return flushCriterion != null && this.mFlushCriteria.remove(flushCriterion);
    }
    
    public void resumeDelivery(final boolean b) {
        this.mPaused.set(false);
        if (b) {
            this.flushEvents();
        }
    }
    
    public boolean shouldFlushQueue() {
        if (this.mPaused.get()) {
            Log.d(this.TAG, "Paused state:: we can not flash events");
        }
        else {
            if (this.mFlushCriteria.size() <= 0) {
                Log.d(this.TAG, "No rules to flush queue, go and flush queue");
                return true;
            }
            if (Log.isLoggable(this.TAG, 3)) {
                Log.d(this.TAG, "Custom rules to flush queue found: " + this.mFlushCriteria.size());
            }
            final Iterator<FlushCriterion> iterator = this.mFlushCriteria.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().shouldFlushQueue(this.mEventQueue.size(), this.mLastTimeEventAddedInMs)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public interface FlushCriterion
    {
        boolean shouldFlushQueue(final int p0, final long p1);
    }
    
    private class QueueSizeFlushCriterion implements FlushCriterion
    {
        @Override
        public boolean shouldFlushQueue(final int n, final long n2) {
            if (EventQueue.this.mEventQueue.size() >= EventQueue.this.mMaxNumberOfEvents) {
                if (Log.isLoggable(EventQueue.this.TAG, 3)) {
                    Log.d(EventQueue.this.TAG, "Posting events: Current number of events in outgoing queue is " + EventQueue.this.mEventQueue.size() + " and it is equal or higher than treshold of " + EventQueue.this.mMaxNumberOfEvents);
                }
                return true;
            }
            if (Log.isLoggable(EventQueue.this.TAG, 3)) {
                Log.d(EventQueue.this.TAG, "Current number of events in outgoing queue is " + EventQueue.this.mEventQueue.size() + " and it is less than treshold of " + EventQueue.this.mMaxNumberOfEvents);
            }
            return false;
        }
    }
    
    private class TimeInQueueFlushCriterion implements FlushCriterion
    {
        @Override
        public boolean shouldFlushQueue(final int n, final long n2) {
            final long n3 = SystemClock.elapsedRealtime() - n2;
            if (n2 > 0L && n3 > EventQueue.this.mMaxTimeToStayInQueueInMs) {
                if (Log.isLoggable(EventQueue.this.TAG, 3)) {
                    Log.d(EventQueue.this.TAG, "Posting events: older event in queue was posted [ms] " + n3 + " and that triggers time of stay in queue criteria of " + EventQueue.this.mMaxTimeToStayInQueueInMs);
                }
                return true;
            }
            if (Log.isLoggable(EventQueue.this.TAG, 3)) {
                Log.d(EventQueue.this.TAG, "Posting events: older event in queue was posted [ms] " + n3 + " and that does NOT triggers time of stay in queue criteria of " + EventQueue.this.mMaxTimeToStayInQueueInMs);
            }
            return false;
        }
    }
}
