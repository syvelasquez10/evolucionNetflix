// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public interface CatalystInstance extends MemoryPressureListener
{
    void addBridgeIdleDebugListener(final NotThreadSafeBridgeIdleDebugListener p0);
    
    @DoNotStrip
    void callFunction(final ExecutorToken p0, final String p1, final String p2, final NativeArray p3);
    
    void destroy();
    
     <T extends JavaScriptModule> T getJSModule(final ExecutorToken p0, final Class<T> p1);
    
     <T extends JavaScriptModule> T getJSModule(final Class<T> p0);
    
     <T extends NativeModule> T getNativeModule(final Class<T> p0);
    
    ReactQueueConfiguration getReactQueueConfiguration();
    
    String getSourceURL();
    
    void initialize();
    
    @DoNotStrip
    void invokeCallback(final ExecutorToken p0, final int p1, final NativeArray p2);
    
    boolean isDestroyed();
    
    void removeBridgeIdleDebugListener(final NotThreadSafeBridgeIdleDebugListener p0);
    
    void runJSBundle();
}
