// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;

public final class zzol extends zzod<zzol>
{
    private String mName;
    private String zzaDI;
    private String zzaIu;
    private String zzaIv;
    private String zzaIw;
    private String zzaIx;
    private String zzaIy;
    private String zzaIz;
    private String zzvx;
    private String zzwj;
    
    public String getContent() {
        return this.zzvx;
    }
    
    public String getId() {
        return this.zzwj;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public String getSource() {
        return this.zzaDI;
    }
    
    public void setName(final String mName) {
        this.mName = mName;
    }
    
    @Override
    public String toString() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name", this.mName);
        hashMap.put("source", this.zzaDI);
        hashMap.put("medium", this.zzaIu);
        hashMap.put("keyword", this.zzaIv);
        hashMap.put("content", this.zzvx);
        hashMap.put("id", this.zzwj);
        hashMap.put("adNetworkId", this.zzaIw);
        hashMap.put("gclid", this.zzaIx);
        hashMap.put("dclid", this.zzaIy);
        hashMap.put("aclid", this.zzaIz);
        return zzod.zzA(hashMap);
    }
    
    @Override
    public void zza(final zzol zzol) {
        if (!TextUtils.isEmpty((CharSequence)this.mName)) {
            zzol.setName(this.mName);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaDI)) {
            zzol.zzdI(this.zzaDI);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaIu)) {
            zzol.zzdJ(this.zzaIu);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaIv)) {
            zzol.zzdK(this.zzaIv);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzvx)) {
            zzol.zzdL(this.zzvx);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzwj)) {
            zzol.zzdM(this.zzwj);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaIw)) {
            zzol.zzdN(this.zzaIw);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaIx)) {
            zzol.zzdO(this.zzaIx);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaIy)) {
            zzol.zzdP(this.zzaIy);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaIz)) {
            zzol.zzdQ(this.zzaIz);
        }
    }
    
    public void zzdI(final String zzaDI) {
        this.zzaDI = zzaDI;
    }
    
    public void zzdJ(final String zzaIu) {
        this.zzaIu = zzaIu;
    }
    
    public void zzdK(final String zzaIv) {
        this.zzaIv = zzaIv;
    }
    
    public void zzdL(final String zzvx) {
        this.zzvx = zzvx;
    }
    
    public void zzdM(final String zzwj) {
        this.zzwj = zzwj;
    }
    
    public void zzdN(final String zzaIw) {
        this.zzaIw = zzaIw;
    }
    
    public void zzdO(final String zzaIx) {
        this.zzaIx = zzaIx;
    }
    
    public void zzdP(final String zzaIy) {
        this.zzaIy = zzaIy;
    }
    
    public void zzdQ(final String zzaIz) {
        this.zzaIz = zzaIz;
    }
    
    public String zzxB() {
        return this.zzaIu;
    }
    
    public String zzxC() {
        return this.zzaIv;
    }
    
    public String zzxD() {
        return this.zzaIw;
    }
    
    public String zzxE() {
        return this.zzaIx;
    }
    
    public String zzxF() {
        return this.zzaIy;
    }
    
    public String zzxG() {
        return this.zzaIz;
    }
}
