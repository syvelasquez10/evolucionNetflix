// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import java.util.Iterator;
import android.content.Intent;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.infer.annotation.Assertions;
import android.content.Context;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.bridge.queue.MessageQueueThread;
import android.view.LayoutInflater;
import android.app.Activity;
import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArraySet;
import android.content.ContextWrapper;

public class ReactContext extends ContextWrapper
{
    private final CopyOnWriteArraySet<ActivityEventListener> mActivityEventListeners;
    private CatalystInstance mCatalystInstance;
    private WeakReference<Activity> mCurrentActivity;
    private LayoutInflater mInflater;
    private MessageQueueThread mJSMessageQueueThread;
    private final CopyOnWriteArraySet<LifecycleEventListener> mLifecycleEventListeners;
    private LifecycleState mLifecycleState;
    private NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
    private MessageQueueThread mNativeModulesMessageQueueThread;
    private MessageQueueThread mUiMessageQueueThread;
    
    public ReactContext(final Context context) {
        super(context);
        this.mLifecycleEventListeners = new CopyOnWriteArraySet<LifecycleEventListener>();
        this.mActivityEventListeners = new CopyOnWriteArraySet<ActivityEventListener>();
        this.mLifecycleState = LifecycleState.BEFORE_CREATE;
    }
    
    public void addLifecycleEventListener(final LifecycleEventListener lifecycleEventListener) {
        this.mLifecycleEventListeners.add(lifecycleEventListener);
        if (this.hasActiveCatalystInstance()) {
            switch (ReactContext$2.$SwitchMap$com$facebook$react$common$LifecycleState[this.mLifecycleState.ordinal()]) {
                default: {
                    throw new RuntimeException("Unhandled lifecycle state.");
                }
                case 3: {
                    this.runOnUiQueueThread(new ReactContext$1(this, lifecycleEventListener));
                }
                case 1:
                case 2: {
                    break;
                }
            }
        }
    }
    
    public void assertOnNativeModulesQueueThread() {
        Assertions.assertNotNull(this.mNativeModulesMessageQueueThread).assertIsOnThread();
    }
    
    public void destroy() {
        UiThreadUtil.assertOnUiThread();
        if (this.mCatalystInstance != null) {
            this.mCatalystInstance.destroy();
        }
    }
    
    public CatalystInstance getCatalystInstance() {
        return Assertions.assertNotNull(this.mCatalystInstance);
    }
    
    public Activity getCurrentActivity() {
        if (this.mCurrentActivity == null) {
            return null;
        }
        return this.mCurrentActivity.get();
    }
    
    public <T extends JavaScriptModule> T getJSModule(final ExecutorToken executorToken, final Class<T> clazz) {
        if (this.mCatalystInstance == null) {
            throw new RuntimeException("Tried to access a JS module before the React instance was fully set up. Calls to ReactContext#getJSModule should only happen once initialize() has been called on your native module.");
        }
        return this.mCatalystInstance.getJSModule(executorToken, clazz);
    }
    
    public <T extends JavaScriptModule> T getJSModule(final Class<T> clazz) {
        if (this.mCatalystInstance == null) {
            throw new RuntimeException("Tried to access a JS module before the React instance was fully set up. Calls to ReactContext#getJSModule should only happen once initialize() has been called on your native module.");
        }
        return this.mCatalystInstance.getJSModule(clazz);
    }
    
    public <T extends NativeModule> T getNativeModule(final Class<T> clazz) {
        if (this.mCatalystInstance == null) {
            throw new RuntimeException("Trying to call native module before CatalystInstance has been set!");
        }
        return this.mCatalystInstance.getNativeModule(clazz);
    }
    
    public Object getSystemService(final String s) {
        if ("layout_inflater".equals(s)) {
            if (this.mInflater == null) {
                this.mInflater = LayoutInflater.from(this.getBaseContext()).cloneInContext((Context)this);
            }
            return this.mInflater;
        }
        return this.getBaseContext().getSystemService(s);
    }
    
    public void handleException(final RuntimeException ex) {
        if (this.mCatalystInstance != null && !this.mCatalystInstance.isDestroyed() && this.mNativeModuleCallExceptionHandler != null) {
            this.mNativeModuleCallExceptionHandler.handleException(ex);
            return;
        }
        throw ex;
    }
    
    public boolean hasActiveCatalystInstance() {
        return this.mCatalystInstance != null && !this.mCatalystInstance.isDestroyed();
    }
    
    public void initializeWithInstance(final CatalystInstance mCatalystInstance) {
        if (mCatalystInstance == null) {
            throw new IllegalArgumentException("CatalystInstance cannot be null.");
        }
        if (this.mCatalystInstance != null) {
            throw new IllegalStateException("ReactContext has been already initialized");
        }
        this.mCatalystInstance = mCatalystInstance;
        final ReactQueueConfiguration reactQueueConfiguration = mCatalystInstance.getReactQueueConfiguration();
        this.mUiMessageQueueThread = reactQueueConfiguration.getUIQueueThread();
        this.mNativeModulesMessageQueueThread = reactQueueConfiguration.getNativeModulesQueueThread();
        this.mJSMessageQueueThread = reactQueueConfiguration.getJSQueueThread();
    }
    
    public boolean isOnUiQueueThread() {
        return Assertions.assertNotNull(this.mUiMessageQueueThread).isOnThread();
    }
    
    public void onActivityResult(final Activity activity, final int n, final int n2, final Intent intent) {
        final Iterator<ActivityEventListener> iterator = this.mActivityEventListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onActivityResult(activity, n, n2, intent);
        }
    }
    
    public void onHostDestroy() {
        UiThreadUtil.assertOnUiThread();
        this.mLifecycleState = LifecycleState.BEFORE_CREATE;
        final Iterator<LifecycleEventListener> iterator = this.mLifecycleEventListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onHostDestroy();
        }
        this.mCurrentActivity = null;
    }
    
    public void onHostPause() {
        UiThreadUtil.assertOnUiThread();
        this.mLifecycleState = LifecycleState.BEFORE_RESUME;
        final Iterator<LifecycleEventListener> iterator = this.mLifecycleEventListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onHostPause();
        }
    }
    
    public void onHostResume(final Activity activity) {
        UiThreadUtil.assertOnUiThread();
        this.mLifecycleState = LifecycleState.RESUMED;
        this.mCurrentActivity = new WeakReference<Activity>(activity);
        this.mLifecycleState = LifecycleState.RESUMED;
        final Iterator<LifecycleEventListener> iterator = this.mLifecycleEventListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onHostResume();
        }
    }
    
    public void onNewIntent(final Activity activity, final Intent intent) {
        UiThreadUtil.assertOnUiThread();
        this.mCurrentActivity = new WeakReference<Activity>(activity);
        final Iterator<ActivityEventListener> iterator = this.mActivityEventListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onNewIntent(intent);
        }
    }
    
    public void removeLifecycleEventListener(final LifecycleEventListener lifecycleEventListener) {
        this.mLifecycleEventListeners.remove(lifecycleEventListener);
    }
    
    public void runOnJSQueueThread(final Runnable runnable) {
        Assertions.assertNotNull(this.mJSMessageQueueThread).runOnQueue(runnable);
    }
    
    public void runOnNativeModulesQueueThread(final Runnable runnable) {
        Assertions.assertNotNull(this.mNativeModulesMessageQueueThread).runOnQueue(runnable);
    }
    
    public void runOnUiQueueThread(final Runnable runnable) {
        Assertions.assertNotNull(this.mUiMessageQueueThread).runOnQueue(runnable);
    }
    
    public void setNativeModuleCallExceptionHandler(final NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler) {
        this.mNativeModuleCallExceptionHandler = mNativeModuleCallExceptionHandler;
    }
}
