// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Build$VERSION;

public final class gr
{
    private static boolean ab(final int n) {
        return Build$VERSION.SDK_INT >= n;
    }
    
    public static boolean fu() {
        return ab(11);
    }
    
    public static boolean fv() {
        return ab(12);
    }
    
    public static boolean fw() {
        return ab(13);
    }
    
    public static boolean fx() {
        return ab(14);
    }
    
    public static boolean fy() {
        return ab(16);
    }
    
    public static boolean fz() {
        return ab(17);
    }
}
