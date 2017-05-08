// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.client;

import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import org.java_websocket.WebSocketFactory;

public interface WebSocketClient$WebSocketClientFactory extends WebSocketFactory
{
    ByteChannel wrapChannel(final SocketChannel p0, final SelectionKey p1, final String p2, final int p3);
}
