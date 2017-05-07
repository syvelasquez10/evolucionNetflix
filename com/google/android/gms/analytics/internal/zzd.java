// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

public abstract class zzd extends zzc
{
    private boolean zzLA;
    private boolean zzLz;
    
    protected zzd(final zzf zzf) {
        super(zzf);
    }
    
    public boolean isInitialized() {
        return this.zzLz && !this.zzLA;
    }
    
    public void zza() {
        this.zzhB();
        this.zzLz = true;
    }
    
    protected abstract void zzhB();
    
    protected void zzio() {
        if (!this.isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }
}
