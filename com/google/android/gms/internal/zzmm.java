// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.Context;
import android.os.Process;
import com.google.android.gms.common.internal.zzd;

public class zzmm
{
    public static boolean zzjA() {
        return zzd.zzaeK && zzlr.isInitialized() && zzlr.zzoo() == Process.myUid();
    }
    
    public static boolean zzl(final Context context, final String s) {
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
}
