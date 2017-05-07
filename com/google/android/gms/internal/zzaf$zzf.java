// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class zzaf$zzf extends zzrr<zzaf$zzf>
{
    public String version;
    public String[] zzid;
    public String[] zzie;
    public zzag$zza[] zzif;
    public zzaf$zze[] zzig;
    public zzaf$zzb[] zzih;
    public zzaf$zzb[] zzii;
    public zzaf$zzb[] zzij;
    public zzaf$zzg[] zzik;
    public String zzil;
    public String zzim;
    public String zzin;
    public zzaf$zza zzio;
    public float zzip;
    public boolean zziq;
    public String[] zzir;
    public int zzis;
    
    public zzaf$zzf() {
        this.zzJ();
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
            if (o instanceof zzaf$zzf) {
                final zzaf$zzf zzaf$zzf = (zzaf$zzf)o;
                b2 = b;
                if (zzrv.equals(this.zzid, zzaf$zzf.zzid)) {
                    b2 = b;
                    if (zzrv.equals(this.zzie, zzaf$zzf.zzie)) {
                        b2 = b;
                        if (zzrv.equals(this.zzif, zzaf$zzf.zzif)) {
                            b2 = b;
                            if (zzrv.equals(this.zzig, zzaf$zzf.zzig)) {
                                b2 = b;
                                if (zzrv.equals(this.zzih, zzaf$zzf.zzih)) {
                                    b2 = b;
                                    if (zzrv.equals(this.zzii, zzaf$zzf.zzii)) {
                                        b2 = b;
                                        if (zzrv.equals(this.zzij, zzaf$zzf.zzij)) {
                                            b2 = b;
                                            if (zzrv.equals(this.zzik, zzaf$zzf.zzik)) {
                                                if (this.zzil == null) {
                                                    b2 = b;
                                                    if (zzaf$zzf.zzil != null) {
                                                        return b2;
                                                    }
                                                }
                                                else if (!this.zzil.equals(zzaf$zzf.zzil)) {
                                                    return false;
                                                }
                                                if (this.zzim == null) {
                                                    b2 = b;
                                                    if (zzaf$zzf.zzim != null) {
                                                        return b2;
                                                    }
                                                }
                                                else if (!this.zzim.equals(zzaf$zzf.zzim)) {
                                                    return false;
                                                }
                                                if (this.zzin == null) {
                                                    b2 = b;
                                                    if (zzaf$zzf.zzin != null) {
                                                        return b2;
                                                    }
                                                }
                                                else if (!this.zzin.equals(zzaf$zzf.zzin)) {
                                                    return false;
                                                }
                                                if (this.version == null) {
                                                    b2 = b;
                                                    if (zzaf$zzf.version != null) {
                                                        return b2;
                                                    }
                                                }
                                                else if (!this.version.equals(zzaf$zzf.version)) {
                                                    return false;
                                                }
                                                if (this.zzio == null) {
                                                    b2 = b;
                                                    if (zzaf$zzf.zzio != null) {
                                                        return b2;
                                                    }
                                                }
                                                else if (!this.zzio.equals(zzaf$zzf.zzio)) {
                                                    return false;
                                                }
                                                b2 = b;
                                                if (Float.floatToIntBits(this.zzip) == Float.floatToIntBits(zzaf$zzf.zzip)) {
                                                    b2 = b;
                                                    if (this.zziq == zzaf$zzf.zziq) {
                                                        b2 = b;
                                                        if (zzrv.equals(this.zzir, zzaf$zzf.zzir)) {
                                                            b2 = b;
                                                            if (this.zzis == zzaf$zzf.zzis) {
                                                                return this.zza(zzaf$zzf);
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
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        final int hashCode2 = zzrv.hashCode(this.zzid);
        final int hashCode3 = zzrv.hashCode(this.zzie);
        final int hashCode4 = zzrv.hashCode(this.zzif);
        final int hashCode5 = zzrv.hashCode(this.zzig);
        final int hashCode6 = zzrv.hashCode(this.zzih);
        final int hashCode7 = zzrv.hashCode(this.zzii);
        final int hashCode8 = zzrv.hashCode(this.zzij);
        final int hashCode9 = zzrv.hashCode(this.zzik);
        int hashCode10;
        if (this.zzil == null) {
            hashCode10 = 0;
        }
        else {
            hashCode10 = this.zzil.hashCode();
        }
        int hashCode11;
        if (this.zzim == null) {
            hashCode11 = 0;
        }
        else {
            hashCode11 = this.zzim.hashCode();
        }
        int hashCode12;
        if (this.zzin == null) {
            hashCode12 = 0;
        }
        else {
            hashCode12 = this.zzin.hashCode();
        }
        int hashCode13;
        if (this.version == null) {
            hashCode13 = 0;
        }
        else {
            hashCode13 = this.version.hashCode();
        }
        if (this.zzio != null) {
            hashCode = this.zzio.hashCode();
        }
        final int floatToIntBits = Float.floatToIntBits(this.zzip);
        int n;
        if (this.zziq) {
            n = 1231;
        }
        else {
            n = 1237;
        }
        return (((n + (((hashCode13 + (hashCode12 + (hashCode11 + (hashCode10 + ((((((((hashCode2 + 527) * 31 + hashCode3) * 31 + hashCode4) * 31 + hashCode5) * 31 + hashCode6) * 31 + hashCode7) * 31 + hashCode8) * 31 + hashCode9) * 31) * 31) * 31) * 31) * 31 + hashCode) * 31 + floatToIntBits) * 31) * 31 + zzrv.hashCode(this.zzir)) * 31 + this.zzis) * 31 + this.zzDk();
    }
    
    @Override
    protected int zzB() {
        final int n = 0;
        final int zzB = super.zzB();
        int n6;
        if (this.zzie != null && this.zzie.length > 0) {
            int i = 0;
            int n2 = 0;
            int n3 = 0;
            while (i < this.zzie.length) {
                final String s = this.zzie[i];
                int n4 = n2;
                int n5 = n3;
                if (s != null) {
                    n5 = n3 + 1;
                    n4 = n2 + zzrq.zzfy(s);
                }
                ++i;
                n2 = n4;
                n3 = n5;
            }
            n6 = zzB + n2 + n3 * 1;
        }
        else {
            n6 = zzB;
        }
        int n7 = n6;
        if (this.zzif != null) {
            n7 = n6;
            if (this.zzif.length > 0) {
                n7 = n6;
                int n8;
                for (int j = 0; j < this.zzif.length; ++j, n7 = n8) {
                    final zzag$zza zzag$zza = this.zzif[j];
                    n8 = n7;
                    if (zzag$zza != null) {
                        n8 = n7 + zzrq.zzc(2, zzag$zza);
                    }
                }
            }
        }
        int n9 = n7;
        if (this.zzig != null) {
            n9 = n7;
            if (this.zzig.length > 0) {
                int n10;
                for (int k = 0; k < this.zzig.length; ++k, n7 = n10) {
                    final zzaf$zze zzaf$zze = this.zzig[k];
                    n10 = n7;
                    if (zzaf$zze != null) {
                        n10 = n7 + zzrq.zzc(3, zzaf$zze);
                    }
                }
                n9 = n7;
            }
        }
        int n11 = n9;
        if (this.zzih != null) {
            n11 = n9;
            if (this.zzih.length > 0) {
                n11 = n9;
                int n12;
                for (int l = 0; l < this.zzih.length; ++l, n11 = n12) {
                    final zzaf$zzb zzaf$zzb = this.zzih[l];
                    n12 = n11;
                    if (zzaf$zzb != null) {
                        n12 = n11 + zzrq.zzc(4, zzaf$zzb);
                    }
                }
            }
        }
        int n13 = n11;
        if (this.zzii != null) {
            n13 = n11;
            if (this.zzii.length > 0) {
                int n15;
                for (int n14 = 0; n14 < this.zzii.length; ++n14, n11 = n15) {
                    final zzaf$zzb zzaf$zzb2 = this.zzii[n14];
                    n15 = n11;
                    if (zzaf$zzb2 != null) {
                        n15 = n11 + zzrq.zzc(5, zzaf$zzb2);
                    }
                }
                n13 = n11;
            }
        }
        int n16 = n13;
        if (this.zzij != null) {
            n16 = n13;
            if (this.zzij.length > 0) {
                n16 = n13;
                int n18;
                for (int n17 = 0; n17 < this.zzij.length; ++n17, n16 = n18) {
                    final zzaf$zzb zzaf$zzb3 = this.zzij[n17];
                    n18 = n16;
                    if (zzaf$zzb3 != null) {
                        n18 = n16 + zzrq.zzc(6, zzaf$zzb3);
                    }
                }
            }
        }
        int n19 = n16;
        if (this.zzik != null) {
            n19 = n16;
            if (this.zzik.length > 0) {
                int n21;
                for (int n20 = 0; n20 < this.zzik.length; ++n20, n16 = n21) {
                    final zzaf$zzg zzaf$zzg = this.zzik[n20];
                    n21 = n16;
                    if (zzaf$zzg != null) {
                        n21 = n16 + zzrq.zzc(7, zzaf$zzg);
                    }
                }
                n19 = n16;
            }
        }
        int n22 = n19;
        if (!this.zzil.equals("")) {
            n22 = n19 + zzrq.zzl(9, this.zzil);
        }
        int n23 = n22;
        if (!this.zzim.equals("")) {
            n23 = n22 + zzrq.zzl(10, this.zzim);
        }
        int n24 = n23;
        if (!this.zzin.equals("0")) {
            n24 = n23 + zzrq.zzl(12, this.zzin);
        }
        int n25 = n24;
        if (!this.version.equals("")) {
            n25 = n24 + zzrq.zzl(13, this.version);
        }
        int n26 = n25;
        if (this.zzio != null) {
            n26 = n25 + zzrq.zzc(14, this.zzio);
        }
        int n27 = n26;
        if (Float.floatToIntBits(this.zzip) != Float.floatToIntBits(0.0f)) {
            n27 = n26 + zzrq.zzc(15, this.zzip);
        }
        int n28 = n27;
        if (this.zzir != null) {
            n28 = n27;
            if (this.zzir.length > 0) {
                int n29 = 0;
                int n30 = 0;
                int n31 = 0;
                while (n29 < this.zzir.length) {
                    final String s2 = this.zzir[n29];
                    int n32 = n30;
                    int n33 = n31;
                    if (s2 != null) {
                        n33 = n31 + 1;
                        n32 = n30 + zzrq.zzfy(s2);
                    }
                    ++n29;
                    n30 = n32;
                    n31 = n33;
                }
                n28 = n27 + n30 + n31 * 2;
            }
        }
        int n34 = n28;
        if (this.zzis != 0) {
            n34 = n28 + zzrq.zzB(17, this.zzis);
        }
        int n35 = n34;
        if (this.zziq) {
            n35 = n34 + zzrq.zzc(18, this.zziq);
        }
        int n36 = n35;
        if (this.zzid != null) {
            n36 = n35;
            if (this.zzid.length > 0) {
                int n37 = 0;
                int n38 = 0;
                int n40;
                int n41;
                for (int n39 = n; n39 < this.zzid.length; ++n39, n37 = n40, n38 = n41) {
                    final String s3 = this.zzid[n39];
                    n40 = n37;
                    n41 = n38;
                    if (s3 != null) {
                        n41 = n38 + 1;
                        n40 = n37 + zzrq.zzfy(s3);
                    }
                }
                n36 = n35 + n37 + n38 * 2;
            }
        }
        return n36;
    }
    
    public zzaf$zzf zzJ() {
        this.zzid = zzsa.zzbcs;
        this.zzie = zzsa.zzbcs;
        this.zzif = zzag$zza.zzQ();
        this.zzig = zzaf$zze.zzH();
        this.zzih = zzaf$zzb.zzC();
        this.zzii = zzaf$zzb.zzC();
        this.zzij = zzaf$zzb.zzC();
        this.zzik = zzaf$zzg.zzK();
        this.zzil = "";
        this.zzim = "";
        this.zzin = "0";
        this.version = "";
        this.zzio = null;
        this.zzip = 0.0f;
        this.zziq = false;
        this.zzir = zzsa.zzbcs;
        this.zzis = 0;
        this.zzbca = null;
        this.zzbcl = -1;
        return this;
    }
    
    @Override
    public void zza(final zzrq zzrq) {
        final int n = 0;
        if (this.zzie != null && this.zzie.length > 0) {
            for (int i = 0; i < this.zzie.length; ++i) {
                final String s = this.zzie[i];
                if (s != null) {
                    zzrq.zzb(1, s);
                }
            }
        }
        if (this.zzif != null && this.zzif.length > 0) {
            for (int j = 0; j < this.zzif.length; ++j) {
                final zzag$zza zzag$zza = this.zzif[j];
                if (zzag$zza != null) {
                    zzrq.zza(2, zzag$zza);
                }
            }
        }
        if (this.zzig != null && this.zzig.length > 0) {
            for (int k = 0; k < this.zzig.length; ++k) {
                final zzaf$zze zzaf$zze = this.zzig[k];
                if (zzaf$zze != null) {
                    zzrq.zza(3, zzaf$zze);
                }
            }
        }
        if (this.zzih != null && this.zzih.length > 0) {
            for (int l = 0; l < this.zzih.length; ++l) {
                final zzaf$zzb zzaf$zzb = this.zzih[l];
                if (zzaf$zzb != null) {
                    zzrq.zza(4, zzaf$zzb);
                }
            }
        }
        if (this.zzii != null && this.zzii.length > 0) {
            for (int n2 = 0; n2 < this.zzii.length; ++n2) {
                final zzaf$zzb zzaf$zzb2 = this.zzii[n2];
                if (zzaf$zzb2 != null) {
                    zzrq.zza(5, zzaf$zzb2);
                }
            }
        }
        if (this.zzij != null && this.zzij.length > 0) {
            for (int n3 = 0; n3 < this.zzij.length; ++n3) {
                final zzaf$zzb zzaf$zzb3 = this.zzij[n3];
                if (zzaf$zzb3 != null) {
                    zzrq.zza(6, zzaf$zzb3);
                }
            }
        }
        if (this.zzik != null && this.zzik.length > 0) {
            for (int n4 = 0; n4 < this.zzik.length; ++n4) {
                final zzaf$zzg zzaf$zzg = this.zzik[n4];
                if (zzaf$zzg != null) {
                    zzrq.zza(7, zzaf$zzg);
                }
            }
        }
        if (!this.zzil.equals("")) {
            zzrq.zzb(9, this.zzil);
        }
        if (!this.zzim.equals("")) {
            zzrq.zzb(10, this.zzim);
        }
        if (!this.zzin.equals("0")) {
            zzrq.zzb(12, this.zzin);
        }
        if (!this.version.equals("")) {
            zzrq.zzb(13, this.version);
        }
        if (this.zzio != null) {
            zzrq.zza(14, this.zzio);
        }
        if (Float.floatToIntBits(this.zzip) != Float.floatToIntBits(0.0f)) {
            zzrq.zzb(15, this.zzip);
        }
        if (this.zzir != null && this.zzir.length > 0) {
            for (int n5 = 0; n5 < this.zzir.length; ++n5) {
                final String s2 = this.zzir[n5];
                if (s2 != null) {
                    zzrq.zzb(16, s2);
                }
            }
        }
        if (this.zzis != 0) {
            zzrq.zzz(17, this.zzis);
        }
        if (this.zziq) {
            zzrq.zzb(18, this.zziq);
        }
        if (this.zzid != null && this.zzid.length > 0) {
            for (int n6 = n; n6 < this.zzid.length; ++n6) {
                final String s3 = this.zzid[n6];
                if (s3 != null) {
                    zzrq.zzb(19, s3);
                }
            }
        }
        super.zza(zzrq);
    }
}
