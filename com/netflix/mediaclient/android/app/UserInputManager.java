// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import android.os.Bundle;
import android.app.Activity;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.common.PinVerifier;
import java.util.concurrent.Executors;
import java.util.Collections;
import java.util.ArrayList;
import android.os.SystemClock;
import java.util.concurrent.ScheduledExecutorService;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;
import android.app.Application$ActivityLifecycleCallbacks;

public class UserInputManager implements Application$ActivityLifecycleCallbacks
{
    private static final String TAG = "nf_input";
    private static final ThreadFactory sThreadFactory;
    private AtomicInteger mActivitiesCount;
    private AtomicLong mLastUserInteraction;
    private List<ApplicationStateListener> mListeners;
    private AtomicInteger mResumed;
    private ScheduledExecutorService mScheduler;
    private AtomicInteger mStopped;
    
    static {
        sThreadFactory = new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);
            
            @Override
            public Thread newThread(final Runnable runnable) {
                return new Thread(runnable, "UserInputManager #" + this.mCount.getAndIncrement());
            }
        };
    }
    
    public UserInputManager() {
        this.mLastUserInteraction = new AtomicLong(SystemClock.elapsedRealtime());
        this.mListeners = Collections.synchronizedList(new ArrayList<ApplicationStateListener>());
        this.mActivitiesCount = new AtomicInteger();
        this.mResumed = new AtomicInteger();
        this.mStopped = new AtomicInteger();
        this.mScheduler = Executors.newSingleThreadScheduledExecutor(UserInputManager.sThreadFactory);
    }
    
    private void notifyOthersOfLastUserInteraction() {
        PinVerifier.lastUserInteractionTime(this.getTimeSinceLastUserInteraction());
    }
    
    private void postOnBackground() {
        if (this.mResumed != this.mStopped) {
            Log.d("nf_input", "UI is in background");
            this.mScheduler.execute(new Runnable() {
                @Override
                public void run() {
                    final Iterator<ApplicationStateListener> iterator = UserInputManager.this.mListeners.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().onBackground(UserInputManager.this);
                    }
                }
            });
        }
    }
    
    private void postOnForeground() {
        if (this.mResumed != this.mStopped) {
            Log.d("nf_input", "UI is in foreground");
            this.mScheduler.execute(new Runnable() {
                @Override
                public void run() {
                    final Iterator<ApplicationStateListener> iterator = UserInputManager.this.mListeners.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().onForeground(UserInputManager.this);
                    }
                }
            });
        }
    }
    
    private void postUiExit(final int n) {
        if (n == 0) {
            Log.d("nf_input", "UI is  gone");
            this.mScheduler.execute(new Runnable() {
                @Override
                public void run() {
                    final Iterator<ApplicationStateListener> iterator = UserInputManager.this.mListeners.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().onUiGone(UserInputManager.this);
                    }
                }
            });
        }
        else if (Log.isLoggable("nf_input", 3)) {
            Log.d("nf_input", "Activity destroyed, count " + n);
        }
    }
    
    private void postUiStart(final int n) {
        if (n == 1) {
            Log.d("nf_input", "UI may just started, only one activity");
            this.mScheduler.execute(new Runnable() {
                @Override
                public void run() {
                    final Iterator<ApplicationStateListener> iterator = UserInputManager.this.mListeners.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().onUiStarted(UserInputManager.this);
                    }
                }
            });
        }
        else if (Log.isLoggable("nf_input", 3)) {
            Log.d("nf_input", "New activity, count " + n);
        }
    }
    
    public boolean addListener(final ApplicationStateListener applicationStateListener) {
        if (!this.mListeners.contains(applicationStateListener)) {
            return this.mListeners.add(applicationStateListener);
        }
        Log.e("nf_input", "Listener already exist");
        return false;
    }
    
    public void checkState() {
        this.notifyOthersOfLastUserInteraction();
    }
    
    public long getTimeSinceLastUserInteraction() {
        return SystemClock.elapsedRealtime() - this.mLastUserInteraction.get();
    }
    
    public boolean isApplicationBackground() {
        return !this.isApplicationInForeground();
    }
    
    public boolean isApplicationInForeground() {
        return this.mResumed.get() > this.mStopped.get();
    }
    
    public void onActivityCreated(final Activity activity, final Bundle bundle) {
        this.postUiStart(this.mActivitiesCount.incrementAndGet());
    }
    
    public void onActivityDestroyed(final Activity activity) {
        this.postUiExit(this.mActivitiesCount.decrementAndGet());
    }
    
    public void onActivityPaused(final Activity activity) {
    }
    
    public void onActivityResumed(final Activity activity) {
        this.mResumed.incrementAndGet();
        this.postOnForeground();
    }
    
    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
    }
    
    public void onActivityStarted(final Activity activity) {
    }
    
    public void onActivityStopped(final Activity activity) {
        this.mStopped.incrementAndGet();
        this.postOnBackground();
    }
    
    public boolean removeListener(final ApplicationStateListener applicationStateListener) {
        return this.mListeners.remove(applicationStateListener);
    }
    
    public void updateUserInteraction() {
        this.mLastUserInteraction.set(SystemClock.elapsedRealtime());
    }
}
