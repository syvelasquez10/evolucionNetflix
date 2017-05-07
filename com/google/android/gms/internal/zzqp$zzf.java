// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

public class zzqp$zzf
{
    private final List<zzqp$zza> zzaTW;
    private final List<zzqp$zza> zzaTX;
    private final List<zzqp$zza> zzaTY;
    private final List<zzqp$zza> zzaTZ;
    private final List<zzqp$zza> zzaUa;
    private final List<zzqp$zza> zzaUb;
    private final List<String> zzaUc;
    private final List<String> zzaUd;
    private final List<String> zzaUe;
    private final List<String> zzaUf;
    
    private zzqp$zzf() {
        this.zzaTW = new ArrayList<zzqp$zza>();
        this.zzaTX = new ArrayList<zzqp$zza>();
        this.zzaTY = new ArrayList<zzqp$zza>();
        this.zzaTZ = new ArrayList<zzqp$zza>();
        this.zzaUa = new ArrayList<zzqp$zza>();
        this.zzaUb = new ArrayList<zzqp$zza>();
        this.zzaUc = new ArrayList<String>();
        this.zzaUd = new ArrayList<String>();
        this.zzaUe = new ArrayList<String>();
        this.zzaUf = new ArrayList<String>();
    }
    
    public zzqp$zze zzBU() {
        return new zzqp$zze(this.zzaTW, this.zzaTX, this.zzaTY, this.zzaTZ, this.zzaUa, this.zzaUb, this.zzaUc, this.zzaUd, this.zzaUe, this.zzaUf, null);
    }
    
    public zzqp$zzf zzd(final zzqp$zza zzqp$zza) {
        this.zzaTW.add(zzqp$zza);
        return this;
    }
    
    public zzqp$zzf zze(final zzqp$zza zzqp$zza) {
        this.zzaTX.add(zzqp$zza);
        return this;
    }
    
    public zzqp$zzf zzf(final zzqp$zza zzqp$zza) {
        this.zzaTY.add(zzqp$zza);
        return this;
    }
    
    public zzqp$zzf zzfl(final String s) {
        this.zzaUe.add(s);
        return this;
    }
    
    public zzqp$zzf zzfm(final String s) {
        this.zzaUf.add(s);
        return this;
    }
    
    public zzqp$zzf zzfn(final String s) {
        this.zzaUc.add(s);
        return this;
    }
    
    public zzqp$zzf zzfo(final String s) {
        this.zzaUd.add(s);
        return this;
    }
    
    public zzqp$zzf zzg(final zzqp$zza zzqp$zza) {
        this.zzaTZ.add(zzqp$zza);
        return this;
    }
    
    public zzqp$zzf zzh(final zzqp$zza zzqp$zza) {
        this.zzaUa.add(zzqp$zza);
        return this;
    }
    
    public zzqp$zzf zzi(final zzqp$zza zzqp$zza) {
        this.zzaUb.add(zzqp$zza);
        return this;
    }
}
