// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.internal.zzx;

public final class Cast$CastOptions$Builder
{
    CastDevice zzTj;
    Cast$Listener zzTk;
    private int zzTl;
    
    public Cast$CastOptions$Builder(final CastDevice zzTj, final Cast$Listener zzTk) {
        zzx.zzb(zzTj, "CastDevice parameter cannot be null");
        zzx.zzb(zzTk, "CastListener parameter cannot be null");
        this.zzTj = zzTj;
        this.zzTk = zzTk;
        this.zzTl = 0;
    }
    
    public Cast$CastOptions build() {
        return new Cast$CastOptions(this, null);
    }
    
    public Cast$CastOptions$Builder setVerboseLoggingEnabled(final boolean b) {
        if (b) {
            this.zzTl |= 0x1;
            return this;
        }
        this.zzTl &= 0xFFFFFFFE;
        return this;
    }
}
