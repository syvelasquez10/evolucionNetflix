// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.core;

import java.util.concurrent.ThreadFactory;

public class PriorityThreadFactory implements ThreadFactory
{
    private final int mThreadPriority;
    
    public PriorityThreadFactory(final int mThreadPriority) {
        this.mThreadPriority = mThreadPriority;
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        return new Thread(new PriorityThreadFactory$1(this, runnable));
    }
}
