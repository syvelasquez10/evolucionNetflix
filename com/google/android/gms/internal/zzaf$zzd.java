// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class zzaf$zzd extends zzrr<zzaf$zzd>
{
    public zzag$zza[] zzhZ;
    public zzag$zza[] zzia;
    public zzaf$zzc[] zzib;
    
    public zzaf$zzd() {
        this.zzG();
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
            if (o instanceof zzaf$zzd) {
                final zzaf$zzd zzaf$zzd = (zzaf$zzd)o;
                b2 = b;
                if (zzrv.equals(this.zzhZ, zzaf$zzd.zzhZ)) {
                    b2 = b;
                    if (zzrv.equals(this.zzia, zzaf$zzd.zzia)) {
                        b2 = b;
                        if (zzrv.equals(this.zzib, zzaf$zzd.zzib)) {
                            return this.zza(zzaf$zzd);
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return (((zzrv.hashCode(this.zzhZ) + 527) * 31 + zzrv.hashCode(this.zzia)) * 31 + zzrv.hashCode(this.zzib)) * 31 + this.zzDk();
    }
    
    @Override
    protected int zzB() {
        final int n = 0;
        int zzB;
        int n2 = zzB = super.zzB();
        if (this.zzhZ != null) {
            zzB = n2;
            if (this.zzhZ.length > 0) {
                int n3;
                for (int i = 0; i < this.zzhZ.length; ++i, n2 = n3) {
                    final zzag$zza zzag$zza = this.zzhZ[i];
                    n3 = n2;
                    if (zzag$zza != null) {
                        n3 = n2 + zzrq.zzc(1, zzag$zza);
                    }
                }
                zzB = n2;
            }
        }
        int n4 = zzB;
        if (this.zzia != null) {
            n4 = zzB;
            if (this.zzia.length > 0) {
                n4 = zzB;
                int n5;
                for (int j = 0; j < this.zzia.length; ++j, n4 = n5) {
                    final zzag$zza zzag$zza2 = this.zzia[j];
                    n5 = n4;
                    if (zzag$zza2 != null) {
                        n5 = n4 + zzrq.zzc(2, zzag$zza2);
                    }
                }
            }
        }
        int n6 = n4;
        if (this.zzib != null) {
            n6 = n4;
            if (this.zzib.length > 0) {
                int n7 = n;
                while (true) {
                    n6 = n4;
                    if (n7 >= this.zzib.length) {
                        break;
                    }
                    final zzaf$zzc zzaf$zzc = this.zzib[n7];
                    int n8 = n4;
                    if (zzaf$zzc != null) {
                        n8 = n4 + zzrq.zzc(3, zzaf$zzc);
                    }
                    ++n7;
                    n4 = n8;
                }
            }
        }
        return n6;
    }
    
    public zzaf$zzd zzG() {
        this.zzhZ = zzag$zza.zzQ();
        this.zzia = zzag$zza.zzQ();
        this.zzib = zzaf$zzc.zzE();
        this.zzbca = null;
        this.zzbcl = -1;
        return this;
    }
    
    @Override
    public void zza(final zzrq zzrq) {
        final int n = 0;
        if (this.zzhZ != null && this.zzhZ.length > 0) {
            for (int i = 0; i < this.zzhZ.length; ++i) {
                final zzag$zza zzag$zza = this.zzhZ[i];
                if (zzag$zza != null) {
                    zzrq.zza(1, zzag$zza);
                }
            }
        }
        if (this.zzia != null && this.zzia.length > 0) {
            for (int j = 0; j < this.zzia.length; ++j) {
                final zzag$zza zzag$zza2 = this.zzia[j];
                if (zzag$zza2 != null) {
                    zzrq.zza(2, zzag$zza2);
                }
            }
        }
        if (this.zzib != null && this.zzib.length > 0) {
            for (int k = n; k < this.zzib.length; ++k) {
                final zzaf$zzc zzaf$zzc = this.zzib[k];
                if (zzaf$zzc != null) {
                    zzrq.zza(3, zzaf$zzc);
                }
            }
        }
        super.zza(zzrq);
    }
}
