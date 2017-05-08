// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge.queue;

import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public interface MessageQueueThread
{
    @DoNotStrip
    void assertIsOnThread();
    
    @DoNotStrip
     <T> Future<T> callOnQueue(final Callable<T> p0);
    
    @DoNotStrip
    boolean isOnThread();
    
    @DoNotStrip
    void quitSynchronous();
    
    @DoNotStrip
    void runOnQueue(final Runnable p0);
}
