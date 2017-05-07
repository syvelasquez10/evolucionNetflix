// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.net.InetAddress;
import javax.net.ssl.SSLSocket;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;

public final class ab extends SSLSocketFactory
{
    private SSLSocketFactory a;
    private e b;
    private d c;
    
    public ab(final SSLSocketFactory a, final e b, final d c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    private Socket a(final Socket socket) {
        Socket socket2 = socket;
        if (socket == null) {
            return socket2;
        }
        socket2 = socket;
        try {
            if (socket instanceof SSLSocket) {
                socket2 = new aa((SSLSocket)socket, this.b, this.c);
            }
            return socket2;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
            return socket;
        }
    }
    
    public final SSLSocketFactory a() {
        return this.a;
    }
    
    @Override
    public final Socket createSocket() {
        return this.a(this.a.createSocket());
    }
    
    @Override
    public final Socket createSocket(final String s, final int n) {
        return this.a(this.a.createSocket(s, n));
    }
    
    @Override
    public final Socket createSocket(final String s, final int n, final InetAddress inetAddress, final int n2) {
        return this.a(this.a.createSocket(s, n, inetAddress, n2));
    }
    
    @Override
    public final Socket createSocket(final InetAddress inetAddress, final int n) {
        return this.a(this.a.createSocket(inetAddress, n));
    }
    
    @Override
    public final Socket createSocket(final InetAddress inetAddress, final int n, final InetAddress inetAddress2, final int n2) {
        return this.a(this.a.createSocket(inetAddress, n, inetAddress2, n2));
    }
    
    @Override
    public final Socket createSocket(final Socket socket, final String s, final int n, final boolean b) {
        return this.a(this.a.createSocket(socket, s, n, b));
    }
    
    @Override
    public final String[] getDefaultCipherSuites() {
        return this.a.getDefaultCipherSuites();
    }
    
    @Override
    public final String[] getSupportedCipherSuites() {
        return this.a.getSupportedCipherSuites();
    }
}
