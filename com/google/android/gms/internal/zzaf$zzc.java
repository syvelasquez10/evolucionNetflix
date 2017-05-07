// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class zzaf$zzc extends zzrr<zzaf$zzc>
{
    private static volatile zzaf$zzc[] zzhU;
    public String key;
    public long zzhV;
    public long zzhW;
    public boolean zzhX;
    public long zzhY;
    
    public zzaf$zzc() {
        this.zzF();
    }
    
    public static zzaf$zzc[] zzE() {
        Label_0027: {
            if (zzaf$zzc.zzhU != null) {
                break Label_0027;
            }
            synchronized (zzrv.zzbck) {
                if (zzaf$zzc.zzhU == null) {
                    zzaf$zzc.zzhU = new zzaf$zzc[0];
                }
                return zzaf$zzc.zzhU;
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
            if (o instanceof zzaf$zzc) {
                final zzaf$zzc zzaf$zzc = (zzaf$zzc)o;
                if (this.key == null) {
                    b2 = b;
                    if (zzaf$zzc.key != null) {
                        return b2;
                    }
                }
                else if (!this.key.equals(zzaf$zzc.key)) {
                    return false;
                }
                b2 = b;
                if (this.zzhV == zzaf$zzc.zzhV) {
                    b2 = b;
                    if (this.zzhW == zzaf$zzc.zzhW) {
                        b2 = b;
                        if (this.zzhX == zzaf$zzc.zzhX) {
                            b2 = b;
                            if (this.zzhY == zzaf$zzc.zzhY) {
                                return this.zza(zzaf$zzc);
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
        int hashCode;
        if (this.key == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.key.hashCode();
        }
        final int n = (int)(this.zzhV ^ this.zzhV >>> 32);
        final int n2 = (int)(this.zzhW ^ this.zzhW >>> 32);
        int n3;
        if (this.zzhX) {
            n3 = 1231;
        }
        else {
            n3 = 1237;
        }
        return ((n3 + (((hashCode + 527) * 31 + n) * 31 + n2) * 31) * 31 + (int)(this.zzhY ^ this.zzhY >>> 32)) * 31 + this.zzDk();
    }
    
    @Override
    protected int zzB() {
        int zzB;
        final int n = zzB = super.zzB();
        if (!this.key.equals("")) {
            zzB = n + zzrq.zzl(1, this.key);
        }
        int n2 = zzB;
        if (this.zzhV != 0L) {
            n2 = zzB + zzrq.zzd(2, this.zzhV);
        }
        int n3 = n2;
        if (this.zzhW != 2147483647L) {
            n3 = n2 + zzrq.zzd(3, this.zzhW);
        }
        int n4 = n3;
        if (this.zzhX) {
            n4 = n3 + zzrq.zzc(4, this.zzhX);
        }
        int n5 = n4;
        if (this.zzhY != 0L) {
            n5 = n4 + zzrq.zzd(5, this.zzhY);
        }
        return n5;
    }
    
    public zzaf$zzc zzF() {
        this.key = "";
        this.zzhV = 0L;
        this.zzhW = 2147483647L;
        this.zzhX = false;
        this.zzhY = 0L;
        this.zzbca = null;
        this.zzbcl = -1;
        return this;
    }
    
    @Override
    public void zza(final zzrq zzrq) {
        if (!this.key.equals("")) {
            zzrq.zzb(1, this.key);
        }
        if (this.zzhV != 0L) {
            zzrq.zzb(2, this.zzhV);
        }
        if (this.zzhW != 2147483647L) {
            zzrq.zzb(3, this.zzhW);
        }
        if (this.zzhX) {
            zzrq.zzb(4, this.zzhX);
        }
        if (this.zzhY != 0L) {
            zzrq.zzb(5, this.zzhY);
        }
        super.zza(zzrq);
    }
}
