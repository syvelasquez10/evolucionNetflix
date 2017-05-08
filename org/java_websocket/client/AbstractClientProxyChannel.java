// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.client;

import java.io.UnsupportedEncodingException;
import java.nio.channels.ByteChannel;
import java.nio.ByteBuffer;
import org.java_websocket.AbstractWrappedByteChannel;

public abstract class AbstractClientProxyChannel extends AbstractWrappedByteChannel
{
    protected final ByteBuffer proxyHandshake;
    
    public AbstractClientProxyChannel(final ByteChannel byteChannel) {
        super(byteChannel);
        try {
            this.proxyHandshake = ByteBuffer.wrap(this.buildHandShake().getBytes("ASCII"));
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public abstract String buildHandShake();
    
    @Override
    public int write(final ByteBuffer byteBuffer) {
        if (!this.proxyHandshake.hasRemaining()) {
            return super.write(byteBuffer);
        }
        return super.write(this.proxyHandshake);
    }
}
