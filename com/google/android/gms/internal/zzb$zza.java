// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Map;

public class zzb$zza
{
    public byte[] data;
    public String zzb;
    public long zzc;
    public long zzd;
    public long zze;
    public long zzf;
    public Map<String, String> zzg;
    
    public zzb$zza() {
        this.zzg = Collections.emptyMap();
    }
    
    public boolean zzb() {
        return this.zze < System.currentTimeMillis();
    }
    
    public boolean zzc() {
        return this.zzf < System.currentTimeMillis();
    }
}
