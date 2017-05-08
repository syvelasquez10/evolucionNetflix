// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

class MslControl$SendReceiveResult extends MslControl$SendResult
{
    public final MessageInputStream response;
    
    public MslControl$SendReceiveResult(final MessageInputStream response, final MslControl$SendResult mslControl$SendResult) {
        super(mslControl$SendResult.request, mslControl$SendResult.handshake, null);
        this.response = response;
    }
}
