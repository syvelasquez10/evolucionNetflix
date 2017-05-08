// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.react.bridge.MemoryPressure;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.bridge.NativeModule;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.JavaScriptModule;
import android.content.res.AssetManager;
import java.util.Collection;
import com.facebook.react.bridge.queue.MessageQueueThread;
import java.util.Iterator;
import com.facebook.systrace.Systrace;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.queue.QueueThreadExceptionHandler;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import com.facebook.soloader.SoLoader;
import com.facebook.systrace.TraceListener;
import com.facebook.react.bridge.queue.ReactQueueConfigurationImpl;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.ExecutorToken;
import com.facebook.react.bridge.JavaScriptModuleRegistry;
import java.util.ArrayList;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.CatalystInstance;
import java.lang.ref.WeakReference;

class CatalystInstanceImpl$BridgeCallback implements ReactCallback
{
    private final WeakReference<CatalystInstanceImpl> mOuter;
    
    public CatalystInstanceImpl$BridgeCallback(final CatalystInstanceImpl catalystInstanceImpl) {
        this.mOuter = new WeakReference<CatalystInstanceImpl>(catalystInstanceImpl);
    }
    
    @Override
    public void decrementPendingJSCalls() {
        final CatalystInstanceImpl catalystInstanceImpl = this.mOuter.get();
        if (catalystInstanceImpl != null) {
            catalystInstanceImpl.decrementPendingJSCalls();
        }
    }
    
    @Override
    public void incrementPendingJSCalls() {
        final CatalystInstanceImpl catalystInstanceImpl = this.mOuter.get();
        if (catalystInstanceImpl != null) {
            catalystInstanceImpl.incrementPendingJSCalls();
        }
    }
    
    @Override
    public void onBatchComplete() {
        final CatalystInstanceImpl catalystInstanceImpl = this.mOuter.get();
        if (catalystInstanceImpl != null) {
            catalystInstanceImpl.mJavaRegistry.onBatchComplete();
        }
    }
    
    @Override
    public void onNativeException(final Exception ex) {
        final CatalystInstanceImpl catalystInstanceImpl = this.mOuter.get();
        if (catalystInstanceImpl != null) {
            catalystInstanceImpl.onNativeException(ex);
        }
    }
}
