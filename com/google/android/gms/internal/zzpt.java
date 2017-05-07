// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient$ServerAuthCodeCallbacks;
import com.google.android.gms.common.api.Api$ApiOptions$Optional;

public final class zzpt implements Api$ApiOptions$Optional
{
    public static final zzpt zzaJP;
    private final boolean zzaJQ;
    private final boolean zzaJR;
    private final String zzaJS;
    private final GoogleApiClient$ServerAuthCodeCallbacks zzaJT;
    
    static {
        zzaJP = new zzpt$zza().zzya();
    }
    
    private zzpt(final boolean zzaJQ, final boolean zzaJR, final String zzaJS, final GoogleApiClient$ServerAuthCodeCallbacks zzaJT) {
        this.zzaJQ = zzaJQ;
        this.zzaJR = zzaJR;
        this.zzaJS = zzaJS;
        this.zzaJT = zzaJT;
    }
    
    public boolean zzxX() {
        return this.zzaJQ;
    }
    
    public boolean zzxY() {
        return this.zzaJR;
    }
    
    public GoogleApiClient$ServerAuthCodeCallbacks zzxZ() {
        return this.zzaJT;
    }
    
    public String zzxr() {
        return this.zzaJS;
    }
}
