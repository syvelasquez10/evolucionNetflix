// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class zzaf$zzj extends zzrr<zzaf$zzj>
{
    public zzaf$zzi[] zziQ;
    public zzaf$zzf zziR;
    public String zziS;
    
    public zzaf$zzj() {
        this.zzP();
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
            if (o instanceof zzaf$zzj) {
                final zzaf$zzj zzaf$zzj = (zzaf$zzj)o;
                b2 = b;
                if (zzrv.equals(this.zziQ, zzaf$zzj.zziQ)) {
                    if (this.zziR == null) {
                        b2 = b;
                        if (zzaf$zzj.zziR != null) {
                            return b2;
                        }
                    }
                    else if (!this.zziR.equals(zzaf$zzj.zziR)) {
                        return false;
                    }
                    if (this.zziS == null) {
                        b2 = b;
                        if (zzaf$zzj.zziS != null) {
                            return b2;
                        }
                    }
                    else if (!this.zziS.equals(zzaf$zzj.zziS)) {
                        return false;
                    }
                    return this.zza(zzaf$zzj);
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        final int hashCode2 = zzrv.hashCode(this.zziQ);
        int hashCode3;
        if (this.zziR == null) {
            hashCode3 = 0;
        }
        else {
            hashCode3 = this.zziR.hashCode();
        }
        if (this.zziS != null) {
            hashCode = this.zziS.hashCode();
        }
        return ((hashCode3 + (hashCode2 + 527) * 31) * 31 + hashCode) * 31 + this.zzDk();
    }
    
    @Override
    protected int zzB() {
        int zzB;
        int n = zzB = super.zzB();
        if (this.zziQ != null) {
            zzB = n;
            if (this.zziQ.length > 0) {
                int n2 = 0;
                while (true) {
                    zzB = n;
                    if (n2 >= this.zziQ.length) {
                        break;
                    }
                    final zzaf$zzi zzaf$zzi = this.zziQ[n2];
                    int n3 = n;
                    if (zzaf$zzi != null) {
                        n3 = n + zzrq.zzc(1, zzaf$zzi);
                    }
                    ++n2;
                    n = n3;
                }
            }
        }
        int n4 = zzB;
        if (this.zziR != null) {
            n4 = zzB + zzrq.zzc(2, this.zziR);
        }
        int n5 = n4;
        if (!this.zziS.equals("")) {
            n5 = n4 + zzrq.zzl(3, this.zziS);
        }
        return n5;
    }
    
    public zzaf$zzj zzP() {
        this.zziQ = zzaf$zzi.zzN();
        this.zziR = null;
        this.zziS = "";
        this.zzbca = null;
        this.zzbcl = -1;
        return this;
    }
    
    @Override
    public void zza(final zzrq zzrq) {
        if (this.zziQ != null && this.zziQ.length > 0) {
            for (int i = 0; i < this.zziQ.length; ++i) {
                final zzaf$zzi zzaf$zzi = this.zziQ[i];
                if (zzaf$zzi != null) {
                    zzrq.zza(1, zzaf$zzi);
                }
            }
        }
        if (this.zziR != null) {
            zzrq.zza(2, this.zziR);
        }
        if (!this.zziS.equals("")) {
            zzrq.zzb(3, this.zziS);
        }
        super.zza(zzrq);
    }
}
