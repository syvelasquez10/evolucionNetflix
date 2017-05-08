// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.client;

import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import org.java_websocket.WebSocketListener;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.WebSocket;
import java.net.Socket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.WebSocketAdapter;

public class DefaultWebSocketClientFactory implements WebSocketClient$WebSocketClientFactory
{
    private final WebSocketClient webSocketClient;
    
    public DefaultWebSocketClientFactory(final WebSocketClient webSocketClient) {
        this.webSocketClient = webSocketClient;
    }
    
    @Override
    public WebSocket createWebSocket(final WebSocketAdapter webSocketAdapter, final Draft draft, final Socket socket) {
        return new WebSocketImpl(this.webSocketClient, draft);
    }
    
    @Override
    public ByteChannel wrapChannel(final SocketChannel socketChannel, final SelectionKey selectionKey, final String s, final int n) {
        if (selectionKey == null) {}
        return socketChannel;
    }
}
