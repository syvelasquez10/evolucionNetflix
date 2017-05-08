// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

public abstract class PublicMessageContext implements MessageContext
{
    @Override
    public boolean isEncrypted() {
        return false;
    }
    
    @Override
    public boolean isIntegrityProtected() {
        return true;
    }
    
    @Override
    public boolean isNonReplayable() {
        return false;
    }
}
