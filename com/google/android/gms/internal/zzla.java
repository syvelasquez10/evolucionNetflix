// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Process;
import com.google.android.gms.common.internal.zzd;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.Context;

public class zzla
{
    public static boolean zzi(final Context context, final String s) {
        boolean b = false;
        final PackageManager packageManager = context.getPackageManager();
        try {
            if ((packageManager.getApplicationInfo(s, 0).flags & 0x200000) != 0x0) {
                b = true;
            }
            return b;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return false;
        }
    }
    
    public static boolean zziW() {
        return zzd.zzZQ && zzkf.isInitialized() && zzkf.zzmW() == Process.myUid();
    }
}
