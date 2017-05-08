// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.jstasks;

import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.infer.annotation.Assertions;
import java.util.concurrent.CopyOnWriteArraySet;
import android.util.SparseArray;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;
import android.os.Handler;
import java.util.Set;
import com.facebook.react.bridge.ReactContext;
import java.util.WeakHashMap;

public class HeadlessJsTaskContext
{
    private static final WeakHashMap<ReactContext, HeadlessJsTaskContext> INSTANCES;
    private final Set<Integer> mActiveTasks;
    private final Handler mHandler;
    private final Set<HeadlessJsTaskEventListener> mHeadlessJsTaskEventListeners;
    private final AtomicInteger mLastTaskId;
    private final WeakReference<ReactContext> mReactContext;
    private final SparseArray<Runnable> mTaskTimeouts;
    
    static {
        INSTANCES = new WeakHashMap<ReactContext, HeadlessJsTaskContext>();
    }
    
    private HeadlessJsTaskContext(final ReactContext reactContext) {
        this.mHeadlessJsTaskEventListeners = new CopyOnWriteArraySet<HeadlessJsTaskEventListener>();
        this.mLastTaskId = new AtomicInteger(0);
        this.mHandler = new Handler();
        this.mActiveTasks = new CopyOnWriteArraySet<Integer>();
        this.mTaskTimeouts = (SparseArray<Runnable>)new SparseArray();
        this.mReactContext = new WeakReference<ReactContext>(reactContext);
    }
    
    public static HeadlessJsTaskContext getInstance(final ReactContext reactContext) {
        HeadlessJsTaskContext headlessJsTaskContext;
        if ((headlessJsTaskContext = HeadlessJsTaskContext.INSTANCES.get(reactContext)) == null) {
            headlessJsTaskContext = new HeadlessJsTaskContext(reactContext);
            HeadlessJsTaskContext.INSTANCES.put(reactContext, headlessJsTaskContext);
        }
        return headlessJsTaskContext;
    }
    
    public void addTaskEventListener(final HeadlessJsTaskEventListener headlessJsTaskEventListener) {
        this.mHeadlessJsTaskEventListeners.add(headlessJsTaskEventListener);
    }
    
    public void finishTask(final int n) {
        synchronized (this) {
            Assertions.assertCondition(this.mActiveTasks.remove(n), "Tried to finish non-existent task with id " + n + ".");
            final Runnable runnable = (Runnable)this.mTaskTimeouts.get(n);
            if (runnable != null) {
                this.mHandler.removeCallbacks(runnable);
                this.mTaskTimeouts.remove(n);
            }
            UiThreadUtil.runOnUiThread(new HeadlessJsTaskContext$1(this, n));
        }
    }
    
    public boolean hasActiveTasks() {
        return this.mActiveTasks.size() > 0;
    }
    
    public boolean isTaskRunning(final int n) {
        synchronized (this) {
            return this.mActiveTasks.contains(n);
        }
    }
    
    public void removeTaskEventListener(final HeadlessJsTaskEventListener headlessJsTaskEventListener) {
        this.mHeadlessJsTaskEventListeners.remove(headlessJsTaskEventListener);
    }
}
