// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import android.content.Context;

public class zzdc
{
    private Context mContext;
    private Tracker zzKq;
    private GoogleAnalytics zzKs;
    
    public zzdc(final Context mContext) {
        this.mContext = mContext;
    }
    
    private void zzeW(final String s) {
        synchronized (this) {
            if (this.zzKs == null) {
                (this.zzKs = GoogleAnalytics.getInstance(this.mContext)).setLogger(new zzdc$zza());
                this.zzKq = this.zzKs.newTracker(s);
            }
        }
    }
    
    public Tracker zzeV(final String s) {
        this.zzeW(s);
        return this.zzKq;
    }
}
