// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.analytics.Logger;

class zzdc$zza implements Logger
{
    private static int zzjl(final int n) {
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
    public void error(final String s) {
        zzbg.e(s);
    }
    
    @Override
    public int getLogLevel() {
        return zzjl(zzbg.getLogLevel());
    }
    
    @Override
    public void setLogLevel(final int n) {
        zzbg.zzaE("GA uses GTM logger. Please use TagManager.setLogLevel(int) instead.");
    }
    
    @Override
    public void warn(final String s) {
        zzbg.zzaE(s);
    }
}
