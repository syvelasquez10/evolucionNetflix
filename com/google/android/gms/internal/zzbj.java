// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.content.Context;
import android.app.Activity;
import android.app.Application$ActivityLifecycleCallbacks;

@zzgk
public class zzbj implements Application$ActivityLifecycleCallbacks
{
    private Activity mActivity;
    private Context mContext;
    private final Object zzpc;
    
    private void setActivity(final Activity mActivity) {
        synchronized (this.zzpc) {
            if (!mActivity.getClass().getName().startsWith("com.google.android.gms.ads")) {
                this.mActivity = mActivity;
            }
        }
    }
    
    public Activity getActivity() {
        return this.mActivity;
    }
    
    public Context getContext() {
        return this.mContext;
    }
    
    public void onActivityCreated(final Activity activity, final Bundle bundle) {
    }
    
    public void onActivityDestroyed(final Activity activity) {
        synchronized (this.zzpc) {
            if (this.mActivity == null) {
                return;
            }
            if (this.mActivity.equals(activity)) {
                this.mActivity = null;
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
