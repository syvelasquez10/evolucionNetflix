// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;

public final class zzoo extends zzod<zzoo>
{
    private String mCategory;
    private String zzOj;
    private String zzaID;
    private long zzasE;
    
    public String getAction() {
        return this.zzOj;
    }
    
    public String getLabel() {
        return this.zzaID;
    }
    
    public long getValue() {
        return this.zzasE;
    }
    
    @Override
    public String toString() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("category", this.mCategory);
        hashMap.put("action", this.zzOj);
        hashMap.put("label", this.zzaID);
        hashMap.put("value", (String)this.zzasE);
        return zzod.zzA(hashMap);
    }
    
    public void zzM(final long zzasE) {
        this.zzasE = zzasE;
    }
    
    @Override
    public void zza(final zzoo zzoo) {
        if (!TextUtils.isEmpty((CharSequence)this.mCategory)) {
            zzoo.zzdR(this.mCategory);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzOj)) {
            zzoo.zzdS(this.zzOj);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaID)) {
            zzoo.zzdT(this.zzaID);
        }
        if (this.zzasE != 0L) {
            zzoo.zzM(this.zzasE);
        }
    }
    
    public void zzdR(final String mCategory) {
        this.mCategory = mCategory;
    }
    
    public void zzdS(final String zzOj) {
        this.zzOj = zzOj;
    }
    
    public void zzdT(final String zzaID) {
        this.zzaID = zzaID;
    }
    
    public String zzxQ() {
        return this.mCategory;
    }
}
