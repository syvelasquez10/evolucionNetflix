// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class zzaf$zze extends zzrr<zzaf$zze>
{
    private static volatile zzaf$zze[] zzic;
    public int key;
    public int value;
    
    public zzaf$zze() {
        this.zzI();
    }
    
    public static zzaf$zze[] zzH() {
        Label_0027: {
            if (zzaf$zze.zzic != null) {
                break Label_0027;
            }
            synchronized (zzrv.zzbck) {
                if (zzaf$zze.zzic == null) {
                    zzaf$zze.zzic = new zzaf$zze[0];
                }
                return zzaf$zze.zzic;
            }
        }
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
            if (o instanceof zzaf$zze) {
                final zzaf$zze zzaf$zze = (zzaf$zze)o;
                b2 = b;
                if (this.key == zzaf$zze.key) {
                    b2 = b;
                    if (this.value == zzaf$zze.value) {
                        return this.zza(zzaf$zze);
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return ((this.key + 527) * 31 + this.value) * 31 + this.zzDk();
    }
    
    @Override
    protected int zzB() {
        return super.zzB() + zzrq.zzB(1, this.key) + zzrq.zzB(2, this.value);
    }
    
    public zzaf$zze zzI() {
        this.key = 0;
        this.value = 0;
        this.zzbca = null;
        this.zzbcl = -1;
        return this;
    }
    
    @Override
    public void zza(final zzrq zzrq) {
        zzrq.zzz(1, this.key);
        zzrq.zzz(2, this.value);
        super.zza(zzrq);
    }
}
