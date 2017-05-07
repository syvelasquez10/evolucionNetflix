// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;

public final class zzos extends zzod<zzos>
{
    public String mCategory;
    public String zzaID;
    public String zzaIP;
    public long zzaIQ;
    
    public String getLabel() {
        return this.zzaID;
    }
    
    public long getTimeInMillis() {
        return this.zzaIQ;
    }
    
    public void setTimeInMillis(final long zzaIQ) {
        this.zzaIQ = zzaIQ;
    }
    
    @Override
    public String toString() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("variableName", this.zzaIP);
        hashMap.put("timeInMillis", (String)this.zzaIQ);
        hashMap.put("category", this.mCategory);
        hashMap.put("label", this.zzaID);
        return zzod.zzA(hashMap);
    }
    
    @Override
    public void zza(final zzos zzos) {
        if (!TextUtils.isEmpty((CharSequence)this.zzaIP)) {
            zzos.zzdY(this.zzaIP);
        }
        if (this.zzaIQ != 0L) {
            zzos.setTimeInMillis(this.zzaIQ);
        }
        if (!TextUtils.isEmpty((CharSequence)this.mCategory)) {
            zzos.zzdR(this.mCategory);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaID)) {
            zzos.zzdT(this.zzaID);
        }
    }
    
    public void zzdR(final String mCategory) {
        this.mCategory = mCategory;
    }
    
    public void zzdT(final String zzaID) {
        this.zzaID = zzaID;
    }
    
    public void zzdY(final String zzaIP) {
        this.zzaIP = zzaIP;
    }
    
    public String zzxQ() {
        return this.mCategory;
    }
    
    public String zzyb() {
        return this.zzaIP;
    }
}
