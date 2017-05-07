// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;

public final class zzom extends zzod<zzom>
{
    private String zzUc;
    public int zzaIA;
    public int zzaIB;
    public int zzaIC;
    public int zzzQ;
    public int zzzR;
    
    public String getLanguage() {
        return this.zzUc;
    }
    
    public void setLanguage(final String zzUc) {
        this.zzUc = zzUc;
    }
    
    @Override
    public String toString() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("language", this.zzUc);
        hashMap.put("screenColors", (String)this.zzaIA);
        hashMap.put("screenWidth", (String)this.zzzQ);
        hashMap.put("screenHeight", (String)this.zzzR);
        hashMap.put("viewportWidth", (String)this.zzaIB);
        hashMap.put("viewportHeight", (String)this.zzaIC);
        return zzod.zzA(hashMap);
    }
    
    @Override
    public void zza(final zzom zzom) {
        if (this.zzaIA != 0) {
            zzom.zzhN(this.zzaIA);
        }
        if (this.zzzQ != 0) {
            zzom.zzhO(this.zzzQ);
        }
        if (this.zzzR != 0) {
            zzom.zzhP(this.zzzR);
        }
        if (this.zzaIB != 0) {
            zzom.zzhQ(this.zzaIB);
        }
        if (this.zzaIC != 0) {
            zzom.zzhR(this.zzaIC);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzUc)) {
            zzom.setLanguage(this.zzUc);
        }
    }
    
    public void zzhN(final int zzaIA) {
        this.zzaIA = zzaIA;
    }
    
    public void zzhO(final int zzzQ) {
        this.zzzQ = zzzQ;
    }
    
    public void zzhP(final int zzzR) {
        this.zzzR = zzzR;
    }
    
    public void zzhQ(final int zzaIB) {
        this.zzaIB = zzaIB;
    }
    
    public void zzhR(final int zzaIC) {
        this.zzaIC = zzaIC;
    }
    
    public int zzxH() {
        return this.zzaIA;
    }
    
    public int zzxI() {
        return this.zzzQ;
    }
    
    public int zzxJ() {
        return this.zzzR;
    }
    
    public int zzxK() {
        return this.zzaIB;
    }
    
    public int zzxL() {
        return this.zzaIC;
    }
}
