// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public abstract class zzk
{
    private static final ExecutorService zzaay;
    
    static {
        zzaay = Executors.newFixedThreadPool(2);
    }
    
    public static ExecutorService zznF() {
        return zzk.zzaay;
    }
}
