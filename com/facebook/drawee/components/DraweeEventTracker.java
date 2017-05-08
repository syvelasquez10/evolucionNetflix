// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.components;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.Queue;

public class DraweeEventTracker
{
    private final Queue<DraweeEventTracker$Event> mEventQueue;
    
    public DraweeEventTracker() {
        this.mEventQueue = new ArrayBlockingQueue<DraweeEventTracker$Event>(20);
    }
    
    public void recordEvent(final DraweeEventTracker$Event draweeEventTracker$Event) {
        if (this.mEventQueue.size() + 1 > 20) {
            this.mEventQueue.poll();
        }
        this.mEventQueue.add(draweeEventTracker$Event);
    }
    
    @Override
    public String toString() {
        return this.mEventQueue.toString();
    }
}
