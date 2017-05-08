// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket;

import org.java_websocket.framing.Framedata;
import java.net.InetSocketAddress;

public interface WebSocket
{
    InetSocketAddress getLocalSocketAddress();
    
    void sendFrame(final Framedata p0);
}
