// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import android.os.Bundle;
import android.app.Activity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.pin.PinVerifier;
import android.app.ActivityManager$RunningTaskInfo;
import android.app.ActivityManager;
import android.content.Context;
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
    private boolean mForeground;
    private AtomicLong mLastUserInteraction;
    private List<ApplicationStateListener> mListeners;
    private AtomicInteger mResumed;
    private ScheduledExecutorService mScheduler;
    private AtomicInteger mStopped;
    
    static {
        sThreadFactory = new UserInputManager$1();
    }
    
    public UserInputManager() {
        this.mLastUserInteraction = new AtomicLong(SystemClock.elapsedRealtime());
        this.mListeners = Collections.synchronizedList(new ArrayList<ApplicationStateListener>());
        this.mActivitiesCount = new AtomicInteger();
        this.mResumed = new AtomicInteger();
        this.mStopped = new AtomicInteger();
        this.mScheduler = Executors.newSingleThreadScheduledExecutor(UserInputManager.sThreadFactory);
    }
    
    private static boolean isApplicationBroughtToBackground(final Context context) {
        final List runningTasks = ((ActivityManager)context.getSystemService("activity")).getRunningTasks(1);
        return !runningTasks.isEmpty() && !runningTasks.get(0).topActivity.getPackageName().equals(context.getPackageName());
    }
    
    private void notifyOthersOfLastUserInteraction() {
        PinVerifier.lastUserInteractionTime(this.getTimeSinceLastUserInteraction());
    }
    
    private void postOnBackground(final Context context) {
        Log.d("nf_input", "B Resumed " + this.mResumed);
        Log.d("nf_input", "B Stopped " + this.mStopped);
        Log.d("nf_input", "F Foreground " + this.mForeground);
        if (!isApplicationBroughtToBackground(context)) {
            Log.d("nf_input", "Our app is still in foreground");
            return;
        }
        Log.d("nf_input", "Our app is in background");
        this.mForeground = false;
        this.mScheduler.execute(new UserInputManager$5(this));
    }
    
    private void postOnForeground(final Context context) {
        Log.d("nf_input", "F Resumed " + this.mResumed);
        Log.d("nf_input", "F Stopped " + this.mStopped);
        Log.d("nf_input", "F Foreground " + this.mForeground);
        if (this.mForeground) {
            Log.d("nf_input", "Our app is in foreground already");
            return;
        }
        Log.d("nf_input", "Our app is in foreground");
        this.mForeground = true;
        this.mScheduler.execute(new UserInputManager$4(this));
    }
    
    private void postUiExit(final int n) {
        if (n == 0) {
            Log.d("nf_input", "UI is  gone");
            this.mScheduler.execute(new UserInputManager$3(this));
        }
        else if (Log.isLoggable("nf_input", 3)) {
            Log.d("nf_input", "Activity destroyed, count " + n);
        }
    }
    
    private void postUiStart(final int n) {
        if (n == 1) {
            Log.d("nf_input", "UI may just started, only one activity");
            this.mScheduler.execute(new UserInputManager$2(this));
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
        return this.mForeground;
    }
    
    public void onActivityCreated(final Activity activity, final Bundle bundle) {
        if (Log.isLoggable("nf_input", 3)) {
            Log.d("nf_input", "onActivityCreated " + activity.getClass().getSimpleName());
        }
        this.postUiStart(this.mActivitiesCount.incrementAndGet());
    }
    
    public void onActivityDestroyed(final Activity activity) {
        if (Log.isLoggable("nf_input", 3)) {
            Log.d("nf_input", "onActivityDestroyed " + activity.getClass().getSimpleName());
        }
        this.postUiExit(this.mActivitiesCount.decrementAndGet());
    }
    
    public void onActivityPaused(final Activity activity) {
        if (Log.isLoggable("nf_input", 3)) {
            Log.d("nf_input", "onActivityPaused " + activity.getClass().getSimpleName());
        }
    }
    
    public void onActivityResumed(final Activity activity) {
        if (Log.isLoggable("nf_input", 3)) {
            Log.d("nf_input", "onActivityResumed " + activity.getClass().getSimpleName());
        }
        this.mResumed.incrementAndGet();
        this.postOnForeground((Context)activity);
    }
    
    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        if (Log.isLoggable("nf_input", 3)) {
            Log.d("nf_input", "onActivitySaveInstanceState " + activity.getClass().getSimpleName());
        }
    }
    
    public void onActivityStarted(final Activity activity) {
        if (Log.isLoggable("nf_input", 3)) {
            Log.d("nf_input", "onActivityStarted " + activity.getClass().getSimpleName());
        }
    }
    
    public void onActivityStopped(final Activity activity) {
        if (Log.isLoggable("nf_input", 3)) {
            Log.d("nf_input", "onActivityStopped " + activity.getClass().getSimpleName());
        }
        this.mStopped.incrementAndGet();
        this.postOnBackground((Context)activity);
    }
    
    public boolean removeListener(final ApplicationStateListener applicationStateListener) {
        return this.mListeners.remove(applicationStateListener);
    }
    
    public void updateUserInteraction() {
        this.mLastUserInteraction.set(SystemClock.elapsedRealtime());
    }
}
