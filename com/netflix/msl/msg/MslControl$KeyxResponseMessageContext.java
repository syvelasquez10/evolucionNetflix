// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

class MslControl$KeyxResponseMessageContext extends MslControl$FilterMessageContext
{
    public MslControl$KeyxResponseMessageContext(final MessageContext messageContext) {
        super(messageContext);
    }
    
    @Override
    public boolean isEncrypted() {
        return false;
    }
    
    @Override
    public boolean isIntegrityProtected() {
        return false;
    }
    
    @Override
    public boolean isNonReplayable() {
        return false;
    }
    
    @Override
    public void write(final MessageOutputStream messageOutputStream) {
    }
}
