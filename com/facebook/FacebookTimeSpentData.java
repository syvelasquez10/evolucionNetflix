// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import com.facebook.internal.Logger;
import android.os.Bundle;
import java.io.Serializable;

class FacebookTimeSpentData implements Serializable
{
    private static final long[] INACTIVE_SECONDS_QUANTA;
    private static final String TAG;
    private String firstOpenSourceApplication;
    private int interruptionCount;
    private boolean isAppActive;
    private boolean isWarmLaunch;
    private long lastActivateEventLoggedTime;
    private long lastResumeTime;
    private long lastSuspendTime;
    private long millisecondsSpentInSession;
    
    static {
        TAG = AppEventsLogger.class.getCanonicalName();
        INACTIVE_SECONDS_QUANTA = new long[] { 300000L, 900000L, 1800000L, 3600000L, 21600000L, 43200000L, 86400000L, 172800000L, 259200000L, 604800000L, 1209600000L, 1814400000L, 2419200000L, 5184000000L, 7776000000L, 10368000000L, 12960000000L, 15552000000L, 31536000000L };
    }
    
    FacebookTimeSpentData() {
        this.resetSession();
    }
    
    private static int getQuantaIndex(final long n) {
        int n2;
        for (n2 = 0; n2 < FacebookTimeSpentData.INACTIVE_SECONDS_QUANTA.length && FacebookTimeSpentData.INACTIVE_SECONDS_QUANTA[n2] < n; ++n2) {}
        return n2;
    }
    
    private boolean isColdLaunch() {
        final boolean b = !this.isWarmLaunch;
        this.isWarmLaunch = true;
        return b;
    }
    
    private void logAppDeactivatedEvent(final AppEventsLogger appEventsLogger, final long n) {
        final Bundle bundle = new Bundle();
        bundle.putInt("fb_mobile_app_interruptions", this.interruptionCount);
        bundle.putString("fb_mobile_time_between_sessions", String.format("session_quanta_%d", getQuantaIndex(n)));
        bundle.putString("fb_mobile_launch_source", this.firstOpenSourceApplication);
        appEventsLogger.logEvent("fb_mobile_deactivate_app", this.millisecondsSpentInSession / 1000L, bundle);
        this.resetSession();
    }
    
    private void resetSession() {
        this.isAppActive = false;
        this.lastResumeTime = -1L;
        this.lastSuspendTime = -1L;
        this.interruptionCount = 0;
        this.millisecondsSpentInSession = 0L;
    }
    
    private boolean wasSuspendedEver() {
        return this.lastSuspendTime != -1L;
    }
    
    void onResume(final AppEventsLogger appEventsLogger, final long n, final String firstOpenSourceApplication) {
        final long n2 = 0L;
        if (this.isColdLaunch() || n - this.lastActivateEventLoggedTime > 300000L) {
            final Bundle bundle = new Bundle();
            bundle.putString("fb_mobile_launch_source", firstOpenSourceApplication);
            appEventsLogger.logEvent("fb_mobile_activate_app", bundle);
            this.lastActivateEventLoggedTime = n;
        }
        if (this.isAppActive) {
            Logger.log(LoggingBehavior.APP_EVENTS, FacebookTimeSpentData.TAG, "Resume for active app");
            return;
        }
        long n3;
        if (this.wasSuspendedEver()) {
            n3 = n - this.lastSuspendTime;
        }
        else {
            n3 = 0L;
        }
        if (n3 < 0L) {
            Logger.log(LoggingBehavior.APP_EVENTS, FacebookTimeSpentData.TAG, "Clock skew detected");
            n3 = n2;
        }
        if (n3 > 60000L) {
            this.logAppDeactivatedEvent(appEventsLogger, n3);
        }
        else if (n3 > 1000L) {
            ++this.interruptionCount;
        }
        if (this.interruptionCount == 0) {
            this.firstOpenSourceApplication = firstOpenSourceApplication;
        }
        this.lastResumeTime = n;
        this.isAppActive = true;
    }
}
