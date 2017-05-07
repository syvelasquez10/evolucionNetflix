// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient$ServerAuthCodeCallbacks;
import com.google.android.gms.common.api.Api$ApiOptions$Optional;

public final class zzqx implements Api$ApiOptions$Optional
{
    public static final zzqx zzaUZ;
    private final boolean zzTi;
    private final boolean zzTk;
    private final String zzTl;
    private final boolean zzaVa;
    private final GoogleApiClient$ServerAuthCodeCallbacks zzaVb;
    private final boolean zzaVc;
    
    static {
        zzaUZ = new zzqx$zza().zzCi();
    }
    
    private zzqx(final boolean zzaVa, final boolean zzTi, final String zzTl, final GoogleApiClient$ServerAuthCodeCallbacks zzaVb, final boolean zzaVc, final boolean zzTk) {
        this.zzaVa = zzaVa;
        this.zzTi = zzTi;
        this.zzTl = zzTl;
        this.zzaVb = zzaVb;
        this.zzaVc = zzaVc;
        this.zzTk = zzTk;
    }
    
    public boolean zzCf() {
        return this.zzaVa;
    }
    
    public GoogleApiClient$ServerAuthCodeCallbacks zzCg() {
        return this.zzaVb;
    }
    
    public boolean zzCh() {
        return this.zzaVc;
    }
    
    public boolean zzlY() {
        return this.zzTi;
    }
    
    public boolean zzma() {
        return this.zzTk;
    }
    
    public String zzmb() {
        return this.zzTl;
    }
}
