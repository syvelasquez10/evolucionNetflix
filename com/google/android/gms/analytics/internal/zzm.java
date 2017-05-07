// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

public enum zzm
{
    zzMA, 
    zzMB, 
    zzMC, 
    zzMD, 
    zzME, 
    zzMz;
    
    public static zzm zzbh(final String s) {
        if ("BATCH_BY_SESSION".equalsIgnoreCase(s)) {
            return zzm.zzMA;
        }
        if ("BATCH_BY_TIME".equalsIgnoreCase(s)) {
            return zzm.zzMB;
        }
        if ("BATCH_BY_BRUTE_FORCE".equalsIgnoreCase(s)) {
            return zzm.zzMC;
        }
        if ("BATCH_BY_COUNT".equalsIgnoreCase(s)) {
            return zzm.zzMD;
        }
        if ("BATCH_BY_SIZE".equalsIgnoreCase(s)) {
            return zzm.zzME;
        }
        return zzm.zzMz;
    }
}
