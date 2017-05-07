// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.io.File;
import android.os.Build$VERSION;

class zzal
{
    public static int version() {
        try {
            return Integer.parseInt(Build$VERSION.SDK);
        }
        catch (NumberFormatException ex) {
            zzbg.e("Invalid version number: " + Build$VERSION.SDK);
            return 0;
        }
    }
    
    static boolean zzbj(final String s) {
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
