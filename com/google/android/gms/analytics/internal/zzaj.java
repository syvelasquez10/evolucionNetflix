// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzlm;

class zzaj
{
    private long zzOC;
    private final zzlm zzpO;
    
    public zzaj(final zzlm zzpO) {
        zzx.zzv(zzpO);
        this.zzpO = zzpO;
    }
    
    public zzaj(final zzlm zzpO, final long zzOC) {
        zzx.zzv(zzpO);
        this.zzpO = zzpO;
        this.zzOC = zzOC;
    }
    
    public void clear() {
        this.zzOC = 0L;
    }
    
    public void start() {
        this.zzOC = this.zzpO.elapsedRealtime();
    }
    
    public boolean zzv(final long n) {
        return this.zzOC == 0L || this.zzpO.elapsedRealtime() - this.zzOC > n;
    }
}
