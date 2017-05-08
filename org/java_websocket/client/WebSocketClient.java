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
import java.nio.channels.ByteChannel;
import java.net.URI;
import java.net.InetSocketAddress;
import java.util.Map;
import org.java_websocket.drafts.Draft;
import org.java_websocket.WebSocketImpl;
import java.util.concurrent.CountDownLatch;
import java.nio.channels.SocketChannel;
import org.java_websocket.WebSocketAdapter;

public abstract class WebSocketClient extends WebSocketAdapter implements Runnable
{
    private SocketChannel channel;
    private CountDownLatch closeLatch;
    private WebSocketImpl conn;
    private CountDownLatch connectLatch;
    private Draft draft;
    private Map<String, String> headers;
    private InetSocketAddress proxyAddress;
    private Thread readthread;
    private int timeout;
    protected URI uri;
    private ByteChannel wrappedchannel;
    private Thread writethread;
    private WebSocketClient$WebSocketClientFactory wsfactory;
    
    public WebSocketClient(final URI uri) {
        this(uri, new Draft_10());
    }
    
    public WebSocketClient(final URI uri, final Draft draft) {
        this(uri, draft, null, 0);
    }
    
    public WebSocketClient(final URI uri, final Draft draft, final Map<String, String> headers, final int timeout) {
        this.uri = null;
        this.conn = null;
        this.channel = null;
        this.wrappedchannel = null;
        this.connectLatch = new CountDownLatch(1);
        this.closeLatch = new CountDownLatch(1);
        this.timeout = 0;
        this.wsfactory = new DefaultWebSocketClientFactory(this);
        this.proxyAddress = null;
        if (uri == null) {
            throw new IllegalArgumentException();
        }
        if (draft == null) {
            throw new IllegalArgumentException("null as draft is permitted for `WebSocketServer` only!");
        }
        this.uri = uri;
        this.draft = draft;
        this.headers = headers;
        this.timeout = timeout;
        while (true) {
            try {
                (this.channel = SelectorProvider.provider().openSocketChannel()).configureBlocking(true);
                if (this.channel == null) {
                    (this.conn = (WebSocketImpl)this.wsfactory.createWebSocket(this, draft, null)).close(-1, "Failed to create or configure SocketChannel.");
                    return;
                }
            }
            catch (IOException ex) {
                this.channel = null;
                this.onWebsocketError(null, ex);
                continue;
            }
            break;
        }
        this.conn = (WebSocketImpl)this.wsfactory.createWebSocket(this, draft, this.channel.socket());
    }
    
    private int getPort() {
        int port;
        if ((port = this.uri.getPort()) == -1) {
            final String scheme = this.uri.getScheme();
            if (scheme.equals("wss")) {
                port = 443;
            }
            else {
                if (scheme.equals("ws")) {
                    return 80;
                }
                throw new RuntimeException("unkonow scheme" + scheme);
            }
        }
        return port;
    }
    
    private final void interruptableRun() {
        if (this.channel != null) {
            try {
                if (this.proxyAddress == null) {
                    goto Label_0219;
                }
                final String hostName = this.proxyAddress.getHostName();
                final int port = this.proxyAddress.getPort();
                this.channel.connect(new InetSocketAddress(hostName, port));
                final WebSocketImpl conn = this.conn;
                final ByteChannel proxyChannel = this.createProxyChannel(this.wsfactory.wrapChannel(this.channel, null, hostName, port));
                this.wrappedchannel = proxyChannel;
                conn.channel = proxyChannel;
                this.timeout = 0;
                this.sendHandshake();
                (this.readthread = new Thread(new WebSocketClient$WebsocketWriteThread(this, null))).start();
                final ByteBuffer allocate = ByteBuffer.allocate(WebSocketImpl.RCVBUF);
                try {
                    WrappedByteChannel wrappedByteChannel = null;
                    Label_0187: {
                        while (this.channel.isOpen()) {
                            if (!SocketChannelIOHelper.read(allocate, this.conn, this.wrappedchannel)) {
                                goto Label_0266;
                            }
                            this.conn.decode(allocate);
                            if (!(this.wrappedchannel instanceof WrappedByteChannel)) {
                                continue;
                            }
                            wrappedByteChannel = (WrappedByteChannel)this.wrappedchannel;
                            if (wrappedByteChannel.isNeedRead()) {
                                break Label_0187;
                            }
                        }
                        return;
                    }
                    while (SocketChannelIOHelper.readMore(allocate, this.conn, wrappedByteChannel)) {
                        this.conn.decode(allocate);
                    }
                }
                catch (CancelledKeyException ex2) {
                    this.conn.eot();
                }
                catch (IOException ex3) {
                    this.conn.eot();
                }
                catch (RuntimeException ex) {
                    this.onError(ex);
                    this.conn.closeConnection(1006, ex.getMessage());
                }
            }
            catch (ClosedByInterruptException ex4) {}
            catch (Exception ex5) {}
        }
    }
    
    private void sendHandshake() {
        final String path = this.uri.getPath();
        final String query = this.uri.getQuery();
        String s = null;
        Label_0034: {
            if (path != null) {
                s = path;
                if (path.length() != 0) {
                    break Label_0034;
                }
            }
            s = "/";
        }
        String string = s;
        if (query != null) {
            string = s + "?" + query;
        }
        final int port = this.getPort();
        final StringBuilder append = new StringBuilder().append(this.uri.getHost());
        String string2;
        if (port != 80) {
            string2 = ":" + port;
        }
        else {
            string2 = "";
        }
        final String string3 = append.append(string2).toString();
        final HandshakeImpl1Client handshakeImpl1Client = new HandshakeImpl1Client();
        handshakeImpl1Client.setResourceDescriptor(string);
        handshakeImpl1Client.put("Host", string3);
        if (this.headers != null) {
            for (final Map.Entry<String, String> entry : this.headers.entrySet()) {
                handshakeImpl1Client.put(entry.getKey(), entry.getValue());
            }
        }
        this.conn.startHandshake(handshakeImpl1Client);
    }
    
    public void close() {
        if (this.writethread != null) {
            this.conn.close(1000);
        }
    }
    
    public void connect() {
        if (this.writethread != null) {
            throw new IllegalStateException("WebSocketClient objects are not reuseable");
        }
        (this.writethread = new Thread(this)).start();
    }
    
    public boolean connectBlocking() {
        this.connect();
        this.connectLatch.await();
        return this.conn.isOpen();
    }
    
    public ByteChannel createProxyChannel(final ByteChannel byteChannel) {
        ByteChannel byteChannel2 = byteChannel;
        if (this.proxyAddress != null) {
            byteChannel2 = new WebSocketClient$DefaultClientProxyChannel(this, byteChannel);
        }
        return byteChannel2;
    }
    
    @Override
    public InetSocketAddress getLocalSocketAddress(final WebSocket webSocket) {
        if (this.channel != null) {
            return (InetSocketAddress)this.channel.socket().getLocalSocketAddress();
        }
        return null;
    }
    
    public abstract void onClose(final int p0, final String p1, final boolean p2);
    
    public void onCloseInitiated(final int n, final String s) {
    }
    
    public void onClosing(final int n, final String s, final boolean b) {
    }
    
    public abstract void onError(final Exception p0);
    
    public abstract void onMessage(final String p0);
    
    public void onMessage(final ByteBuffer byteBuffer) {
    }
    
    public abstract void onOpen(final ServerHandshake p0);
    
    @Override
    public final void onWebsocketClose(final WebSocket webSocket, final int n, final String s, final boolean b) {
        this.connectLatch.countDown();
        this.closeLatch.countDown();
        if (this.readthread != null) {
            this.readthread.interrupt();
        }
        this.onClose(n, s, b);
    }
    
    @Override
    public void onWebsocketCloseInitiated(final WebSocket webSocket, final int n, final String s) {
        this.onCloseInitiated(n, s);
    }
    
    @Override
    public void onWebsocketClosing(final WebSocket webSocket, final int n, final String s, final boolean b) {
        this.onClosing(n, s, b);
    }
    
    @Override
    public final void onWebsocketError(final WebSocket webSocket, final Exception ex) {
        this.onError(ex);
    }
    
    @Override
    public final void onWebsocketMessage(final WebSocket webSocket, final String s) {
        this.onMessage(s);
    }
    
    @Override
    public final void onWebsocketMessage(final WebSocket webSocket, final ByteBuffer byteBuffer) {
        this.onMessage(byteBuffer);
    }
    
    @Override
    public final void onWebsocketOpen(final WebSocket webSocket, final Handshakedata handshakedata) {
        this.connectLatch.countDown();
        this.onOpen((ServerHandshake)handshakedata);
    }
    
    @Override
    public final void onWriteDemand(final WebSocket webSocket) {
    }
    
    @Override
    public void run() {
        if (this.writethread == null) {
            this.writethread = Thread.currentThread();
        }
        this.interruptableRun();
        assert !this.channel.isOpen();
    }
    
    public void send(final String s) {
        this.conn.send(s);
    }
}
