// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Map;
import java.util.List;

public class zzqp$zzc
{
    private final String zzWs;
    private final List<zzqp$zze> zzaTT;
    private final Map<String, List<zzqp$zza>> zzaTU;
    private final int zzaTV;
    
    private zzqp$zzc(final List<zzqp$zze> list, final Map<String, List<zzqp$zza>> map, final String zzWs, final int zzaTV) {
        this.zzaTT = Collections.unmodifiableList((List<? extends zzqp$zze>)list);
        this.zzaTU = Collections.unmodifiableMap((Map<? extends String, ? extends List<zzqp$zza>>)map);
        this.zzWs = zzWs;
        this.zzaTV = zzaTV;
    }
    
    public static zzqp$zzd zzBF() {
        return new zzqp$zzd(null);
    }
    
    public String getVersion() {
        return this.zzWs;
    }
    
    @Override
    public String toString() {
        return "Rules: " + this.zzBG() + "  Macros: " + this.zzaTU;
    }
    
    public List<zzqp$zze> zzBG() {
        return this.zzaTT;
    }
    
    public Map<String, List<zzqp$zza>> zzBH() {
        return this.zzaTU;
    }
}
