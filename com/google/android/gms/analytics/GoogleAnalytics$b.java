// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.os.Bundle;
import android.app.Activity;
import android.app.Application$ActivityLifecycleCallbacks;

class GoogleAnalytics$b implements Application$ActivityLifecycleCallbacks
{
    final /* synthetic */ GoogleAnalytics AD;
    
    GoogleAnalytics$b(final GoogleAnalytics ad) {
        this.AD = ad;
    }
    
    public void onActivityCreated(final Activity activity, final Bundle bundle) {
    }
    
    public void onActivityDestroyed(final Activity activity) {
    }
    
    public void onActivityPaused(final Activity activity) {
    }
    
    public void onActivityResumed(final Activity activity) {
    }
    
    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
    }
    
    public void onActivityStarted(final Activity activity) {
        this.AD.g(activity);
    }
    
    public void onActivityStopped(final Activity activity) {
        this.AD.h(activity);
    }
}
