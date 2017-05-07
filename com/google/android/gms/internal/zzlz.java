// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zze;
import java.util.regex.Pattern;

public class zzlz
{
    private static final Pattern zzagg;
    
    static {
        zzagg = Pattern.compile("\\$\\{(.*?)\\}");
    }
    
    public static boolean zzcB(final String s) {
        return s == null || zze.zzacG.zzb(s);
    }
}
