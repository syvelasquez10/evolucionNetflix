// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.internal.zzu;
import com.google.android.gms.common.api.Api$ApiOptions$HasOptions;

public final class Cast$CastOptions implements Api$ApiOptions$HasOptions
{
    final CastDevice zzQD;
    final Cast$Listener zzQE;
    private final int zzQF;
    
    private Cast$CastOptions(final Cast$CastOptions$Builder cast$CastOptions$Builder) {
        this.zzQD = cast$CastOptions$Builder.zzQG;
        this.zzQE = cast$CastOptions$Builder.zzQH;
        this.zzQF = cast$CastOptions$Builder.zzQI;
    }
    
    @Deprecated
    public static Cast$CastOptions$Builder builder(final CastDevice castDevice, final Cast$Listener cast$Listener) {
        return new Cast$CastOptions$Builder(castDevice, cast$Listener);
    }
}
