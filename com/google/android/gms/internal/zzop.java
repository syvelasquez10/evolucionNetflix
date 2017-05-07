// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;

public final class zzop extends zzod<zzop>
{
    public boolean zzaIE;
    public String zzaoB;
    
    public String getDescription() {
        return this.zzaoB;
    }
    
    public void setDescription(final String zzaoB) {
        this.zzaoB = zzaoB;
    }
    
    @Override
    public String toString() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("description", this.zzaoB);
        hashMap.put("fatal", (String)this.zzaIE);
        return zzod.zzA(hashMap);
    }
    
    @Override
    public void zza(final zzop zzop) {
        if (!TextUtils.isEmpty((CharSequence)this.zzaoB)) {
            zzop.setDescription(this.zzaoB);
        }
        if (this.zzaIE) {
            zzop.zzak(this.zzaIE);
        }
    }
    
    public void zzak(final boolean zzaIE) {
        this.zzaIE = zzaIE;
    }
    
    public boolean zzxR() {
        return this.zzaIE;
    }
}
