// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;
import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;

class de
{
    private GoogleAnalytics aaB;
    private Context mContext;
    private Tracker sB;
    
    de(final Context mContext) {
        this.mContext = mContext;
    }
    
    private void bV(final String s) {
        synchronized (this) {
            if (this.aaB == null) {
                (this.aaB = GoogleAnalytics.getInstance(this.mContext)).setLogger(new a());
                this.sB = this.aaB.newTracker(s);
            }
        }
    }
    
    public Tracker bU(final String s) {
        this.bV(s);
        return this.sB;
    }
    
    static class a implements Logger
    {
        private static int ci(final int n) {
            switch (n) {
                default: {
                    return 3;
                }
                case 5: {
                    return 2;
                }
                case 3:
                case 4: {
                    return 1;
                }
                case 2: {
                    return 0;
                }
            }
        }
        
        @Override
        public void error(final Exception ex) {
            bh.b("", ex);
        }
        
        @Override
        public void error(final String s) {
            bh.w(s);
        }
        
        @Override
        public int getLogLevel() {
            return ci(bh.getLogLevel());
        }
        
        @Override
        public void info(final String s) {
            bh.x(s);
        }
        
        @Override
        public void setLogLevel(final int n) {
            bh.z("GA uses GTM logger. Please use TagManager.setLogLevel(int) instead.");
        }
        
        @Override
        public void verbose(final String s) {
            bh.y(s);
        }
        
        @Override
        public void warn(final String s) {
            bh.z(s);
        }
    }
}
