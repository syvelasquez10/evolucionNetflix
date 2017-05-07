// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;
import com.google.android.gms.common.internal.zzx;

public final class zzjb extends zzod<zzjb>
{
    private String zzGh;
    private String zzLc;
    private String zzLd;
    private String zzLe;
    private boolean zzLf;
    private String zzLg;
    private boolean zzLh;
    private double zzLi;
    
    public String getClientId() {
        return this.zzLd;
    }
    
    public String getUserId() {
        return this.zzGh;
    }
    
    public void setClientId(final String zzLd) {
        this.zzLd = zzLd;
    }
    
    public void setSampleRate(final double zzLi) {
        zzx.zzb(zzLi >= 0.0 && zzLi <= 100.0, "Sample rate must be between 0% and 100%");
        this.zzLi = zzLi;
    }
    
    public void setUserId(final String zzGh) {
        this.zzGh = zzGh;
    }
    
    @Override
    public String toString() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("hitType", this.zzLc);
        hashMap.put("clientId", this.zzLd);
        hashMap.put("userId", this.zzGh);
        hashMap.put("androidAdId", this.zzLe);
        hashMap.put("AdTargetingEnabled", (String)this.zzLf);
        hashMap.put("sessionControl", this.zzLg);
        hashMap.put("nonInteraction", (String)this.zzLh);
        hashMap.put("sampleRate", (String)this.zzLi);
        return zzod.zzA(hashMap);
    }
    
    public void zzG(final boolean zzLf) {
        this.zzLf = zzLf;
    }
    
    public void zzH(final boolean zzLh) {
        this.zzLh = zzLh;
    }
    
    @Override
    public void zza(final zzjb zzjb) {
        if (!TextUtils.isEmpty((CharSequence)this.zzLc)) {
            zzjb.zzaS(this.zzLc);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzLd)) {
            zzjb.setClientId(this.zzLd);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzGh)) {
            zzjb.setUserId(this.zzGh);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzLe)) {
            zzjb.zzaT(this.zzLe);
        }
        if (this.zzLf) {
            zzjb.zzG(true);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzLg)) {
            zzjb.zzaU(this.zzLg);
        }
        if (this.zzLh) {
            zzjb.zzH(this.zzLh);
        }
        if (this.zzLi != 0.0) {
            zzjb.setSampleRate(this.zzLi);
        }
    }
    
    public void zzaS(final String zzLc) {
        this.zzLc = zzLc;
    }
    
    public void zzaT(final String zzLe) {
        this.zzLe = zzLe;
    }
    
    public void zzaU(final String zzLg) {
        this.zzLg = zzLg;
    }
    
    public String zzhK() {
        return this.zzLc;
    }
    
    public String zzhL() {
        return this.zzLe;
    }
    
    public boolean zzhM() {
        return this.zzLf;
    }
    
    public String zzhN() {
        return this.zzLg;
    }
    
    public boolean zzhO() {
        return this.zzLh;
    }
    
    public double zzhP() {
        return this.zzLi;
    }
}
