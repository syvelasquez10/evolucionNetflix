// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.api.Api$ApiOptions$HasOptions;

public final class Cast$CastOptions implements Api$ApiOptions$HasOptions
{
    final CastDevice EK;
    final Cast$Listener EL;
    private final int EM;
    
    private Cast$CastOptions(final Cast$CastOptions$Builder cast$CastOptions$Builder) {
        this.EK = cast$CastOptions$Builder.EN;
        this.EL = cast$CastOptions$Builder.EO;
        this.EM = cast$CastOptions$Builder.EP;
    }
    
    public static Cast$CastOptions$Builder builder(final CastDevice castDevice, final Cast$Listener cast$Listener) {
        return new Cast$CastOptions$Builder(castDevice, cast$Listener, null);
    }
}
