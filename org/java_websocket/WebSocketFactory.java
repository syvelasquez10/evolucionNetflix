// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket;

import java.net.Socket;
import org.java_websocket.drafts.Draft;

public interface WebSocketFactory
{
    WebSocket createWebSocket(final WebSocketAdapter p0, final Draft p1, final Socket p2);
}
