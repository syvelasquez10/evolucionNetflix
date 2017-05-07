// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import com.google.android.gms.internal.zzqp$zza;
import java.util.List;
import java.util.Map;
import com.google.android.gms.internal.zzqp$zze;
import java.util.Set;

class zzcp$zzc
{
    private final Set<zzqp$zze> zzaRG;
    private final Map<zzqp$zze, List<zzqp$zza>> zzaRR;
    private final Map<zzqp$zze, List<zzqp$zza>> zzaRS;
    private final Map<zzqp$zze, List<String>> zzaRT;
    private final Map<zzqp$zze, List<String>> zzaRU;
    private zzqp$zza zzaRV;
    
    public zzcp$zzc() {
        this.zzaRG = new HashSet<zzqp$zze>();
        this.zzaRR = new HashMap<zzqp$zze, List<zzqp$zza>>();
        this.zzaRT = new HashMap<zzqp$zze, List<String>>();
        this.zzaRS = new HashMap<zzqp$zze, List<zzqp$zza>>();
        this.zzaRU = new HashMap<zzqp$zze, List<String>>();
    }
    
    public Set<zzqp$zze> zzAJ() {
        return this.zzaRG;
    }
    
    public Map<zzqp$zze, List<zzqp$zza>> zzAK() {
        return this.zzaRR;
    }
    
    public Map<zzqp$zze, List<String>> zzAL() {
        return this.zzaRT;
    }
    
    public Map<zzqp$zze, List<String>> zzAM() {
        return this.zzaRU;
    }
    
    public Map<zzqp$zze, List<zzqp$zza>> zzAN() {
        return this.zzaRS;
    }
    
    public zzqp$zza zzAO() {
        return this.zzaRV;
    }
    
    public void zza(final zzqp$zze zzqp$zze) {
        this.zzaRG.add(zzqp$zze);
    }
    
    public void zza(final zzqp$zze zzqp$zze, final zzqp$zza zzqp$zza) {
        List<zzqp$zza> list;
        if ((list = this.zzaRR.get(zzqp$zze)) == null) {
            list = new ArrayList<zzqp$zza>();
            this.zzaRR.put(zzqp$zze, list);
        }
        list.add(zzqp$zza);
    }
    
    public void zza(final zzqp$zze zzqp$zze, final String s) {
        List<String> list;
        if ((list = this.zzaRT.get(zzqp$zze)) == null) {
            list = new ArrayList<String>();
            this.zzaRT.put(zzqp$zze, list);
        }
        list.add(s);
    }
    
    public void zzb(final zzqp$zza zzaRV) {
        this.zzaRV = zzaRV;
    }
    
    public void zzb(final zzqp$zze zzqp$zze, final zzqp$zza zzqp$zza) {
        List<zzqp$zza> list;
        if ((list = this.zzaRS.get(zzqp$zze)) == null) {
            list = new ArrayList<zzqp$zza>();
            this.zzaRS.put(zzqp$zze, list);
        }
        list.add(zzqp$zza);
    }
    
    public void zzb(final zzqp$zze zzqp$zze, final String s) {
        List<String> list;
        if ((list = this.zzaRU.get(zzqp$zze)) == null) {
            list = new ArrayList<String>();
            this.zzaRU.put(zzqp$zze, list);
        }
        list.add(s);
    }
}
