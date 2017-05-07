// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.res.Configuration;
import android.content.res.Resources;

public final class zzle
{
    public static boolean zzb(final Resources resources) {
        if (resources != null) {
            boolean b;
            if ((resources.getConfiguration().screenLayout & 0xF) > 3) {
                b = true;
            }
            else {
                b = false;
            }
            if ((zzlk.zzoP() && b) || zzc(resources)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean zzc(final Resources resources) {
        final boolean b = false;
        final Configuration configuration = resources.getConfiguration();
        boolean b2 = b;
        if (zzlk.zzoR()) {
            b2 = b;
            if ((configuration.screenLayout & 0xF) <= 3) {
                b2 = b;
                if (configuration.smallestScreenWidthDp >= 600) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
}
