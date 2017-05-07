// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.WeakHashMap;
import android.app.Activity;
import java.util.Map;

public abstract class zza
{
    private static final Map<Activity, zza> zzaaZ;
    private static final Object zzpy;
    
    static {
        zzaaZ = new WeakHashMap<Activity, zza>();
        zzpy = new Object();
    }
    
    public abstract void remove(final int p0);
}
