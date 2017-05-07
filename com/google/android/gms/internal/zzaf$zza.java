// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class zzaf$zza extends zzrr<zzaf$zza>
{
    public int level;
    public int zzhN;
    public int zzhO;
    
    public zzaf$zza() {
        this.zzA();
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = false;
        boolean b2;
        if (o == this) {
            b2 = true;
        }
        else {
            b2 = b;
            if (o instanceof zzaf$zza) {
                final zzaf$zza zzaf$zza = (zzaf$zza)o;
                b2 = b;
                if (this.level == zzaf$zza.level) {
                    b2 = b;
                    if (this.zzhN == zzaf$zza.zzhN) {
                        b2 = b;
                        if (this.zzhO == zzaf$zza.zzhO) {
                            return this.zza(zzaf$zza);
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return (((this.level + 527) * 31 + this.zzhN) * 31 + this.zzhO) * 31 + this.zzDk();
    }
    
    public zzaf$zza zzA() {
        this.level = 1;
        this.zzhN = 0;
        this.zzhO = 0;
        this.zzbca = null;
        this.zzbcl = -1;
        return this;
    }
    
    @Override
    protected int zzB() {
        int zzB;
        final int n = zzB = super.zzB();
        if (this.level != 1) {
            zzB = n + zzrq.zzB(1, this.level);
        }
        int n2 = zzB;
        if (this.zzhN != 0) {
            n2 = zzB + zzrq.zzB(2, this.zzhN);
        }
        int n3 = n2;
        if (this.zzhO != 0) {
            n3 = n2 + zzrq.zzB(3, this.zzhO);
        }
        return n3;
    }
    
    @Override
    public void zza(final zzrq zzrq) {
        if (this.level != 1) {
            zzrq.zzz(1, this.level);
        }
        if (this.zzhN != 0) {
            zzrq.zzz(2, this.zzhN);
        }
        if (this.zzhO != 0) {
            zzrq.zzz(3, this.zzhO);
        }
        super.zza(zzrq);
    }
}
