// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class zzag$zza extends zzrr<zzag$zza>
{
    private static volatile zzag$zza[] zziT;
    public int type;
    public String zziU;
    public zzag$zza[] zziV;
    public zzag$zza[] zziW;
    public zzag$zza[] zziX;
    public String zziY;
    public String zziZ;
    public long zzja;
    public boolean zzjb;
    public zzag$zza[] zzjc;
    public int[] zzjd;
    public boolean zzje;
    
    public zzag$zza() {
        this.zzR();
    }
    
    public static zzag$zza[] zzQ() {
        Label_0027: {
            if (zzag$zza.zziT != null) {
                break Label_0027;
            }
            synchronized (zzrv.zzbck) {
                if (zzag$zza.zziT == null) {
                    zzag$zza.zziT = new zzag$zza[0];
                }
                return zzag$zza.zziT;
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
            if (o instanceof zzag$zza) {
                final zzag$zza zzag$zza = (zzag$zza)o;
                b2 = b;
                if (this.type == zzag$zza.type) {
                    if (this.zziU == null) {
                        b2 = b;
                        if (zzag$zza.zziU != null) {
                            return b2;
                        }
                    }
                    else if (!this.zziU.equals(zzag$zza.zziU)) {
                        return false;
                    }
                    b2 = b;
                    if (zzrv.equals(this.zziV, zzag$zza.zziV)) {
                        b2 = b;
                        if (zzrv.equals(this.zziW, zzag$zza.zziW)) {
                            b2 = b;
                            if (zzrv.equals(this.zziX, zzag$zza.zziX)) {
                                if (this.zziY == null) {
                                    b2 = b;
                                    if (zzag$zza.zziY != null) {
                                        return b2;
                                    }
                                }
                                else if (!this.zziY.equals(zzag$zza.zziY)) {
                                    return false;
                                }
                                if (this.zziZ == null) {
                                    b2 = b;
                                    if (zzag$zza.zziZ != null) {
                                        return b2;
                                    }
                                }
                                else if (!this.zziZ.equals(zzag$zza.zziZ)) {
                                    return false;
                                }
                                b2 = b;
                                if (this.zzja == zzag$zza.zzja) {
                                    b2 = b;
                                    if (this.zzjb == zzag$zza.zzjb) {
                                        b2 = b;
                                        if (zzrv.equals(this.zzjc, zzag$zza.zzjc)) {
                                            b2 = b;
                                            if (zzrv.equals(this.zzjd, zzag$zza.zzjd)) {
                                                b2 = b;
                                                if (this.zzje == zzag$zza.zzje) {
                                                    return this.zza(zzag$zza);
                                                }
                                            }
                                        }
                                    }
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
        int hashCode = 0;
        final int type = this.type;
        int hashCode2;
        if (this.zziU == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.zziU.hashCode();
        }
        final int hashCode3 = zzrv.hashCode(this.zziV);
        final int hashCode4 = zzrv.hashCode(this.zziW);
        final int hashCode5 = zzrv.hashCode(this.zziX);
        int hashCode6;
        if (this.zziY == null) {
            hashCode6 = 0;
        }
        else {
            hashCode6 = this.zziY.hashCode();
        }
        if (this.zziZ != null) {
            hashCode = this.zziZ.hashCode();
        }
        final int n2 = (int)(this.zzja ^ this.zzja >>> 32);
        int n3;
        if (this.zzjb) {
            n3 = 1231;
        }
        else {
            n3 = 1237;
        }
        final int hashCode7 = zzrv.hashCode(this.zzjc);
        final int hashCode8 = zzrv.hashCode(this.zzjd);
        if (!this.zzje) {
            n = 1237;
        }
        return ((((n3 + (((hashCode6 + ((((hashCode2 + (type + 527) * 31) * 31 + hashCode3) * 31 + hashCode4) * 31 + hashCode5) * 31) * 31 + hashCode) * 31 + n2) * 31) * 31 + hashCode7) * 31 + hashCode8) * 31 + n) * 31 + this.zzDk();
    }
    
    @Override
    protected int zzB() {
        final int n = 0;
        int n3;
        final int n2 = n3 = super.zzB() + zzrq.zzB(1, this.type);
        if (!this.zziU.equals("")) {
            n3 = n2 + zzrq.zzl(2, this.zziU);
        }
        int n4 = n3;
        if (this.zziV != null) {
            n4 = n3;
            if (this.zziV.length > 0) {
                int n5;
                for (int i = 0; i < this.zziV.length; ++i, n3 = n5) {
                    final zzag$zza zzag$zza = this.zziV[i];
                    n5 = n3;
                    if (zzag$zza != null) {
                        n5 = n3 + zzrq.zzc(3, zzag$zza);
                    }
                }
                n4 = n3;
            }
        }
        int n6 = n4;
        if (this.zziW != null) {
            n6 = n4;
            if (this.zziW.length > 0) {
                n6 = n4;
                int n7;
                for (int j = 0; j < this.zziW.length; ++j, n6 = n7) {
                    final zzag$zza zzag$zza2 = this.zziW[j];
                    n7 = n6;
                    if (zzag$zza2 != null) {
                        n7 = n6 + zzrq.zzc(4, zzag$zza2);
                    }
                }
            }
        }
        int n8 = n6;
        if (this.zziX != null) {
            n8 = n6;
            if (this.zziX.length > 0) {
                int n9;
                for (int k = 0; k < this.zziX.length; ++k, n6 = n9) {
                    final zzag$zza zzag$zza3 = this.zziX[k];
                    n9 = n6;
                    if (zzag$zza3 != null) {
                        n9 = n6 + zzrq.zzc(5, zzag$zza3);
                    }
                }
                n8 = n6;
            }
        }
        int n10 = n8;
        if (!this.zziY.equals("")) {
            n10 = n8 + zzrq.zzl(6, this.zziY);
        }
        int n11 = n10;
        if (!this.zziZ.equals("")) {
            n11 = n10 + zzrq.zzl(7, this.zziZ);
        }
        int n12 = n11;
        if (this.zzja != 0L) {
            n12 = n11 + zzrq.zzd(8, this.zzja);
        }
        int n13 = n12;
        if (this.zzje) {
            n13 = n12 + zzrq.zzc(9, this.zzje);
        }
        int n14 = n13;
        if (this.zzjd != null) {
            n14 = n13;
            if (this.zzjd.length > 0) {
                int l = 0;
                int n15 = 0;
                while (l < this.zzjd.length) {
                    n15 += zzrq.zzls(this.zzjd[l]);
                    ++l;
                }
                n14 = n13 + n15 + this.zzjd.length * 1;
            }
        }
        int n16 = n14;
        if (this.zzjc != null) {
            n16 = n14;
            if (this.zzjc.length > 0) {
                int n17 = n;
                while (true) {
                    n16 = n14;
                    if (n17 >= this.zzjc.length) {
                        break;
                    }
                    final zzag$zza zzag$zza4 = this.zzjc[n17];
                    int n18 = n14;
                    if (zzag$zza4 != null) {
                        n18 = n14 + zzrq.zzc(11, zzag$zza4);
                    }
                    ++n17;
                    n14 = n18;
                }
            }
        }
        int n19 = n16;
        if (this.zzjb) {
            n19 = n16 + zzrq.zzc(12, this.zzjb);
        }
        return n19;
    }
    
    public zzag$zza zzR() {
        this.type = 1;
        this.zziU = "";
        this.zziV = zzQ();
        this.zziW = zzQ();
        this.zziX = zzQ();
        this.zziY = "";
        this.zziZ = "";
        this.zzja = 0L;
        this.zzjb = false;
        this.zzjc = zzQ();
        this.zzjd = zzsa.zzbcn;
        this.zzje = false;
        this.zzbca = null;
        this.zzbcl = -1;
        return this;
    }
    
    @Override
    public void zza(final zzrq zzrq) {
        final int n = 0;
        zzrq.zzz(1, this.type);
        if (!this.zziU.equals("")) {
            zzrq.zzb(2, this.zziU);
        }
        if (this.zziV != null && this.zziV.length > 0) {
            for (int i = 0; i < this.zziV.length; ++i) {
                final zzag$zza zzag$zza = this.zziV[i];
                if (zzag$zza != null) {
                    zzrq.zza(3, zzag$zza);
                }
            }
        }
        if (this.zziW != null && this.zziW.length > 0) {
            for (int j = 0; j < this.zziW.length; ++j) {
                final zzag$zza zzag$zza2 = this.zziW[j];
                if (zzag$zza2 != null) {
                    zzrq.zza(4, zzag$zza2);
                }
            }
        }
        if (this.zziX != null && this.zziX.length > 0) {
            for (int k = 0; k < this.zziX.length; ++k) {
                final zzag$zza zzag$zza3 = this.zziX[k];
                if (zzag$zza3 != null) {
                    zzrq.zza(5, zzag$zza3);
                }
            }
        }
        if (!this.zziY.equals("")) {
            zzrq.zzb(6, this.zziY);
        }
        if (!this.zziZ.equals("")) {
            zzrq.zzb(7, this.zziZ);
        }
        if (this.zzja != 0L) {
            zzrq.zzb(8, this.zzja);
        }
        if (this.zzje) {
            zzrq.zzb(9, this.zzje);
        }
        if (this.zzjd != null && this.zzjd.length > 0) {
            for (int l = 0; l < this.zzjd.length; ++l) {
                zzrq.zzz(10, this.zzjd[l]);
            }
        }
        if (this.zzjc != null && this.zzjc.length > 0) {
            for (int n2 = n; n2 < this.zzjc.length; ++n2) {
                final zzag$zza zzag$zza4 = this.zzjc[n2];
                if (zzag$zza4 != null) {
                    zzrq.zza(11, zzag$zza4);
                }
            }
        }
        if (this.zzjb) {
            zzrq.zzb(12, this.zzjb);
        }
        super.zza(zzrq);
    }
}
