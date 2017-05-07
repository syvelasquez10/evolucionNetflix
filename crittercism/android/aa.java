// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.io.IOException;
import javax.net.ssl.SSLSession;
import java.io.OutputStream;
import java.io.InputStream;
import java.nio.channels.SocketChannel;
import java.net.SocketAddress;
import javax.net.ssl.HandshakeCompletedListener;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.Queue;
import javax.net.ssl.SSLSocket;

public final class aa extends SSLSocket implements ae
{
    private SSLSocket a;
    private e b;
    private d c;
    private final Queue d;
    private w e;
    private x f;
    
    public aa(final SSLSocket a, final e b, final d c) {
        this.d = new LinkedList();
        if (a == null) {
            throw new NullPointerException("delegate was null");
        }
        if (b == null) {
            throw new NullPointerException("dispatch was null");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    private c a(final boolean b) {
        final c c = new c();
        final InetAddress inetAddress = this.a.getInetAddress();
        if (inetAddress != null) {
            c.a(inetAddress);
        }
        if (b) {
            c.a(this.getPort());
        }
        c.a(k$a.b);
        if (this.c != null) {
            c.j = this.c.a();
        }
        if (bc.b()) {
            c.a(bc.a());
        }
        return c;
    }
    
    @Override
    public final c a() {
        return this.a(false);
    }
    
    @Override
    public final void a(final c c) {
        if (c == null) {
            return;
        }
        synchronized (this.d) {
            this.d.add(c);
        }
    }
    
    @Override
    public final void addHandshakeCompletedListener(final HandshakeCompletedListener handshakeCompletedListener) {
        this.a.addHandshakeCompletedListener(handshakeCompletedListener);
    }
    
    @Override
    public final c b() {
        synchronized (this.d) {
            return this.d.poll();
        }
    }
    
    @Override
    public final void bind(final SocketAddress socketAddress) {
        this.a.bind(socketAddress);
    }
    
    @Override
    public final void close() {
        this.a.close();
        try {
            if (this.f != null) {
                this.f.d();
            }
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
        }
    }
    
    @Override
    public final void connect(final SocketAddress socketAddress) {
        this.a.connect(socketAddress);
    }
    
    @Override
    public final void connect(final SocketAddress socketAddress, final int n) {
        this.a.connect(socketAddress, n);
    }
    
    @Override
    public final boolean equals(final Object o) {
        return this.a.equals(o);
    }
    
    @Override
    public final SocketChannel getChannel() {
        return this.a.getChannel();
    }
    
    @Override
    public final boolean getEnableSessionCreation() {
        return this.a.getEnableSessionCreation();
    }
    
    @Override
    public final String[] getEnabledCipherSuites() {
        return this.a.getEnabledCipherSuites();
    }
    
    @Override
    public final String[] getEnabledProtocols() {
        return this.a.getEnabledProtocols();
    }
    
    @Override
    public final InetAddress getInetAddress() {
        return this.a.getInetAddress();
    }
    
    @Override
    public final InputStream getInputStream() {
        final InputStream inputStream = this.a.getInputStream();
        if (inputStream != null) {
            try {
                if (this.f != null && this.f.a(inputStream)) {
                    return this.f;
                }
                return this.f = new x(this, inputStream, this.b);
            }
            catch (ThreadDeath threadDeath) {
                throw threadDeath;
            }
            catch (Throwable t) {
                dy.a(t);
            }
        }
        return inputStream;
    }
    
    @Override
    public final boolean getKeepAlive() {
        return this.a.getKeepAlive();
    }
    
    @Override
    public final InetAddress getLocalAddress() {
        return this.a.getLocalAddress();
    }
    
    @Override
    public final int getLocalPort() {
        return this.a.getLocalPort();
    }
    
    @Override
    public final SocketAddress getLocalSocketAddress() {
        return this.a.getLocalSocketAddress();
    }
    
    @Override
    public final boolean getNeedClientAuth() {
        return this.a.getNeedClientAuth();
    }
    
    @Override
    public final boolean getOOBInline() {
        return this.a.getOOBInline();
    }
    
    @Override
    public final OutputStream getOutputStream() {
        final OutputStream outputStream = this.a.getOutputStream();
        if (outputStream != null) {
            try {
                if (this.e != null && this.e.a(outputStream)) {
                    return this.e;
                }
                final w e = this.e;
                return this.e = new w(this, outputStream);
            }
            catch (ThreadDeath threadDeath) {
                throw threadDeath;
            }
            catch (Throwable t) {
                dy.a(t);
            }
        }
        return outputStream;
    }
    
    @Override
    public final int getPort() {
        return this.a.getPort();
    }
    
    @Override
    public final int getReceiveBufferSize() {
        return this.a.getReceiveBufferSize();
    }
    
    @Override
    public final SocketAddress getRemoteSocketAddress() {
        return this.a.getRemoteSocketAddress();
    }
    
    @Override
    public final boolean getReuseAddress() {
        return this.a.getReuseAddress();
    }
    
    @Override
    public final int getSendBufferSize() {
        return this.a.getSendBufferSize();
    }
    
    @Override
    public final SSLSession getSession() {
        return this.a.getSession();
    }
    
    @Override
    public final int getSoLinger() {
        return this.a.getSoLinger();
    }
    
    @Override
    public final int getSoTimeout() {
        return this.a.getSoTimeout();
    }
    
    @Override
    public final String[] getSupportedCipherSuites() {
        return this.a.getSupportedCipherSuites();
    }
    
    @Override
    public final String[] getSupportedProtocols() {
        return this.a.getSupportedProtocols();
    }
    
    @Override
    public final boolean getTcpNoDelay() {
        return this.a.getTcpNoDelay();
    }
    
    @Override
    public final int getTrafficClass() {
        return this.a.getTrafficClass();
    }
    
    @Override
    public final boolean getUseClientMode() {
        return this.a.getUseClientMode();
    }
    
    @Override
    public final boolean getWantClientAuth() {
        return this.a.getWantClientAuth();
    }
    
    @Override
    public final int hashCode() {
        return this.a.hashCode();
    }
    
    @Override
    public final boolean isBound() {
        return this.a.isBound();
    }
    
    @Override
    public final boolean isClosed() {
        return this.a.isClosed();
    }
    
    @Override
    public final boolean isConnected() {
        return this.a.isConnected();
    }
    
    @Override
    public final boolean isInputShutdown() {
        return this.a.isInputShutdown();
    }
    
    @Override
    public final boolean isOutputShutdown() {
        return this.a.isOutputShutdown();
    }
    
    @Override
    public final void removeHandshakeCompletedListener(final HandshakeCompletedListener handshakeCompletedListener) {
        this.a.removeHandshakeCompletedListener(handshakeCompletedListener);
    }
    
    @Override
    public final void sendUrgentData(final int n) {
        this.a.sendUrgentData(n);
    }
    
    @Override
    public final void setEnableSessionCreation(final boolean enableSessionCreation) {
        this.a.setEnableSessionCreation(enableSessionCreation);
    }
    
    @Override
    public final void setEnabledCipherSuites(final String[] enabledCipherSuites) {
        this.a.setEnabledCipherSuites(enabledCipherSuites);
    }
    
    @Override
    public final void setEnabledProtocols(final String[] enabledProtocols) {
        this.a.setEnabledProtocols(enabledProtocols);
    }
    
    @Override
    public final void setKeepAlive(final boolean keepAlive) {
        this.a.setKeepAlive(keepAlive);
    }
    
    @Override
    public final void setNeedClientAuth(final boolean needClientAuth) {
        this.a.setNeedClientAuth(needClientAuth);
    }
    
    @Override
    public final void setOOBInline(final boolean oobInline) {
        this.a.setOOBInline(oobInline);
    }
    
    @Override
    public final void setPerformancePreferences(final int n, final int n2, final int n3) {
        this.a.setPerformancePreferences(n, n2, n3);
    }
    
    @Override
    public final void setReceiveBufferSize(final int receiveBufferSize) {
        this.a.setReceiveBufferSize(receiveBufferSize);
    }
    
    @Override
    public final void setReuseAddress(final boolean reuseAddress) {
        this.a.setReuseAddress(reuseAddress);
    }
    
    @Override
    public final void setSendBufferSize(final int sendBufferSize) {
        this.a.setSendBufferSize(sendBufferSize);
    }
    
    @Override
    public final void setSoLinger(final boolean b, final int n) {
        this.a.setSoLinger(b, n);
    }
    
    @Override
    public final void setSoTimeout(final int soTimeout) {
        this.a.setSoTimeout(soTimeout);
    }
    
    @Override
    public final void setTcpNoDelay(final boolean tcpNoDelay) {
        this.a.setTcpNoDelay(tcpNoDelay);
    }
    
    @Override
    public final void setTrafficClass(final int trafficClass) {
        this.a.setTrafficClass(trafficClass);
    }
    
    @Override
    public final void setUseClientMode(final boolean useClientMode) {
        this.a.setUseClientMode(useClientMode);
    }
    
    @Override
    public final void setWantClientAuth(final boolean wantClientAuth) {
        this.a.setWantClientAuth(wantClientAuth);
    }
    
    @Override
    public final void shutdownInput() {
        this.a.shutdownInput();
    }
    
    @Override
    public final void shutdownOutput() {
        this.a.shutdownOutput();
    }
    
    @Override
    public final void startHandshake() {
        try {
            this.a.startHandshake();
        }
        catch (IOException ex) {
            try {
                final c a = this.a(true);
                a.b();
                a.c();
                a.f();
                a.a(ex);
                this.b.a(a, c$a.j);
                throw ex;
            }
            catch (ThreadDeath threadDeath) {
                throw threadDeath;
            }
            catch (Throwable t) {
                dy.a(t);
            }
        }
    }
    
    @Override
    public final String toString() {
        return this.a.toString();
    }
}
