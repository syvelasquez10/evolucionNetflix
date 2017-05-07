// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.common.api.Api$ApiOptions$HasOptions;

public final class Cast$CastOptions implements Api$ApiOptions$HasOptions
{
    final CastDevice zzUV;
    final Cast$Listener zzUW;
    private final int zzUX;
    
    private Cast$CastOptions(final Cast$CastOptions$Builder cast$CastOptions$Builder) {
        this.zzUV = cast$CastOptions$Builder.zzUY;
        this.zzUW = cast$CastOptions$Builder.zzUZ;
        this.zzUX = cast$CastOptions$Builder.zzVa;
    }
    
    @Deprecated
    public static Cast$CastOptions$Builder builder(final CastDevice castDevice, final Cast$Listener cast$Listener) {
        return new Cast$CastOptions$Builder(castDevice, cast$Listener);
    }
}
