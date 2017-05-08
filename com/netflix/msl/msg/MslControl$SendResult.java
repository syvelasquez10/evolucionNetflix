// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

class MslControl$SendResult
{
    public final boolean handshake;
    public final MessageOutputStream request;
    
    private MslControl$SendResult(final MessageOutputStream request, final boolean handshake) {
        this.request = request;
        this.handshake = handshake;
    }
}
