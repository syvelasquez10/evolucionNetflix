// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import java.io.File;
import android.os.Build$VERSION;

public class zzx
{
    public static int version() {
        try {
            return Integer.parseInt(Build$VERSION.SDK);
        }
        catch (NumberFormatException ex) {
            zzae.zzf("Invalid version number", Build$VERSION.SDK);
            return 0;
        }
    }
    
    public static boolean zzbj(final String s) {
        if (version() < 9) {
            return false;
        }
        final File file = new File(s);
        file.setReadable(false, false);
        file.setWritable(false, false);
        file.setReadable(true, true);
        file.setWritable(true, true);
        return true;
    }
}
