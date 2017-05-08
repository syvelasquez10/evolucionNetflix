// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.client;

import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import java.util.Iterator;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeImpl1Client;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.CancelledKeyException;
import org.java_websocket.WrappedByteChannel;
import org.java_websocket.SocketChannelIOHelper;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.net.SocketAddress;
import java.io.IOException;
import org.java_websocket.WebSocket;
import java.net.Socket;
import java.nio.channels.spi.SelectorProvider;
import org.java_websocket.drafts.Draft_10;
import java.net.URI;
import java.net.InetSocketAddress;
import java.util.Map;
import org.java_websocket.drafts.Draft;
import org.java_websocket.WebSocketImpl;
import java.util.concurrent.CountDownLatch;
import java.nio.channels.SocketChannel;
import org.java_websocket.WebSocketAdapter;
import java.nio.channels.ByteChannel;

public class WebSocketClient$DefaultClientProxyChannel extends AbstractClientProxyChannel
{
    final /* synthetic */ WebSocketClient this$0;
    
    public WebSocketClient$DefaultClientProxyChannel(final WebSocketClient this$0, final ByteChannel byteChannel) {
        this.this$0 = this$0;
        super(byteChannel);
    }
    
    @Override
    public String buildHandShake() {
        final StringBuilder sb = new StringBuilder();
        final String host = this.this$0.uri.getHost();
        sb.append("CONNECT ");
        sb.append(host);
        sb.append(":");
        sb.append(this.this$0.getPort());
        sb.append(" HTTP/1.1\n");
        sb.append("Host: ");
        sb.append(host);
        sb.append("\n");
        return sb.toString();
    }
}
