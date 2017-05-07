// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Build$VERSION;

public final class zzmx
{
    @Deprecated
    public static boolean isAtLeastL() {
        return zzqD();
    }
    
    private static boolean zzcd(final int n) {
        return Build$VERSION.SDK_INT >= n;
    }
    
    public static boolean zzqA() {
        return zzcd(18);
    }
    
    public static boolean zzqB() {
        return zzcd(19);
    }
    
    public static boolean zzqD() {
        return zzcd(21);
    }
    
    public static boolean zzqu() {
        return zzcd(11);
    }
    
    public static boolean zzqw() {
        return zzcd(13);
    }
    
    public static boolean zzqx() {
        return zzcd(14);
    }
    
    public static boolean zzqz() {
        return zzcd(17);
    }
}
