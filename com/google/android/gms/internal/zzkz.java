// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Context;
import java.util.regex.Pattern;

public final class zzkz
{
    private static Pattern zzacI;
    
    static {
        zzkz.zzacI = null;
    }
    
    public static boolean zzai(final Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }
    
    public static int zzbN(final int n) {
        return n / 1000;
    }
    
    public static int zzbO(final int n) {
        return n % 1000 / 100;
    }
    
    public static boolean zzbP(final int n) {
        return zzbO(n) == 3;
    }
}
