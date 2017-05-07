// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Iterator;
import java.util.ArrayList;

@zzgr
public class zzbh
{
    private final Object zzpd;
    private final int zzrN;
    private final int zzrO;
    private final int zzrP;
    private final zzbm zzrQ;
    private ArrayList<String> zzrR;
    private int zzrS;
    private int zzrT;
    private int zzrU;
    private int zzrV;
    private String zzrW;
    
    public zzbh(final int zzrN, final int zzrO, final int zzrP, final int n) {
        this.zzpd = new Object();
        this.zzrR = new ArrayList<String>();
        this.zzrS = 0;
        this.zzrT = 0;
        this.zzrU = 0;
        this.zzrW = "";
        this.zzrN = zzrN;
        this.zzrO = zzrO;
        this.zzrP = zzrP;
        this.zzrQ = new zzbm(n);
    }
    
    private String zza(final ArrayList<String> list, final int n) {
        String string;
        if (list.isEmpty()) {
            string = "";
        }
        else {
            final StringBuffer sb = new StringBuffer();
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next());
                sb.append(' ');
                if (sb.length() > n) {
                    break;
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            final String s = string = sb.toString();
            if (s.length() >= n) {
                return s.substring(0, n);
            }
        }
        return string;
    }
    
    private void zzx(final String s) {
        if (s == null || s.length() < this.zzrP) {
            return;
        }
        synchronized (this.zzpd) {
            this.zzrR.add(s);
            this.zzrS += s.length();
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof zzbh) {
            if (o == this) {
                return true;
            }
            final zzbh zzbh = (zzbh)o;
            if (zzbh.zzcm() != null && zzbh.zzcm().equals(this.zzcm())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.zzcm().hashCode();
    }
    
    @Override
    public String toString() {
        return "ActivityContent fetchId: " + this.zzrT + " score:" + this.zzrV + " total_length:" + this.zzrS + "\n text: " + this.zza(this.zzrR, 200) + "\n signture: " + this.zzrW;
    }
    
    int zza(final int n, final int n2) {
        return this.zzrN * n + this.zzrO * n2;
    }
    
    public boolean zzcl() {
        while (true) {
            synchronized (this.zzpd) {
                if (this.zzrU == 0) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public String zzcm() {
        return this.zzrW;
    }
    
    public void zzco() {
        synchronized (this.zzpd) {
            --this.zzrU;
        }
    }
    
    public void zzcp() {
        synchronized (this.zzpd) {
            ++this.zzrU;
        }
    }
    
    public void zzcq() {
        synchronized (this.zzpd) {
            final int zza = this.zza(this.zzrS, this.zzrT);
            if (zza > this.zzrV) {
                this.zzrV = zza;
                this.zzrW = this.zzrQ.zza(this.zzrR);
            }
        }
    }
    
    int zzcr() {
        return this.zzrS;
    }
    
    public void zzg(final int zzrT) {
        this.zzrT = zzrT;
    }
    
    public void zzv(final String s) {
        this.zzx(s);
        synchronized (this.zzpd) {
            if (this.zzrU < 0) {
                zzb.zzaF("ActivityContent: negative number of WebViews.");
            }
            this.zzcq();
        }
    }
    
    public void zzw(final String s) {
        this.zzx(s);
    }
}
