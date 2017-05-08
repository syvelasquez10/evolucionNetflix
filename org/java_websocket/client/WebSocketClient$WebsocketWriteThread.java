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
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.net.SocketAddress;
import org.java_websocket.WebSocket;
import java.net.Socket;
import java.nio.channels.spi.SelectorProvider;
import org.java_websocket.drafts.Draft_10;
import java.nio.channels.ByteChannel;
import java.net.URI;
import java.net.InetSocketAddress;
import java.util.Map;
import org.java_websocket.drafts.Draft;
import org.java_websocket.WebSocketImpl;
import java.util.concurrent.CountDownLatch;
import java.nio.channels.SocketChannel;
import org.java_websocket.WebSocketAdapter;
import java.io.IOException;
import org.java_websocket.SocketChannelIOHelper;

class WebSocketClient$WebsocketWriteThread implements Runnable
{
    final /* synthetic */ WebSocketClient this$0;
    
    private WebSocketClient$WebsocketWriteThread(final WebSocketClient this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Thread.currentThread().setName("WebsocketWriteThread");
        try {
            while (!Thread.interrupted()) {
                SocketChannelIOHelper.writeBlocking(this.this$0.conn, this.this$0.wrappedchannel);
            }
            goto Label_0045;
        }
        catch (IOException ex) {
            this.this$0.conn.eot();
        }
        catch (InterruptedException ex2) {}
    }
}
