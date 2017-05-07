// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;

public final class zzok extends zzod<zzok>
{
    private String zzNT;
    private String zzNU;
    private String zzaIs;
    private String zzaIt;
    
    public void setAppId(final String zzaIs) {
        this.zzaIs = zzaIs;
    }
    
    public void setAppInstallerId(final String zzaIt) {
        this.zzaIt = zzaIt;
    }
    
    public void setAppName(final String zzNT) {
        this.zzNT = zzNT;
    }
    
    public void setAppVersion(final String zzNU) {
        this.zzNU = zzNU;
    }
    
    @Override
    public String toString() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("appName", this.zzNT);
        hashMap.put("appVersion", this.zzNU);
        hashMap.put("appId", this.zzaIs);
        hashMap.put("appInstallerId", this.zzaIt);
        return zzod.zzA(hashMap);
    }
    
    @Override
    public void zza(final zzok zzok) {
        if (!TextUtils.isEmpty((CharSequence)this.zzNT)) {
            zzok.setAppName(this.zzNT);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzNU)) {
            zzok.setAppVersion(this.zzNU);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaIs)) {
            zzok.setAppId(this.zzaIs);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaIt)) {
            zzok.setAppInstallerId(this.zzaIt);
        }
    }
    
    public String zzjZ() {
        return this.zzNT;
    }
    
    public String zzkb() {
        return this.zzNU;
    }
    
    public String zztW() {
        return this.zzaIs;
    }
    
    public String zzxA() {
        return this.zzaIt;
    }
}
