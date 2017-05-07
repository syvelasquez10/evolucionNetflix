// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzx;

public class zzqi
{
    private final String zzaOS;
    private final String zzaRq;
    private final Integer zzaTv;
    private final String zzaTw;
    private final boolean zzaTx;
    
    public zzqi(final String s, final Integer n, final String s2, final boolean b) {
        this(s, n, s2, b, "");
    }
    
    public zzqi(final String zzaOS, final Integer zzaTv, final String zzaTw, final boolean zzaTx, final String zzaRq) {
        zzx.zzv(zzaOS);
        zzx.zzv(zzaRq);
        this.zzaOS = zzaOS;
        this.zzaTv = zzaTv;
        this.zzaTw = zzaTw;
        this.zzaTx = zzaTx;
        this.zzaRq = zzaRq;
    }
    
    public String getContainerId() {
        return this.zzaOS;
    }
    
    public Integer zzBp() {
        return this.zzaTv;
    }
    
    public String zzBq() {
        return this.zzaTw;
    }
    
    public String zzBr() {
        if (this.zzaTw != null) {
            return this.zzaTw + "_" + this.zzaOS;
        }
        return this.zzaOS;
    }
    
    public boolean zzBs() {
        return this.zzaTx;
    }
    
    public String zzBt() {
        return this.zzaRq;
    }
}
