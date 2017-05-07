// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public class zzd implements zzo
{
    private int zzo;
    private int zzp;
    private final int zzq;
    private final float zzr;
    
    public zzd() {
        this(2500, 1, 1.0f);
    }
    
    public zzd(final int zzo, final int zzq, final float zzr) {
        this.zzo = zzo;
        this.zzq = zzq;
        this.zzr = zzr;
    }
    
    @Override
    public void zza(final zzr zzr) {
        ++this.zzp;
        this.zzo += (int)(this.zzo * this.zzr);
        if (!this.zzf()) {
            throw zzr;
        }
    }
    
    @Override
    public int zzd() {
        return this.zzo;
    }
    
    @Override
    public int zze() {
        return this.zzp;
    }
    
    protected boolean zzf() {
        return this.zzp <= this.zzq;
    }
}
