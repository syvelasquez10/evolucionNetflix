// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.common.api.Api$ApiOptions$HasOptions;

public final class Cast$CastOptions implements Api$ApiOptions$HasOptions
{
    final CastDevice zzTg;
    final Cast$Listener zzTh;
    private final int zzTi;
    
    private Cast$CastOptions(final Cast$CastOptions$Builder cast$CastOptions$Builder) {
        this.zzTg = cast$CastOptions$Builder.zzTj;
        this.zzTh = cast$CastOptions$Builder.zzTk;
        this.zzTi = cast$CastOptions$Builder.zzTl;
    }
    
    @Deprecated
    public static Cast$CastOptions$Builder builder(final CastDevice castDevice, final Cast$Listener cast$Listener) {
        return new Cast$CastOptions$Builder(castDevice, cast$Listener);
    }
}
