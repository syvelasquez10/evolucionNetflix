// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import android.text.TextUtils;
import android.os.Process;
import android.os.PowerManager$WakeLock;

public class zzf
{
    public static String zza(final PowerManager$WakeLock powerManager$WakeLock, final String s) {
        final StringBuilder append = new StringBuilder().append(String.valueOf(Process.myPid() << 32 | System.identityHashCode(powerManager$WakeLock)));
        String s2 = s;
        if (TextUtils.isEmpty((CharSequence)s)) {
            s2 = "";
        }
        return append.append(s2).toString();
    }
}
