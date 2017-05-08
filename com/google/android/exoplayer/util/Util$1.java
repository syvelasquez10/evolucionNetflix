// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

import java.util.concurrent.ThreadFactory;

final class Util$1 implements ThreadFactory
{
    final /* synthetic */ String val$threadName;
    
    Util$1(final String val$threadName) {
        this.val$threadName = val$threadName;
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        return new Thread(runnable, this.val$threadName);
    }
}
