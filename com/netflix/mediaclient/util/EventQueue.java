// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.util.Iterator;
import com.netflix.mediaclient.Log;
import android.os.SystemClock;
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
    private List<EventQueue$FlushCriterion> mFlushCriteria;
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
        this.mFlushCriteria = Collections.synchronizedList(new ArrayList<EventQueue$FlushCriterion>());
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
            this.mFlushCriteria.add(new EventQueue$QueueSizeFlushCriterion(this, null));
        }
        if (b2) {
            this.mFlushCriteria.add(new EventQueue$TimeInQueueFlushCriterion(this, null));
        }
    }
    
    public EventQueue(final String tag, final boolean b, final boolean b2) {
        this.TAG = "nf_event";
        this.mMaxNumberOfEvents = 100;
        this.mMaxTimeToStayInQueueInMs = 300000L;
        this.mEventQueue = Collections.synchronizedList(new ArrayList<T>());
        this.mFlushCriteria = Collections.synchronizedList(new ArrayList<EventQueue$FlushCriterion>());
        this.mPaused = new AtomicBoolean(false);
        if (StringUtils.isNotEmpty(tag)) {
            this.TAG = tag;
        }
        if (b) {
            this.mFlushCriteria.add(new EventQueue$QueueSizeFlushCriterion(this, null));
        }
        if (b2) {
            this.mFlushCriteria.add(new EventQueue$TimeInQueueFlushCriterion(this, null));
        }
    }
    
    public void addFlushCriterion(final EventQueue$FlushCriterion eventQueue$FlushCriterion) {
        if (eventQueue$FlushCriterion == null) {
            return;
        }
        this.mFlushCriteria.add(eventQueue$FlushCriterion);
    }
    
    protected abstract void doFlush(final List<T> p0, final boolean p1);
    
    public void flushEvents(final boolean b) {
        synchronized (this) {
            final ArrayList<Object> list = new ArrayList<Object>();
            synchronized (this.mEventQueue) {
                list.addAll(this.mEventQueue);
                this.mEventQueue.clear();
                this.mLastTimeEventAddedInMs = 0L;
                // monitorexit(this.mEventQueue)
                this.doFlush((List<T>)list, b);
            }
        }
    }
    
    public boolean flushIfCriteriaIsFulfilled() {
        if (this.shouldFlushQueue()) {
            this.flushEvents(true);
            return true;
        }
        return false;
    }
    
    public void pauseDelivery() {
        this.mPaused.set(true);
    }
    
    public final boolean post(final T t) {
        boolean b = false;
        // monitorenter(this)
        if (t != null) {
            try {
                synchronized (this.mEventQueue) {
                    if (this.mEventQueue.isEmpty()) {
                        this.mLastTimeEventAddedInMs = SystemClock.elapsedRealtime();
                    }
                    this.mEventQueue.add(t);
                    // monitorexit(this.mEventQueue)
                    if (this.shouldFlushQueue()) {
                        this.flushEvents(true);
                        b = true;
                    }
                }
            }
            finally {
            }
            // monitorexit(this)
        }
        // monitorexit(this)
        return b;
    }
    
    public boolean removeFlushCriterion(final EventQueue$FlushCriterion eventQueue$FlushCriterion) {
        return eventQueue$FlushCriterion != null && this.mFlushCriteria.remove(eventQueue$FlushCriterion);
    }
    
    public void resumeDelivery(final boolean b) {
        this.mPaused.set(false);
        if (b) {
            this.flushEvents(true);
        }
    }
    
    public boolean shouldFlushQueue() {
        if (this.mPaused.get()) {
            Log.d(this.TAG, "Paused state:: we can not flash events");
            return false;
        }
        if (this.mFlushCriteria.size() > 0) {
            if (Log.isLoggable()) {
                Log.d(this.TAG, "Custom rules to flush queue found: " + this.mFlushCriteria.size());
            }
            final Iterator<EventQueue$FlushCriterion> iterator = this.mFlushCriteria.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().shouldFlushQueue(this.mEventQueue.size(), this.mLastTimeEventAddedInMs)) {
                    return true;
                }
            }
            return false;
        }
        Log.d(this.TAG, "No rules to flush queue, go and flush queue");
        return true;
    }
}
