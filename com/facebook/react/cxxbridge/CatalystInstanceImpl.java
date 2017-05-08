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

@DoNotStrip
public class CatalystInstanceImpl implements CatalystInstance
{
    private static final AtomicInteger sNextInstanceIdForTrace;
    private volatile boolean mAcceptCalls;
    private final CopyOnWriteArrayList<NotThreadSafeBridgeIdleDebugListener> mBridgeIdleListeners;
    private volatile boolean mDestroyed;
    private final HybridData mHybridData;
    private boolean mInitialized;
    private boolean mJSBundleHasLoaded;
    private final JSBundleLoader mJSBundleLoader;
    private final ArrayList<CatalystInstanceImpl$PendingJSCall> mJSCallsPendingInit;
    private final Object mJSCallsPendingInitLock;
    private final JavaScriptModuleRegistry mJSModuleRegistry;
    private final NativeModuleRegistry mJavaRegistry;
    private final String mJsPendingCallsTitleForTrace;
    private ExecutorToken mMainExecutorToken;
    private final NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
    private final AtomicInteger mPendingJSCalls;
    private final ReactQueueConfigurationImpl mReactQueueConfiguration;
    private final TraceListener mTraceListener;
    
    static {
        SoLoader.loadLibrary("reactnativejnifb");
        sNextInstanceIdForTrace = new AtomicInteger(1);
    }
    
    private CatalystInstanceImpl(final ReactQueueConfigurationSpec reactQueueConfigurationSpec, final JavaScriptExecutor javaScriptExecutor, final NativeModuleRegistry mJavaRegistry, final JavaScriptModuleRegistry mjsModuleRegistry, final JSBundleLoader mjsBundleLoader, final NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler) {
        this.mPendingJSCalls = new AtomicInteger(0);
        this.mJsPendingCallsTitleForTrace = "pending_js_calls_instance" + CatalystInstanceImpl.sNextInstanceIdForTrace.getAndIncrement();
        this.mDestroyed = false;
        this.mJSCallsPendingInit = new ArrayList<CatalystInstanceImpl$PendingJSCall>();
        this.mJSCallsPendingInitLock = new Object();
        this.mInitialized = false;
        this.mAcceptCalls = false;
        FLog.d("React", "Initializing React Xplat Bridge.");
        this.mHybridData = initHybrid();
        this.mReactQueueConfiguration = ReactQueueConfigurationImpl.create(reactQueueConfigurationSpec, new CatalystInstanceImpl$NativeExceptionHandler(this, null));
        this.mBridgeIdleListeners = new CopyOnWriteArrayList<NotThreadSafeBridgeIdleDebugListener>();
        this.mJavaRegistry = mJavaRegistry;
        this.mJSModuleRegistry = mjsModuleRegistry;
        this.mJSBundleLoader = mjsBundleLoader;
        this.mNativeModuleCallExceptionHandler = mNativeModuleCallExceptionHandler;
        this.mTraceListener = new CatalystInstanceImpl$JSProfilerTraceListener(this);
        this.initializeBridge(new CatalystInstanceImpl$BridgeCallback(this), javaScriptExecutor, this.mReactQueueConfiguration.getJSQueueThread(), this.mReactQueueConfiguration.getNativeModulesQueueThread(), this.mJavaRegistry.getModuleRegistryHolder(this));
        this.mMainExecutorToken = this.getMainExecutorToken();
    }
    
    private native void callJSCallback(final ExecutorToken p0, final int p1, final NativeArray p2);
    
    private native void callJSFunction(final ExecutorToken p0, final String p1, final String p2, final NativeArray p3);
    
    private void decrementPendingJSCalls() {
        final int decrementAndGet = this.mPendingJSCalls.decrementAndGet();
        boolean b;
        if (decrementAndGet == 0) {
            b = true;
        }
        else {
            b = false;
        }
        Systrace.traceCounter(0L, this.mJsPendingCallsTitleForTrace, decrementAndGet);
        if (b && !this.mBridgeIdleListeners.isEmpty()) {
            final Iterator<NotThreadSafeBridgeIdleDebugListener> iterator = this.mBridgeIdleListeners.iterator();
            while (iterator.hasNext()) {
                iterator.next().onTransitionToBridgeIdle();
            }
        }
    }
    
    private native ExecutorToken getMainExecutorToken();
    
    private native void handleMemoryPressureCritical();
    
    private native void handleMemoryPressureModerate();
    
    private native void handleMemoryPressureUiHidden();
    
    private void incrementPendingJSCalls() {
        final int andIncrement = this.mPendingJSCalls.getAndIncrement();
        boolean b;
        if (andIncrement == 0) {
            b = true;
        }
        else {
            b = false;
        }
        Systrace.traceCounter(0L, this.mJsPendingCallsTitleForTrace, andIncrement + 1);
        if (b && !this.mBridgeIdleListeners.isEmpty()) {
            final Iterator<NotThreadSafeBridgeIdleDebugListener> iterator = this.mBridgeIdleListeners.iterator();
            while (iterator.hasNext()) {
                iterator.next().onTransitionToBridgeBusy();
            }
        }
    }
    
    private static native HybridData initHybrid();
    
    private native void initializeBridge(final ReactCallback p0, final JavaScriptExecutor p1, final MessageQueueThread p2, final MessageQueueThread p3, final ModuleRegistryHolder p4);
    
    private void onNativeException(final Exception ex) {
        this.mNativeModuleCallExceptionHandler.handleException(ex);
        this.mReactQueueConfiguration.getUIQueueThread().runOnQueue(new CatalystInstanceImpl$1(this));
    }
    
    @Override
    public void addBridgeIdleDebugListener(final NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener) {
        this.mBridgeIdleListeners.add(notThreadSafeBridgeIdleDebugListener);
    }
    
    @Override
    public void callFunction(final ExecutorToken executorToken, final String s, final String s2, final NativeArray nativeArray) {
        if (this.mDestroyed) {
            FLog.w("React", "Calling JS function after bridge has been destroyed.");
            return;
        }
        if (!this.mAcceptCalls) {
            synchronized (this.mJSCallsPendingInitLock) {
                if (!this.mAcceptCalls) {
                    this.mJSCallsPendingInit.add(new CatalystInstanceImpl$PendingJSCall(executorToken, s, s2, nativeArray));
                    return;
                }
            }
        }
        // monitorexit(o)
        this.callJSFunction(executorToken, s, s2, nativeArray);
    }
    
    @Override
    public void destroy() {
        boolean b = true;
        UiThreadUtil.assertOnUiThread();
        if (this.mDestroyed) {
            return;
        }
        this.mDestroyed = true;
        this.mHybridData.resetNative();
        this.mJavaRegistry.notifyCatalystInstanceDestroy();
        if (this.mPendingJSCalls.getAndSet(0) != 0) {
            b = false;
        }
        if (!b && !this.mBridgeIdleListeners.isEmpty()) {
            final Iterator<NotThreadSafeBridgeIdleDebugListener> iterator = this.mBridgeIdleListeners.iterator();
            while (iterator.hasNext()) {
                iterator.next().onTransitionToBridgeIdle();
            }
        }
        Systrace.unregisterListener(this.mTraceListener);
    }
    
    @Override
    public <T extends JavaScriptModule> T getJSModule(final ExecutorToken executorToken, final Class<T> clazz) {
        return Assertions.assertNotNull(this.mJSModuleRegistry).getJavaScriptModule(this, executorToken, clazz);
    }
    
    @Override
    public <T extends JavaScriptModule> T getJSModule(final Class<T> clazz) {
        return this.getJSModule(this.mMainExecutorToken, clazz);
    }
    
    public native long getJavaScriptContext();
    
    @Override
    public <T extends NativeModule> T getNativeModule(final Class<T> clazz) {
        return this.mJavaRegistry.getModule(clazz);
    }
    
    @Override
    public ReactQueueConfiguration getReactQueueConfiguration() {
        return this.mReactQueueConfiguration;
    }
    
    @Override
    public void handleMemoryPressure(final MemoryPressure memoryPressure) {
        if (this.mDestroyed) {
            return;
        }
        switch (CatalystInstanceImpl$2.$SwitchMap$com$facebook$react$bridge$MemoryPressure[memoryPressure.ordinal()]) {
            default: {}
            case 1: {
                this.handleMemoryPressureUiHidden();
            }
            case 2: {
                this.handleMemoryPressureModerate();
            }
            case 3: {
                this.handleMemoryPressureCritical();
            }
        }
    }
    
    @Override
    public void initialize() {
        UiThreadUtil.assertOnUiThread();
        Assertions.assertCondition(!this.mInitialized, "This catalyst instance has already been initialized");
        Assertions.assertCondition(this.mAcceptCalls, "RunJSBundle hasn't completed.");
        this.mInitialized = true;
        this.mJavaRegistry.notifyCatalystInstanceInitialized();
    }
    
    @Override
    public void invokeCallback(final ExecutorToken executorToken, final int n, final NativeArray nativeArray) {
        if (this.mDestroyed) {
            FLog.w("React", "Invoking JS callback after bridge has been destroyed.");
            return;
        }
        this.callJSCallback(executorToken, n, nativeArray);
    }
    
    @Override
    public boolean isDestroyed() {
        return this.mDestroyed;
    }
    
    native void loadScriptFromAssets(final AssetManager p0, final String p1);
    
    native void loadScriptFromFile(final String p0, final String p1);
    
    native void loadScriptFromOptimizedBundle(final String p0, final String p1, final int p2);
    
    @Override
    public void removeBridgeIdleDebugListener(final NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener) {
        this.mBridgeIdleListeners.remove(notThreadSafeBridgeIdleDebugListener);
    }
    
    @Override
    public void runJSBundle() {
        // monitorexit(o)
        while (true) {
            Label_0101: {
                if (this.mJSBundleHasLoaded) {
                    break Label_0101;
                }
                final boolean b = true;
                Assertions.assertCondition(b, "JS bundle was already loaded!");
                this.mJSBundleHasLoaded = true;
                this.mJSBundleLoader.loadScript(this);
                Label_0106: {
                    synchronized (this.mJSCallsPendingInitLock) {
                        this.mAcceptCalls = true;
                        for (final CatalystInstanceImpl$PendingJSCall catalystInstanceImpl$PendingJSCall : this.mJSCallsPendingInit) {
                            this.callJSFunction(catalystInstanceImpl$PendingJSCall.mExecutorToken, catalystInstanceImpl$PendingJSCall.mModule, catalystInstanceImpl$PendingJSCall.mMethod, catalystInstanceImpl$PendingJSCall.mArguments);
                        }
                        break Label_0106;
                    }
                    break Label_0101;
                }
                this.mJSCallsPendingInit.clear();
                Systrace.registerListener(this.mTraceListener);
                return;
            }
            final boolean b = false;
            continue;
        }
    }
    
    public native void setGlobalVariable(final String p0, final String p1);
    
    public native void startProfiler(final String p0);
    
    public native void stopProfiler(final String p0, final String p1);
    
    public native boolean supportsProfiling();
}
