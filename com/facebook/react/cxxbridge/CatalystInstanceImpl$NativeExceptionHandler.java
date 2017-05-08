// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import android.content.res.AssetManager;
import com.facebook.react.bridge.MemoryPressure;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.bridge.NativeModule;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.queue.MessageQueueThread;
import java.util.Iterator;
import com.facebook.systrace.Systrace;
import com.facebook.react.bridge.NativeArray;
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
import com.facebook.react.bridge.queue.QueueThreadExceptionHandler;

class CatalystInstanceImpl$NativeExceptionHandler implements QueueThreadExceptionHandler
{
    final /* synthetic */ CatalystInstanceImpl this$0;
    
    private CatalystInstanceImpl$NativeExceptionHandler(final CatalystInstanceImpl this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void handleException(final Exception ex) {
        this.this$0.onNativeException(ex);
    }
}
