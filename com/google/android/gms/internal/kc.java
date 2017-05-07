// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Build$VERSION;

public final class kc
{
    private static boolean aR(final int n) {
        return Build$VERSION.SDK_INT >= n;
    }
    
    public static boolean hB() {
        return aR(11);
    }
    
    public static boolean hD() {
        return aR(13);
    }
    
    public static boolean hE() {
        return aR(14);
    }
}
