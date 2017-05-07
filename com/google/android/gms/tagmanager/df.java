// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;
import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;

class df
{
    private GoogleAnalytics arF;
    private Context mContext;
    private Tracker xY;
    
    df(final Context mContext) {
        this.mContext = mContext;
    }
    
    private void cS(final String s) {
        synchronized (this) {
            if (this.arF == null) {
                (this.arF = GoogleAnalytics.getInstance(this.mContext)).setLogger(new df$a());
                this.xY = this.arF.newTracker(s);
            }
        }
    }
    
    public Tracker cR(final String s) {
        this.cS(s);
        return this.xY;
    }
}
