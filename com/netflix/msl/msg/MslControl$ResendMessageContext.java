// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import java.util.Iterator;
import java.util.List;

class MslControl$ResendMessageContext extends MslControl$FilterMessageContext
{
    private final List<PayloadChunk> payloads;
    
    public MslControl$ResendMessageContext(final List<PayloadChunk> payloads, final MessageContext messageContext) {
        super(messageContext);
        this.payloads = payloads;
    }
    
    @Override
    public void write(final MessageOutputStream messageOutputStream) {
        if (this.payloads == null || this.payloads.isEmpty()) {
            this.appCtx.write(messageOutputStream);
        }
        else {
            for (final PayloadChunk payloadChunk : this.payloads) {
                messageOutputStream.setCompressionAlgorithm(payloadChunk.getCompressionAlgo());
                messageOutputStream.write(payloadChunk.getData());
                if (payloadChunk.isEndOfMessage()) {
                    messageOutputStream.close();
                }
                else {
                    messageOutputStream.flush();
                }
            }
        }
    }
}
