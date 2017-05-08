// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket;

import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.ByteChannel;
import java.nio.ByteBuffer;

public class SocketChannelIOHelper
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public static boolean read(final ByteBuffer byteBuffer, final WebSocketImpl webSocketImpl, final ByteChannel byteChannel) {
        byteBuffer.clear();
        final int read = byteChannel.read(byteBuffer);
        byteBuffer.flip();
        if (read == -1) {
            webSocketImpl.eot();
        }
        else if (read != 0) {
            return true;
        }
        return false;
    }
    
    public static boolean readMore(final ByteBuffer byteBuffer, final WebSocketImpl webSocketImpl, final WrappedByteChannel wrappedByteChannel) {
        byteBuffer.clear();
        final int more = wrappedByteChannel.readMore(byteBuffer);
        byteBuffer.flip();
        if (more == -1) {
            webSocketImpl.eot();
            return false;
        }
        return wrappedByteChannel.isNeedRead();
    }
    
    public static void writeBlocking(final WebSocketImpl webSocketImpl, final ByteChannel byteChannel) {
        assert !(!((AbstractSelectableChannel)byteChannel).isBlocking());
        if (SocketChannelIOHelper.$assertionsDisabled || !(byteChannel instanceof WrappedByteChannel) || ((WrappedByteChannel)byteChannel).isBlocking()) {
            final ByteBuffer byteBuffer = webSocketImpl.outQueue.take();
            while (byteBuffer.hasRemaining()) {
                byteChannel.write(byteBuffer);
            }
            return;
        }
        throw new AssertionError();
    }
}
