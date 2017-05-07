// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.internal.zzx;

public final class Cast$CastOptions$Builder
{
    CastDevice zzUY;
    Cast$Listener zzUZ;
    private int zzVa;
    
    public Cast$CastOptions$Builder(final CastDevice zzUY, final Cast$Listener zzUZ) {
        zzx.zzb(zzUY, "CastDevice parameter cannot be null");
        zzx.zzb(zzUZ, "CastListener parameter cannot be null");
        this.zzUY = zzUY;
        this.zzUZ = zzUZ;
        this.zzVa = 0;
    }
    
    public Cast$CastOptions build() {
        return new Cast$CastOptions(this, null);
    }
    
    public Cast$CastOptions$Builder setVerboseLoggingEnabled(final boolean b) {
        if (b) {
            this.zzVa |= 0x1;
            return this;
        }
        this.zzVa &= 0xFFFFFFFE;
        return this;
    }
}
