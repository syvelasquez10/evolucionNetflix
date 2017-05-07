// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Context;
import java.util.regex.Pattern;

public final class jt
{
    private static Pattern MJ;
    
    static {
        jt.MJ = null;
    }
    
    public static boolean K(final Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }
    
    public static int aN(final int n) {
        return n / 1000;
    }
    
    public static int aO(final int n) {
        return n % 1000 / 100;
    }
    
    public static boolean aP(final int n) {
        return aO(n) == 3;
    }
}
