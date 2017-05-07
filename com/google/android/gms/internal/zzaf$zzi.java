// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class zzaf$zzi extends zzrr<zzaf$zzi>
{
    private static volatile zzaf$zzi[] zziN;
    public String name;
    public zzag$zza zziO;
    public zzaf$zzd zziP;
    
    public zzaf$zzi() {
        this.zzO();
    }
    
    public static zzaf$zzi[] zzN() {
        Label_0027: {
            if (zzaf$zzi.zziN != null) {
                break Label_0027;
            }
            synchronized (zzrv.zzbck) {
                if (zzaf$zzi.zziN == null) {
                    zzaf$zzi.zziN = new zzaf$zzi[0];
                }
                return zzaf$zzi.zziN;
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
            if (o instanceof zzaf$zzi) {
                final zzaf$zzi zzaf$zzi = (zzaf$zzi)o;
                if (this.name == null) {
                    b2 = b;
                    if (zzaf$zzi.name != null) {
                        return b2;
                    }
                }
                else if (!this.name.equals(zzaf$zzi.name)) {
                    return false;
                }
                if (this.zziO == null) {
                    b2 = b;
                    if (zzaf$zzi.zziO != null) {
                        return b2;
                    }
                }
                else if (!this.zziO.equals(zzaf$zzi.zziO)) {
                    return false;
                }
                if (this.zziP == null) {
                    b2 = b;
                    if (zzaf$zzi.zziP != null) {
                        return b2;
                    }
                }
                else if (!this.zziP.equals(zzaf$zzi.zziP)) {
                    return false;
                }
                return this.zza(zzaf$zzi);
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.name == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.name.hashCode();
        }
        int hashCode3;
        if (this.zziO == null) {
            hashCode3 = 0;
        }
        else {
            hashCode3 = this.zziO.hashCode();
        }
        if (this.zziP != null) {
            hashCode = this.zziP.hashCode();
        }
        return ((hashCode3 + (hashCode2 + 527) * 31) * 31 + hashCode) * 31 + this.zzDk();
    }
    
    @Override
    protected int zzB() {
        int zzB;
        final int n = zzB = super.zzB();
        if (!this.name.equals("")) {
            zzB = n + zzrq.zzl(1, this.name);
        }
        int n2 = zzB;
        if (this.zziO != null) {
            n2 = zzB + zzrq.zzc(2, this.zziO);
        }
        int n3 = n2;
        if (this.zziP != null) {
            n3 = n2 + zzrq.zzc(3, this.zziP);
        }
        return n3;
    }
    
    public zzaf$zzi zzO() {
        this.name = "";
        this.zziO = null;
        this.zziP = null;
        this.zzbca = null;
        this.zzbcl = -1;
        return this;
    }
    
    @Override
    public void zza(final zzrq zzrq) {
        if (!this.name.equals("")) {
            zzrq.zzb(1, this.name);
        }
        if (this.zziO != null) {
            zzrq.zza(2, this.zziO);
        }
        if (this.zziP != null) {
            zzrq.zza(3, this.zziP);
        }
        super.zza(zzrq);
    }
}
