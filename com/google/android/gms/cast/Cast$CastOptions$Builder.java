// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.internal.zzu;

public final class Cast$CastOptions$Builder
{
    CastDevice zzQG;
    Cast$Listener zzQH;
    private int zzQI;
    
    public Cast$CastOptions$Builder(final CastDevice zzQG, final Cast$Listener zzQH) {
        zzu.zzb(zzQG, "CastDevice parameter cannot be null");
        zzu.zzb(zzQH, "CastListener parameter cannot be null");
        this.zzQG = zzQG;
        this.zzQH = zzQH;
        this.zzQI = 0;
    }
    
    public Cast$CastOptions build() {
        return new Cast$CastOptions(this, null);
    }
    
    public Cast$CastOptions$Builder setVerboseLoggingEnabled(final boolean b) {
        if (b) {
            this.zzQI |= 0x1;
            return this;
        }
        this.zzQI &= 0xFFFFFFFE;
        return this;
    }
}
