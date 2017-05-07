// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public abstract class zzrr<M extends zzrr<M>> extends zzrx
{
    protected zzrt zzbca;
    
    @Override
    protected int zzB() {
        int n = 0;
        int n3;
        if (this.zzbca != null) {
            int n2 = 0;
            while (true) {
                n3 = n2;
                if (n >= this.zzbca.size()) {
                    break;
                }
                n2 += this.zzbca.zzlB(n).zzB();
                ++n;
            }
        }
        else {
            n3 = 0;
        }
        return n3;
    }
    
    protected final int zzDk() {
        if (this.zzbca == null || this.zzbca.isEmpty()) {
            return 0;
        }
        return this.zzbca.hashCode();
    }
    
    public M zzDl() {
        final zzrr zzrr = (zzrr)super.zzDm();
        zzrv.zza(this, zzrr);
        return (M)zzrr;
    }
    
    @Override
    public void zza(final zzrq zzrq) {
        if (this.zzbca != null) {
            for (int i = 0; i < this.zzbca.size(); ++i) {
                this.zzbca.zzlB(i).zza(zzrq);
            }
        }
    }
    
    protected final boolean zza(final M m) {
        if (this.zzbca == null || this.zzbca.isEmpty()) {
            return m.zzbca == null || m.zzbca.isEmpty();
        }
        return this.zzbca.equals(m.zzbca);
    }
}
