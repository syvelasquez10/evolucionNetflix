// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Context;
import java.util.regex.Pattern;

public final class zzlk
{
    private static Pattern zzagc;
    
    static {
        zzlk.zzagc = null;
    }
    
    public static boolean zzao(final Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }
    
    public static int zzbV(final int n) {
        return n / 1000;
    }
    
    public static int zzbW(final int n) {
        return n % 1000 / 100;
    }
    
    public static boolean zzbX(final int n) {
        return zzbW(n) == 3;
    }
}
