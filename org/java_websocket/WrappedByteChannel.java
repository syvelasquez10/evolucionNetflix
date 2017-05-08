// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket;

import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

public interface WrappedByteChannel extends ByteChannel
{
    boolean isBlocking();
    
    boolean isNeedRead();
    
    int readMore(final ByteBuffer p0);
}
