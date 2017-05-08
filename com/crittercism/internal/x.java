// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.io.IOException;
import java.net.InetAddress;
import java.net.PlainSocketImpl;

public final class x extends PlainSocketImpl implements z
{
    private w a;
    
    public x(final e e, final d d) {
        this.a = new w(k$a.a, e, d);
    }
    
    @Override
    public final c a() {
        final InetAddress inetAddress = this.getInetAddress();
        final int port = this.getPort();
        final w a = this.a;
        return a.a(inetAddress, port, a.a);
    }
    
    @Override
    public final void a(final c c) {
        this.a.a(c);
    }
    
    @Override
    public final c b() {
        return this.a.b();
    }
    
    public final void close() {
        super.close();
        this.a.a();
    }
    
    public final void connect(final String s, final int n) {
        try {
            super.connect(s, n);
        }
        catch (IOException ex) {
            final InetAddress inetAddress = this.getInetAddress();
            final w a = this.a;
            Label_0035: {
                if (s == null) {
                    break Label_0035;
                }
                try {
                    a.a(ex, inetAddress, n, s, null);
                    throw ex;
                }
                catch (ThreadDeath threadDeath) {
                    throw threadDeath;
                }
                catch (Throwable t) {
                    dw.b(t);
                }
            }
        }
    }
    
    public final void connect(final InetAddress inetAddress, final int n) {
        try {
            super.connect(inetAddress, n);
        }
        catch (IOException ex) {
            final w a = this.a;
            Label_0028: {
                if (inetAddress == null) {
                    break Label_0028;
                }
                try {
                    a.a(ex, inetAddress, n, null, null);
                    throw ex;
                }
                catch (ThreadDeath threadDeath) {
                    throw threadDeath;
                }
                catch (Throwable t) {
                    dw.b(t);
                }
            }
        }
    }
    
    public final void connect(final SocketAddress socketAddress, int port) {
        try {
            super.connect(socketAddress, port);
        }
        catch (IOException ex) {
            final w a = this.a;
            Label_0057: {
                if (socketAddress == null) {
                    break Label_0057;
                }
                try {
                    if (socketAddress instanceof InetSocketAddress) {
                        final InetSocketAddress inetSocketAddress = (InetSocketAddress)socketAddress;
                        final InetAddress address = inetSocketAddress.getAddress();
                        port = inetSocketAddress.getPort();
                        if (address != null) {
                            a.a(ex, address, port, null, null);
                        }
                    }
                    throw ex;
                }
                catch (ThreadDeath threadDeath) {
                    throw threadDeath;
                }
                catch (Throwable t) {
                    dw.b(t);
                }
            }
        }
    }
    
    public final InputStream getInputStream() {
        return this.a.a(this, super.getInputStream());
    }
    
    @Override
    public final Object getOption(final int n) {
        return super.getOption(n);
    }
    
    public final OutputStream getOutputStream() {
        return this.a.a(this, super.getOutputStream());
    }
    
    @Override
    public final void setOption(final int n, final Object o) {
        super.setOption(n, o);
    }
}
