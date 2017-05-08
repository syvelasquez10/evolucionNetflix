// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

public abstract class NonReplayableMessageContext implements MessageContext
{
    @Override
    public boolean isEncrypted() {
        return true;
    }
    
    @Override
    public boolean isIntegrityProtected() {
        return true;
    }
    
    @Override
    public boolean isNonReplayable() {
        return true;
    }
}
