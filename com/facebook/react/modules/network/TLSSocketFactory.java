// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.network;

import java.net.InetAddress;
import javax.net.ssl.SSLSocket;
import java.net.Socket;
import java.security.SecureRandom;
import javax.net.ssl.TrustManager;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public class TLSSocketFactory extends SSLSocketFactory
{
    private SSLSocketFactory delegate;
    
    public TLSSocketFactory() {
        final SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(null, null, null);
        this.delegate = instance.getSocketFactory();
    }
    
    private Socket enableTLSOnSocket(final Socket socket) {
        if (socket != null && socket instanceof SSLSocket) {
            ((SSLSocket)socket).setEnabledProtocols(new String[] { "TLSv1.1", "TLSv1.2" });
        }
        return socket;
    }
    
    @Override
    public Socket createSocket(final String s, final int n) {
        return this.enableTLSOnSocket(this.delegate.createSocket(s, n));
    }
    
    @Override
    public Socket createSocket(final String s, final int n, final InetAddress inetAddress, final int n2) {
        return this.enableTLSOnSocket(this.delegate.createSocket(s, n, inetAddress, n2));
    }
    
    @Override
    public Socket createSocket(final InetAddress inetAddress, final int n) {
        return this.enableTLSOnSocket(this.delegate.createSocket(inetAddress, n));
    }
    
    @Override
    public Socket createSocket(final InetAddress inetAddress, final int n, final InetAddress inetAddress2, final int n2) {
        return this.enableTLSOnSocket(this.delegate.createSocket(inetAddress, n, inetAddress2, n2));
    }
    
    @Override
    public Socket createSocket(final Socket socket, final String s, final int n, final boolean b) {
        return this.enableTLSOnSocket(this.delegate.createSocket(socket, s, n, b));
    }
    
    @Override
    public String[] getDefaultCipherSuites() {
        return this.delegate.getDefaultCipherSuites();
    }
    
    @Override
    public String[] getSupportedCipherSuites() {
        return this.delegate.getSupportedCipherSuites();
    }
}
