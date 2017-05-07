// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Iterator;
import java.util.ArrayList;

@zzgk
public class zzbh
{
    private final Object zzpc;
    private final int zzrC;
    private final int zzrD;
    private final int zzrE;
    private final zzbm zzrF;
    private ArrayList<String> zzrG;
    private int zzrH;
    private int zzrI;
    private int zzrJ;
    private int zzrK;
    private String zzrL;
    
    public zzbh(final int zzrC, final int zzrD, final int zzrE, final int n) {
        this.zzpc = new Object();
        this.zzrG = new ArrayList<String>();
        this.zzrH = 0;
        this.zzrI = 0;
        this.zzrJ = 0;
        this.zzrL = "";
        this.zzrC = zzrC;
        this.zzrD = zzrD;
        this.zzrE = zzrE;
        this.zzrF = new zzbm(n);
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
        if (s == null || s.length() < this.zzrE) {
            return;
        }
        synchronized (this.zzpc) {
            this.zzrG.add(s);
            this.zzrH += s.length();
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
        return "ActivityContent fetchId: " + this.zzrI + " score:" + this.zzrK + " total_length:" + this.zzrH + "\n text: " + this.zza(this.zzrG, 200) + "\n signture: " + this.zzrL;
    }
    
    int zza(final int n, final int n2) {
        return this.zzrC * n + this.zzrD * n2;
    }
    
    public boolean zzcl() {
        while (true) {
            synchronized (this.zzpc) {
                if (this.zzrJ == 0) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public String zzcm() {
        return this.zzrL;
    }
    
    public void zzco() {
        synchronized (this.zzpc) {
            --this.zzrJ;
        }
    }
    
    public void zzcp() {
        synchronized (this.zzpc) {
            ++this.zzrJ;
        }
    }
    
    public void zzcq() {
        synchronized (this.zzpc) {
            final int zza = this.zza(this.zzrH, this.zzrI);
            if (zza > this.zzrK) {
                this.zzrK = zza;
                this.zzrL = this.zzrF.zza(this.zzrG);
            }
        }
    }
    
    int zzcr() {
        return this.zzrH;
    }
    
    public void zzg(final int zzrI) {
        this.zzrI = zzrI;
    }
    
    public void zzv(final String s) {
        this.zzx(s);
        synchronized (this.zzpc) {
            if (this.zzrJ < 0) {
                zzb.zzaC("ActivityContent: negative number of WebViews.");
            }
            this.zzcq();
        }
    }
    
    public void zzw(final String s) {
        this.zzx(s);
    }
}
