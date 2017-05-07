// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class zzaf$zzb extends zzrr<zzaf$zzb>
{
    private static volatile zzaf$zzb[] zzhP;
    public int name;
    public int[] zzhQ;
    public int zzhR;
    public boolean zzhS;
    public boolean zzhT;
    
    public zzaf$zzb() {
        this.zzD();
    }
    
    public static zzaf$zzb[] zzC() {
        Label_0027: {
            if (zzaf$zzb.zzhP != null) {
                break Label_0027;
            }
            synchronized (zzrv.zzbck) {
                if (zzaf$zzb.zzhP == null) {
                    zzaf$zzb.zzhP = new zzaf$zzb[0];
                }
                return zzaf$zzb.zzhP;
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
            if (o instanceof zzaf$zzb) {
                final zzaf$zzb zzaf$zzb = (zzaf$zzb)o;
                b2 = b;
                if (zzrv.equals(this.zzhQ, zzaf$zzb.zzhQ)) {
                    b2 = b;
                    if (this.zzhR == zzaf$zzb.zzhR) {
                        b2 = b;
                        if (this.name == zzaf$zzb.name) {
                            b2 = b;
                            if (this.zzhS == zzaf$zzb.zzhS) {
                                b2 = b;
                                if (this.zzhT == zzaf$zzb.zzhT) {
                                    return this.zza(zzaf$zzb);
                                }
                            }
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        int n = 1231;
        final int hashCode = zzrv.hashCode(this.zzhQ);
        final int zzhR = this.zzhR;
        final int name = this.name;
        int n2;
        if (this.zzhS) {
            n2 = 1231;
        }
        else {
            n2 = 1237;
        }
        if (!this.zzhT) {
            n = 1237;
        }
        return ((n2 + (((hashCode + 527) * 31 + zzhR) * 31 + name) * 31) * 31 + n) * 31 + this.zzDk();
    }
    
    @Override
    protected int zzB() {
        int n = 0;
        int zzB = super.zzB();
        if (this.zzhT) {
            zzB += zzrq.zzc(1, this.zzhT);
        }
        final int n2 = zzrq.zzB(2, this.zzhR) + zzB;
        int n3;
        if (this.zzhQ != null && this.zzhQ.length > 0) {
            for (int i = 0; i < this.zzhQ.length; ++i) {
                n += zzrq.zzls(this.zzhQ[i]);
            }
            n3 = n2 + n + this.zzhQ.length * 1;
        }
        else {
            n3 = n2;
        }
        int n4 = n3;
        if (this.name != 0) {
            n4 = n3 + zzrq.zzB(4, this.name);
        }
        int n5 = n4;
        if (this.zzhS) {
            n5 = n4 + zzrq.zzc(6, this.zzhS);
        }
        return n5;
    }
    
    public zzaf$zzb zzD() {
        this.zzhQ = zzsa.zzbcn;
        this.zzhR = 0;
        this.name = 0;
        this.zzhS = false;
        this.zzhT = false;
        this.zzbca = null;
        this.zzbcl = -1;
        return this;
    }
    
    @Override
    public void zza(final zzrq zzrq) {
        if (this.zzhT) {
            zzrq.zzb(1, this.zzhT);
        }
        zzrq.zzz(2, this.zzhR);
        if (this.zzhQ != null && this.zzhQ.length > 0) {
            for (int i = 0; i < this.zzhQ.length; ++i) {
                zzrq.zzz(3, this.zzhQ[i]);
            }
        }
        if (this.name != 0) {
            zzrq.zzz(4, this.name);
        }
        if (this.zzhS) {
            zzrq.zzb(6, this.zzhS);
        }
        super.zza(zzrq);
    }
}
