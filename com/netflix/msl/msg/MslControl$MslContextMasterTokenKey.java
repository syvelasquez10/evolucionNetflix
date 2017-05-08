// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.util.MslContext;

class MslControl$MslContextMasterTokenKey
{
    private final MslContext ctx;
    private final MasterToken masterToken;
    
    public MslControl$MslContextMasterTokenKey(final MslContext ctx, final MasterToken masterToken) {
        this.ctx = ctx;
        this.masterToken = masterToken;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof MslControl$MslContextMasterTokenKey)) {
                return false;
            }
            final MslControl$MslContextMasterTokenKey mslControl$MslContextMasterTokenKey = (MslControl$MslContextMasterTokenKey)o;
            if (!this.ctx.equals(mslControl$MslContextMasterTokenKey.ctx) || !this.masterToken.equals(mslControl$MslContextMasterTokenKey.masterToken)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return this.ctx.hashCode() ^ this.masterToken.hashCode();
    }
}
