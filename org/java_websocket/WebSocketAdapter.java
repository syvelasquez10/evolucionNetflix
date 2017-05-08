// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket;

import org.java_websocket.framing.Framedata$Opcode;
import org.java_websocket.framing.FramedataImpl1;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.HandshakeImpl1Server;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ClientHandshake;

public abstract class WebSocketAdapter implements WebSocketListener
{
    @Override
    public String getFlashPolicy(final WebSocket webSocket) {
        return "<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"" + webSocket.getLocalSocketAddress().getPort() + "\" /></cross-domain-policy>\u0000";
    }
    
    @Override
    public void onWebsocketHandshakeReceivedAsClient(final WebSocket webSocket, final ClientHandshake clientHandshake, final ServerHandshake serverHandshake) {
    }
    
    @Override
    public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(final WebSocket webSocket, final Draft draft, final ClientHandshake clientHandshake) {
        return new HandshakeImpl1Server();
    }
    
    @Override
    public void onWebsocketHandshakeSentAsClient(final WebSocket webSocket, final ClientHandshake clientHandshake) {
    }
    
    @Override
    public void onWebsocketMessageFragment(final WebSocket webSocket, final Framedata framedata) {
    }
    
    @Override
    public void onWebsocketPing(final WebSocket webSocket, final Framedata framedata) {
        final FramedataImpl1 framedataImpl1 = new FramedataImpl1(framedata);
        framedataImpl1.setOptcode(Framedata$Opcode.PONG);
        webSocket.sendFrame(framedataImpl1);
    }
    
    @Override
    public void onWebsocketPong(final WebSocket webSocket, final Framedata framedata) {
    }
}
