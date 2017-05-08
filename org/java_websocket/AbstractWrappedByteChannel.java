// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.ByteChannel;

public class AbstractWrappedByteChannel implements WrappedByteChannel
{
    private final ByteChannel channel;
    
    public AbstractWrappedByteChannel(final ByteChannel channel) {
        this.channel = channel;
    }
    
    @Override
    public void close() {
        this.channel.close();
    }
    
    @Override
    public boolean isBlocking() {
        if (this.channel instanceof SocketChannel) {
            return ((SocketChannel)this.channel).isBlocking();
        }
        return this.channel instanceof WrappedByteChannel && ((WrappedByteChannel)this.channel).isBlocking();
    }
    
    @Override
    public boolean isNeedRead() {
        return this.channel instanceof WrappedByteChannel && ((WrappedByteChannel)this.channel).isNeedRead();
    }
    
    @Override
    public boolean isOpen() {
        return this.channel.isOpen();
    }
    
    @Override
    public int read(final ByteBuffer byteBuffer) {
        return this.channel.read(byteBuffer);
    }
    
    @Override
    public int readMore(final ByteBuffer byteBuffer) {
        if (this.channel instanceof WrappedByteChannel) {
            return ((WrappedByteChannel)this.channel).readMore(byteBuffer);
        }
        return 0;
    }
    
    @Override
    public int write(final ByteBuffer byteBuffer) {
        return this.channel.write(byteBuffer);
    }
}
