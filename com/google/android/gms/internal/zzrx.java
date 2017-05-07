// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public abstract class zzrx
{
    protected volatile int zzbcl;
    
    public zzrx() {
        this.zzbcl = -1;
    }
    
    @Override
    public String toString() {
        return zzry.zzg(this);
    }
    
    protected int zzB() {
        return 0;
    }
    
    public zzrx zzDm() {
        return (zzrx)super.clone();
    }
    
    public int zzDw() {
        if (this.zzbcl < 0) {
            this.zzDx();
        }
        return this.zzbcl;
    }
    
    public int zzDx() {
        return this.zzbcl = this.zzB();
    }
    
    public void zza(final zzrq zzrq) {
    }
}
