// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class zzaf$zzg extends zzrr<zzaf$zzg>
{
    private static volatile zzaf$zzg[] zzit;
    public int[] zziA;
    public int[] zziB;
    public int[] zziC;
    public int[] zziD;
    public int[] zziu;
    public int[] zziv;
    public int[] zziw;
    public int[] zzix;
    public int[] zziy;
    public int[] zziz;
    
    public zzaf$zzg() {
        this.zzL();
    }
    
    public static zzaf$zzg[] zzK() {
        Label_0027: {
            if (zzaf$zzg.zzit != null) {
                break Label_0027;
            }
            synchronized (zzrv.zzbck) {
                if (zzaf$zzg.zzit == null) {
                    zzaf$zzg.zzit = new zzaf$zzg[0];
                }
                return zzaf$zzg.zzit;
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
            if (o instanceof zzaf$zzg) {
                final zzaf$zzg zzaf$zzg = (zzaf$zzg)o;
                b2 = b;
                if (zzrv.equals(this.zziu, zzaf$zzg.zziu)) {
                    b2 = b;
                    if (zzrv.equals(this.zziv, zzaf$zzg.zziv)) {
                        b2 = b;
                        if (zzrv.equals(this.zziw, zzaf$zzg.zziw)) {
                            b2 = b;
                            if (zzrv.equals(this.zzix, zzaf$zzg.zzix)) {
                                b2 = b;
                                if (zzrv.equals(this.zziy, zzaf$zzg.zziy)) {
                                    b2 = b;
                                    if (zzrv.equals(this.zziz, zzaf$zzg.zziz)) {
                                        b2 = b;
                                        if (zzrv.equals(this.zziA, zzaf$zzg.zziA)) {
                                            b2 = b;
                                            if (zzrv.equals(this.zziB, zzaf$zzg.zziB)) {
                                                b2 = b;
                                                if (zzrv.equals(this.zziC, zzaf$zzg.zziC)) {
                                                    b2 = b;
                                                    if (zzrv.equals(this.zziD, zzaf$zzg.zziD)) {
                                                        return this.zza(zzaf$zzg);
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
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return ((((((((((zzrv.hashCode(this.zziu) + 527) * 31 + zzrv.hashCode(this.zziv)) * 31 + zzrv.hashCode(this.zziw)) * 31 + zzrv.hashCode(this.zzix)) * 31 + zzrv.hashCode(this.zziy)) * 31 + zzrv.hashCode(this.zziz)) * 31 + zzrv.hashCode(this.zziA)) * 31 + zzrv.hashCode(this.zziB)) * 31 + zzrv.hashCode(this.zziC)) * 31 + zzrv.hashCode(this.zziD)) * 31 + this.zzDk();
    }
    
    @Override
    protected int zzB() {
        final int n = 0;
        final int zzB = super.zzB();
        int n3;
        if (this.zziu != null && this.zziu.length > 0) {
            int i = 0;
            int n2 = 0;
            while (i < this.zziu.length) {
                n2 += zzrq.zzls(this.zziu[i]);
                ++i;
            }
            n3 = zzB + n2 + this.zziu.length * 1;
        }
        else {
            n3 = zzB;
        }
        int n4 = n3;
        if (this.zziv != null) {
            n4 = n3;
            if (this.zziv.length > 0) {
                int j = 0;
                int n5 = 0;
                while (j < this.zziv.length) {
                    n5 += zzrq.zzls(this.zziv[j]);
                    ++j;
                }
                n4 = n3 + n5 + this.zziv.length * 1;
            }
        }
        int n6 = n4;
        if (this.zziw != null) {
            n6 = n4;
            if (this.zziw.length > 0) {
                int k = 0;
                int n7 = 0;
                while (k < this.zziw.length) {
                    n7 += zzrq.zzls(this.zziw[k]);
                    ++k;
                }
                n6 = n4 + n7 + this.zziw.length * 1;
            }
        }
        int n8 = n6;
        if (this.zzix != null) {
            n8 = n6;
            if (this.zzix.length > 0) {
                int l = 0;
                int n9 = 0;
                while (l < this.zzix.length) {
                    n9 += zzrq.zzls(this.zzix[l]);
                    ++l;
                }
                n8 = n6 + n9 + this.zzix.length * 1;
            }
        }
        int n10 = n8;
        if (this.zziy != null) {
            n10 = n8;
            if (this.zziy.length > 0) {
                int n11 = 0;
                int n12 = 0;
                while (n11 < this.zziy.length) {
                    n12 += zzrq.zzls(this.zziy[n11]);
                    ++n11;
                }
                n10 = n8 + n12 + this.zziy.length * 1;
            }
        }
        int n13 = n10;
        if (this.zziz != null) {
            n13 = n10;
            if (this.zziz.length > 0) {
                int n14 = 0;
                int n15 = 0;
                while (n14 < this.zziz.length) {
                    n15 += zzrq.zzls(this.zziz[n14]);
                    ++n14;
                }
                n13 = n10 + n15 + this.zziz.length * 1;
            }
        }
        int n16 = n13;
        if (this.zziA != null) {
            n16 = n13;
            if (this.zziA.length > 0) {
                int n17 = 0;
                int n18 = 0;
                while (n17 < this.zziA.length) {
                    n18 += zzrq.zzls(this.zziA[n17]);
                    ++n17;
                }
                n16 = n13 + n18 + this.zziA.length * 1;
            }
        }
        int n19 = n16;
        if (this.zziB != null) {
            n19 = n16;
            if (this.zziB.length > 0) {
                int n20 = 0;
                int n21 = 0;
                while (n20 < this.zziB.length) {
                    n21 += zzrq.zzls(this.zziB[n20]);
                    ++n20;
                }
                n19 = n16 + n21 + this.zziB.length * 1;
            }
        }
        int n22 = n19;
        if (this.zziC != null) {
            n22 = n19;
            if (this.zziC.length > 0) {
                int n23 = 0;
                int n24 = 0;
                while (n23 < this.zziC.length) {
                    n24 += zzrq.zzls(this.zziC[n23]);
                    ++n23;
                }
                n22 = n19 + n24 + this.zziC.length * 1;
            }
        }
        int n25 = n22;
        if (this.zziD != null) {
            n25 = n22;
            if (this.zziD.length > 0) {
                int n26 = 0;
                for (int n27 = n; n27 < this.zziD.length; ++n27) {
                    n26 += zzrq.zzls(this.zziD[n27]);
                }
                n25 = n22 + n26 + this.zziD.length * 1;
            }
        }
        return n25;
    }
    
    public zzaf$zzg zzL() {
        this.zziu = zzsa.zzbcn;
        this.zziv = zzsa.zzbcn;
        this.zziw = zzsa.zzbcn;
        this.zzix = zzsa.zzbcn;
        this.zziy = zzsa.zzbcn;
        this.zziz = zzsa.zzbcn;
        this.zziA = zzsa.zzbcn;
        this.zziB = zzsa.zzbcn;
        this.zziC = zzsa.zzbcn;
        this.zziD = zzsa.zzbcn;
        this.zzbca = null;
        this.zzbcl = -1;
        return this;
    }
    
    @Override
    public void zza(final zzrq zzrq) {
        final int n = 0;
        if (this.zziu != null && this.zziu.length > 0) {
            for (int i = 0; i < this.zziu.length; ++i) {
                zzrq.zzz(1, this.zziu[i]);
            }
        }
        if (this.zziv != null && this.zziv.length > 0) {
            for (int j = 0; j < this.zziv.length; ++j) {
                zzrq.zzz(2, this.zziv[j]);
            }
        }
        if (this.zziw != null && this.zziw.length > 0) {
            for (int k = 0; k < this.zziw.length; ++k) {
                zzrq.zzz(3, this.zziw[k]);
            }
        }
        if (this.zzix != null && this.zzix.length > 0) {
            for (int l = 0; l < this.zzix.length; ++l) {
                zzrq.zzz(4, this.zzix[l]);
            }
        }
        if (this.zziy != null && this.zziy.length > 0) {
            for (int n2 = 0; n2 < this.zziy.length; ++n2) {
                zzrq.zzz(5, this.zziy[n2]);
            }
        }
        if (this.zziz != null && this.zziz.length > 0) {
            for (int n3 = 0; n3 < this.zziz.length; ++n3) {
                zzrq.zzz(6, this.zziz[n3]);
            }
        }
        if (this.zziA != null && this.zziA.length > 0) {
            for (int n4 = 0; n4 < this.zziA.length; ++n4) {
                zzrq.zzz(7, this.zziA[n4]);
            }
        }
        if (this.zziB != null && this.zziB.length > 0) {
            for (int n5 = 0; n5 < this.zziB.length; ++n5) {
                zzrq.zzz(8, this.zziB[n5]);
            }
        }
        if (this.zziC != null && this.zziC.length > 0) {
            for (int n6 = 0; n6 < this.zziC.length; ++n6) {
                zzrq.zzz(9, this.zziC[n6]);
            }
        }
        if (this.zziD != null && this.zziD.length > 0) {
            for (int n7 = n; n7 < this.zziD.length; ++n7) {
                zzrq.zzz(10, this.zziD[n7]);
            }
        }
        super.zza(zzrq);
    }
}
