// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public abstract class zzlk
{
    private static final ExecutorService zzacD;
    
    static {
        zzacD = Executors.newFixedThreadPool(2, new zzlk$zza(null));
    }
    
    public static ExecutorService zzoj() {
        return zzlk.zzacD;
    }
}
