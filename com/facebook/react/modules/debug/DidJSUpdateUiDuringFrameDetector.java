// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.debug;

import com.facebook.react.common.LongArray;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;

public class DidJSUpdateUiDuringFrameDetector implements NotThreadSafeBridgeIdleDebugListener, NotThreadSafeViewHierarchyUpdateDebugListener
{
    private final LongArray mTransitionToBusyEvents;
    private final LongArray mTransitionToIdleEvents;
    private final LongArray mViewHierarchyUpdateEnqueuedEvents;
    private final LongArray mViewHierarchyUpdateFinishedEvents;
    private volatile boolean mWasIdleAtEndOfLastFrame;
    
    public DidJSUpdateUiDuringFrameDetector() {
        this.mTransitionToIdleEvents = LongArray.createWithInitialCapacity(20);
        this.mTransitionToBusyEvents = LongArray.createWithInitialCapacity(20);
        this.mViewHierarchyUpdateEnqueuedEvents = LongArray.createWithInitialCapacity(20);
        this.mViewHierarchyUpdateFinishedEvents = LongArray.createWithInitialCapacity(20);
        this.mWasIdleAtEndOfLastFrame = true;
    }
    
    private static void cleanUp(final LongArray longArray, final long n) {
        final int n2 = 0;
        final int size = longArray.size();
        int i = 0;
        int n3 = 0;
        while (i < size) {
            int n4 = n3;
            if (longArray.get(i) < n) {
                n4 = n3 + 1;
            }
            ++i;
            n3 = n4;
        }
        if (n3 > 0) {
            for (int j = n2; j < size - n3; ++j) {
                longArray.set(j, longArray.get(j + n3));
            }
            longArray.dropTail(n3);
        }
    }
    
    private boolean didEndFrameIdle(long lastEventBetweenTimestamps, final long n) {
        final long lastEventBetweenTimestamps2 = getLastEventBetweenTimestamps(this.mTransitionToIdleEvents, lastEventBetweenTimestamps, n);
        lastEventBetweenTimestamps = getLastEventBetweenTimestamps(this.mTransitionToBusyEvents, lastEventBetweenTimestamps, n);
        if (lastEventBetweenTimestamps2 == -1L && lastEventBetweenTimestamps == -1L) {
            return this.mWasIdleAtEndOfLastFrame;
        }
        return lastEventBetweenTimestamps2 > lastEventBetweenTimestamps;
    }
    
    private static long getLastEventBetweenTimestamps(final LongArray longArray, final long n, final long n2) {
        long n3 = -1L;
        long n4;
        for (int i = 0; i < longArray.size(); ++i, n3 = n4) {
            final long value = longArray.get(i);
            if (value >= n && value < n2) {
                n4 = value;
            }
            else {
                n4 = n3;
                if (value >= n2) {
                    break;
                }
            }
        }
        return n3;
    }
    
    private static boolean hasEventBetweenTimestamps(final LongArray longArray, final long n, final long n2) {
        final boolean b = false;
        int n3 = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n3 >= longArray.size()) {
                break;
            }
            final long value = longArray.get(n3);
            if (value >= n && value < n2) {
                b2 = true;
                break;
            }
            ++n3;
        }
        return b2;
    }
    
    public boolean getDidJSHitFrameAndCleanup(final long n, final long n2) {
        boolean b = true;
        synchronized (this) {
            final boolean hasEventBetweenTimestamps = hasEventBetweenTimestamps(this.mViewHierarchyUpdateFinishedEvents, n, n2);
            final boolean didEndFrameIdle = this.didEndFrameIdle(n, n2);
            if (!hasEventBetweenTimestamps && (!didEndFrameIdle || hasEventBetweenTimestamps(this.mViewHierarchyUpdateEnqueuedEvents, n, n2))) {
                b = false;
            }
            cleanUp(this.mTransitionToIdleEvents, n2);
            cleanUp(this.mTransitionToBusyEvents, n2);
            cleanUp(this.mViewHierarchyUpdateEnqueuedEvents, n2);
            cleanUp(this.mViewHierarchyUpdateFinishedEvents, n2);
            this.mWasIdleAtEndOfLastFrame = didEndFrameIdle;
            return b;
        }
    }
    
    @Override
    public void onTransitionToBridgeBusy() {
        synchronized (this) {
            this.mTransitionToBusyEvents.add(System.nanoTime());
        }
    }
    
    @Override
    public void onTransitionToBridgeIdle() {
        synchronized (this) {
            this.mTransitionToIdleEvents.add(System.nanoTime());
        }
    }
    
    @Override
    public void onViewHierarchyUpdateEnqueued() {
        synchronized (this) {
            this.mViewHierarchyUpdateEnqueuedEvents.add(System.nanoTime());
        }
    }
    
    @Override
    public void onViewHierarchyUpdateFinished() {
        synchronized (this) {
            this.mViewHierarchyUpdateFinishedEvents.add(System.nanoTime());
        }
    }
}
