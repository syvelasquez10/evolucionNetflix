// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.app.Application;
import android.app.Activity;
import android.content.Context;
import android.app.Application$ActivityLifecycleCallbacks;

@ez
public class am implements Application$ActivityLifecycleCallbacks
{
    private Context mContext;
    private final Object mw;
    private Activity nr;
    
    public am(final Application application, final Activity activity) {
        this.mw = new Object();
        application.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)this);
        this.setActivity(activity);
        this.mContext = application.getApplicationContext();
    }
    
    private void setActivity(final Activity nr) {
        synchronized (this.mw) {
            if (!nr.getClass().getName().startsWith("com.google.android.gms.ads")) {
                this.nr = nr;
            }
        }
    }
    
    public Activity getActivity() {
        return this.nr;
    }
    
    public Context getContext() {
        return this.mContext;
    }
    
    public void onActivityCreated(final Activity activity, final Bundle bundle) {
    }
    
    public void onActivityDestroyed(final Activity activity) {
        synchronized (this.mw) {
            if (this.nr == null) {
                return;
            }
            if (this.nr.equals(activity)) {
                this.nr = null;
            }
        }
    }
    
    public void onActivityPaused(final Activity activity) {
        this.setActivity(activity);
    }
    
    public void onActivityResumed(final Activity activity) {
        this.setActivity(activity);
    }
    
    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
    }
    
    public void onActivityStarted(final Activity activity) {
        this.setActivity(activity);
    }
    
    public void onActivityStopped(final Activity activity) {
    }
}
