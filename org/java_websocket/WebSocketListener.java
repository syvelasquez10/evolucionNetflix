// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket;

import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.framing.Framedata;
import java.nio.ByteBuffer;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ClientHandshake;
import java.net.InetSocketAddress;

public interface WebSocketListener
{
    String getFlashPolicy(final WebSocket p0);
    
    InetSocketAddress getLocalSocketAddress(final WebSocket p0);
    
    void onWebsocketClose(final WebSocket p0, final int p1, final String p2, final boolean p3);
    
    void onWebsocketCloseInitiated(final WebSocket p0, final int p1, final String p2);
    
    void onWebsocketClosing(final WebSocket p0, final int p1, final String p2, final boolean p3);
    
    void onWebsocketError(final WebSocket p0, final Exception p1);
    
    void onWebsocketHandshakeReceivedAsClient(final WebSocket p0, final ClientHandshake p1, final ServerHandshake p2);
    
    ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(final WebSocket p0, final Draft p1, final ClientHandshake p2);
    
    void onWebsocketHandshakeSentAsClient(final WebSocket p0, final ClientHandshake p1);
    
    void onWebsocketMessage(final WebSocket p0, final String p1);
    
    void onWebsocketMessage(final WebSocket p0, final ByteBuffer p1);
    
    void onWebsocketMessageFragment(final WebSocket p0, final Framedata p1);
    
    void onWebsocketOpen(final WebSocket p0, final Handshakedata p1);
    
    void onWebsocketPing(final WebSocket p0, final Framedata p1);
    
    void onWebsocketPong(final WebSocket p0, final Framedata p1);
    
    void onWriteDemand(final WebSocket p0);
}
