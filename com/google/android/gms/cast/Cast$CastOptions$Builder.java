// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.internal.n;

public final class Cast$CastOptions$Builder
{
    CastDevice EN;
    Cast$Listener EO;
    private int EP;
    
    private Cast$CastOptions$Builder(final CastDevice en, final Cast$Listener eo) {
        n.b(en, "CastDevice parameter cannot be null");
        n.b(eo, "CastListener parameter cannot be null");
        this.EN = en;
        this.EO = eo;
        this.EP = 0;
    }
    
    public Cast$CastOptions build() {
        return new Cast$CastOptions(this, null);
    }
    
    public Cast$CastOptions$Builder setVerboseLoggingEnabled(final boolean b) {
        if (b) {
            this.EP |= 0x1;
            return this;
        }
        this.EP &= 0xFFFFFFFE;
        return this;
    }
}
