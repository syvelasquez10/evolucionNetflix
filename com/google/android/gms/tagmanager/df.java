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
                (this.arF = GoogleAnalytics.getInstance(this.mContext)).setLogger(new a());
                this.xY = this.arF.newTracker(s);
            }
        }
    }
    
    public Tracker cR(final String s) {
        this.cS(s);
        return this.xY;
    }
    
    static class a implements Logger
    {
        private static int fm(final int n) {
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
            bh.T(s);
        }
        
        @Override
        public int getLogLevel() {
            return fm(bh.getLogLevel());
        }
        
        @Override
        public void info(final String s) {
            bh.U(s);
        }
        
        @Override
        public void setLogLevel(final int n) {
            bh.W("GA uses GTM logger. Please use TagManager.setLogLevel(int) instead.");
        }
        
        @Override
        public void verbose(final String s) {
            bh.V(s);
        }
        
        @Override
        public void warn(final String s) {
            bh.W(s);
        }
    }
}
