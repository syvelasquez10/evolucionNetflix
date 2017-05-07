// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.tagmanager.zzdf;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class zzqp$zzd
{
    private String zzWs;
    private final List<zzqp$zze> zzaTT;
    private final Map<String, List<zzqp$zza>> zzaTU;
    private int zzaTV;
    
    private zzqp$zzd() {
        this.zzaTT = new ArrayList<zzqp$zze>();
        this.zzaTU = new HashMap<String, List<zzqp$zza>>();
        this.zzWs = "";
        this.zzaTV = 0;
    }
    
    public zzqp$zzc zzBI() {
        return new zzqp$zzc(this.zzaTT, this.zzaTU, this.zzWs, this.zzaTV, null);
    }
    
    public zzqp$zzd zzb(final zzqp$zze zzqp$zze) {
        this.zzaTT.add(zzqp$zze);
        return this;
    }
    
    public zzqp$zzd zzc(final zzqp$zza zzqp$zza) {
        final String zzg = zzdf.zzg(zzqp$zza.zzBD().get(zzae.zzfu.toString()));
        List<zzqp$zza> list;
        if ((list = this.zzaTU.get(zzg)) == null) {
            list = new ArrayList<zzqp$zza>();
            this.zzaTU.put(zzg, list);
        }
        list.add(zzqp$zza);
        return this;
    }
    
    public zzqp$zzd zzfk(final String zzWs) {
        this.zzWs = zzWs;
        return this;
    }
    
    public zzqp$zzd zzjm(final int zzaTV) {
        this.zzaTV = zzaTV;
        return this;
    }
}
