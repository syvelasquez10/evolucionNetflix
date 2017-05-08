// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

import android.os.Process;
import android.os.HandlerThread;

public final class PriorityHandlerThread extends HandlerThread
{
    private final int priority;
    
    public PriorityHandlerThread(final String s, final int priority) {
        super(s);
        this.priority = priority;
    }
    
    public void run() {
        Process.setThreadPriority(this.priority);
        super.run();
    }
}
