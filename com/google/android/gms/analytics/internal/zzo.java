// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

public enum zzo
{
    zzMJ, 
    zzMK;
    
    public static zzo zzbi(final String s) {
        if ("GZIP".equalsIgnoreCase(s)) {
            return zzo.zzMK;
        }
        return zzo.zzMJ;
    }
}
