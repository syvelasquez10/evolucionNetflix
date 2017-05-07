// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;

public final class zzor extends zzod<zzor>
{
    public String zzOj;
    public String zzaIN;
    public String zzaIO;
    
    public String getAction() {
        return this.zzOj;
    }
    
    public String getTarget() {
        return this.zzaIO;
    }
    
    @Override
    public String toString() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("network", this.zzaIN);
        hashMap.put("action", this.zzOj);
        hashMap.put("target", this.zzaIO);
        return zzod.zzA(hashMap);
    }
    
    @Override
    public void zza(final zzor zzor) {
        if (!TextUtils.isEmpty((CharSequence)this.zzaIN)) {
            zzor.zzdW(this.zzaIN);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzOj)) {
            zzor.zzdS(this.zzOj);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaIO)) {
            zzor.zzdX(this.zzaIO);
        }
    }
    
    public void zzdS(final String zzOj) {
        this.zzOj = zzOj;
    }
    
    public void zzdW(final String zzaIN) {
        this.zzaIN = zzaIN;
    }
    
    public void zzdX(final String zzaIO) {
        this.zzaIO = zzaIO;
    }
    
    public String zzya() {
        return this.zzaIN;
    }
}
