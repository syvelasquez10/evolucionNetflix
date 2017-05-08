// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import com.netflix.mediaclient.ui.launch.LaunchActivity;
import com.netflix.mediaclient.ui.launch.UIWebViewActivity;
import com.netflix.mediaclient.ui.launch.NetflixComLaunchActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import com.netflix.mediaclient.ui.verifyplay.PinVerifier;
import java.util.Iterator;
import com.netflix.mediaclient.service.logging.IntegratedClientLoggingManager;
import com.netflix.mediaclient.Log;
import java.util.concurrent.Executors;
import java.util.Collections;
import java.util.ArrayList;
import android.os.SystemClock;
import java.util.concurrent.ScheduledExecutorService;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import android.content.Intent;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;
import android.app.Application$ActivityLifecycleCallbacks;

public class UserInputManager implements Application$ActivityLifecycleCallbacks
{
    private static final String TAG = "nf_input";
    private static final ThreadFactory sThreadFactory;
    private final AtomicInteger mActivitiesCount;
    private final AtomicInteger mActivitiesVisibleCount;
    private Intent mCachedIntent;
    private final AtomicLong mLastUserInteraction;
    private final List<ApplicationStateListener> mListeners;
    private final ScheduledExecutorService mScheduler;
    
    static {
        sThreadFactory = new UserInputManager$1();
    }
    
    public UserInputManager() {
        this.mLastUserInteraction = new AtomicLong(SystemClock.elapsedRealtime());
        this.mListeners = Collections.synchronizedList(new ArrayList<ApplicationStateListener>());
        this.mActivitiesCount = new AtomicInteger();
        this.mActivitiesVisibleCount = new AtomicInteger();
        this.mScheduler = Executors.newSingleThreadScheduledExecutor(UserInputManager.sThreadFactory);
    }
    
    private boolean isSuspendLoggingReady() {
        synchronized (this) {
            if (Log.isLoggable()) {
                Log.d("nf_input", "isSuspendLoggingReady: count" + this.mListeners.size());
            }
            boolean b;
            if (this.mListeners.isEmpty()) {
                b = false;
            }
            else {
                for (final ApplicationStateListener applicationStateListener : this.mListeners) {
                    Log.d("nf_input", "isSuspendLoggingReady: listener " + applicationStateListener);
                    if (applicationStateListener instanceof IntegratedClientLoggingManager) {
                        Log.d("nf_input", "Logger ready!");
                        b = true;
                        return b;
                    }
                }
                b = false;
            }
            return b;
        }
    }
    
    private void notifyOthersOfLastUserInteraction() {
        PinVerifier.lastUserInteractionTime(this.getTimeSinceLastUserInteraction());
    }
    
    private void postOnBackground(final Context context) {
        final boolean applicationInForeground = this.isApplicationInForeground();
        if (Log.isLoggable()) {
            Log.d("nf_input", "Foreground " + applicationInForeground);
        }
        if (applicationInForeground) {
            Log.d("nf_input", "Our app is still in foreground!");
            return;
        }
        Log.d("nf_input", "Our app is in background now");
        this.mScheduler.execute(new UserInputManager$5(this));
    }
    
    private void postOnFocusGain(final Context context) {
        final boolean applicationInForeground = this.isApplicationInForeground();
        if (Log.isLoggable()) {
            Log.d("nf_input", "Foreground " + applicationInForeground + ", visible " + this.mActivitiesVisibleCount.get());
        }
        if (this.mActivitiesVisibleCount.get() <= 0) {
            Log.d("nf_input", "Our app UI was not in focus!");
            this.mScheduler.execute(new UserInputManager$7(this));
            return;
        }
        Log.d("nf_input", "Our app UI had focus before!");
    }
    
    private void postOnFocusLost(final Context context) {
        final boolean applicationInForeground = this.isApplicationInForeground();
        if (Log.isLoggable()) {
            Log.d("nf_input", "Foreground " + applicationInForeground + ", visible " + this.mActivitiesVisibleCount.get());
        }
        if (this.mActivitiesVisibleCount.get() > 0) {
            Log.d("nf_input", "Our app UI still has focus!");
            return;
        }
        Log.d("nf_input", "Our app UI lost focus");
        this.mScheduler.execute(new UserInputManager$6(this));
    }
    
    private void postOnForeground(final Context context, final Intent mCachedIntent, final boolean b) {
        while (true) {
        Label_0112:
            while (true) {
                synchronized (this) {
                    if (Log.isLoggable()) {
                        Log.d("nf_input", "F Foreground " + b);
                    }
                    if (b) {
                        if (mCachedIntent == null) {
                            Log.d("nf_input", "Our app is in foreground already and we do not have a deep link");
                        }
                        else {
                            Log.d("nf_input", "Our app is in foreground already, deep link most likely");
                            if (!this.isSuspendLoggingReady()) {
                                break Label_0112;
                            }
                            Log.d("nf_input", "We are initialized, report...");
                            this.mScheduler.execute(new UserInputManager$4(this, mCachedIntent));
                        }
                        return;
                    }
                }
                Log.d("nf_input", "Our app was in background");
                continue;
            }
            if (mCachedIntent != null) {
                Log.d("nf_input", "Logger is not ready, cold start, save intent", mCachedIntent);
                this.mCachedIntent = mCachedIntent;
            }
        }
    }
    
    private void postUiExit(final int n) {
        if (n == 0) {
            Log.d("nf_input", "UI is  gone");
            this.mScheduler.execute(new UserInputManager$3(this));
        }
        else if (Log.isLoggable()) {
            Log.d("nf_input", "Activity destroyed, count " + n);
        }
    }
    
    private void postUiStart(final int n) {
        if (n == 1) {
            Log.d("nf_input", "UI may just started, only one activity");
            this.mScheduler.execute(new UserInputManager$2(this));
        }
        else if (Log.isLoggable()) {
            Log.d("nf_input", "New activity, count " + n);
        }
    }
    
    public boolean addListener(final ApplicationStateListener applicationStateListener) {
        synchronized (this) {
            boolean add;
            if (!this.mListeners.contains(applicationStateListener)) {
                add = this.mListeners.add(applicationStateListener);
            }
            else {
                Log.e("nf_input", "Listener already exist");
                add = false;
            }
            return add;
        }
    }
    
    public void checkState() {
        this.notifyOthersOfLastUserInteraction();
    }
    
    public Intent getAndClearCachedIntent() {
        synchronized (this) {
            final Intent mCachedIntent = this.mCachedIntent;
            this.mCachedIntent = null;
            return mCachedIntent;
        }
    }
    
    public int getNumberOfActivities() {
        return this.mActivitiesCount.get();
    }
    
    public long getTimeSinceLastUserInteraction() {
        return SystemClock.elapsedRealtime() - this.mLastUserInteraction.get();
    }
    
    public boolean isApplicationBackground() {
        return !this.isApplicationInForeground();
    }
    
    public boolean isApplicationInForeground() {
        return this.mActivitiesVisibleCount.get() > 0;
    }
    
    public void onActivityCreated(final Activity activity, final Bundle bundle) {
        if (Log.isLoggable()) {
            Log.d("nf_input", "onActivityCreated " + activity.getClass().getSimpleName());
        }
        this.postUiStart(this.mActivitiesCount.incrementAndGet());
    }
    
    public void onActivityDestroyed(final Activity activity) {
        if (Log.isLoggable()) {
            Log.d("nf_input", "onActivityDestroyed " + activity.getClass().getSimpleName());
        }
        this.postUiExit(this.mActivitiesCount.decrementAndGet());
    }
    
    public void onActivityPaused(final Activity activity) {
        if (Log.isLoggable()) {
            Log.d("nf_input", "onActivityPaused " + activity.getClass().getSimpleName());
        }
        if (activity instanceof NetflixComLaunchActivity) {
            Log.d("nf_input", "NetflixComLaunchActivity, ignore");
            return;
        }
        this.mActivitiesVisibleCount.decrementAndGet();
        this.postOnFocusLost((Context)activity);
    }
    
    public void onActivityResumed(final Activity activity) {
        if (Log.isLoggable()) {
            Log.d("nf_input", "onActivityResumed " + activity.getClass().getSimpleName());
        }
        if (activity instanceof NetflixComLaunchActivity) {
            Log.d("nf_input", "NetflixComLaunchActivity, ignore");
            return;
        }
        final boolean applicationInForeground = this.isApplicationInForeground();
        this.mActivitiesVisibleCount.incrementAndGet();
        if (activity instanceof UIWebViewActivity || activity instanceof LaunchActivity) {
            final Intent intent = activity.getIntent();
            if (intent != null) {
                Log.d("nf_input", "LaunchActivity: Foreground with intent", intent);
            }
            else {
                Log.d("nf_input", "LaunchActivity: Foreground without intent");
            }
            this.postOnForeground((Context)activity, intent, applicationInForeground);
        }
        else {
            this.postOnForeground((Context)activity, null, applicationInForeground);
        }
        this.postOnFocusGain((Context)activity);
    }
    
    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        if (Log.isLoggable()) {
            Log.d("nf_input", "onActivitySaveInstanceState " + activity.getClass().getSimpleName());
        }
    }
    
    public void onActivityStarted(final Activity activity) {
        if (Log.isLoggable()) {
            Log.d("nf_input", "onActivityStarted " + activity.getClass().getSimpleName());
        }
    }
    
    public void onActivityStopped(final Activity activity) {
        if (Log.isLoggable()) {
            Log.d("nf_input", "onActivityStopped " + activity.getClass().getSimpleName());
        }
        this.postOnBackground((Context)activity);
    }
    
    public boolean removeListener(final ApplicationStateListener applicationStateListener) {
        return this.mListeners.remove(applicationStateListener);
    }
    
    public void updateUserInteraction() {
        this.mLastUserInteraction.set(SystemClock.elapsedRealtime());
    }
}
