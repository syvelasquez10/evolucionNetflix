// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Context;
import java.util.regex.Pattern;

public final class zzml
{
    private static Pattern zzaij;
    
    static {
        zzml.zzaij = null;
    }
    
    public static boolean zzan(final Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }
    
    public static int zzca(final int n) {
        return n / 1000;
    }
    
    @Deprecated
    public static boolean zzcb(final int n) {
        return false;
    }
}
