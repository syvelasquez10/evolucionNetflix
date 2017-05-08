// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.io.IOException;
import javax.net.ssl.SSLSocket;
import java.io.OutputStream;
import java.io.InputStream;
import org.apache.harmony.xnet.provider.jsse.SSLParametersImpl;
import java.net.Socket;
import org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImplWrapper;

public final class u extends OpenSSLSocketImplWrapper implements z
{
    private w a;
    
    protected u(final e e, final d d, final Socket socket, final String s, final int n, final boolean b, final SSLParametersImpl sslParametersImpl) {
        super(socket, s, n, b, sslParametersImpl);
        this.a = new w(k$a.b, e, d);
    }
    
    public final c a() {
        return this.a.a(this.getInetAddress());
    }
    
    public final void a(final c c) {
        this.a.a(c);
    }
    
    public final c b() {
        return this.a.b();
    }
    
    public final void close() {
        super.close();
        this.a.a();
    }
    
    public final InputStream getInputStream() {
        return this.a.a(this, super.getInputStream());
    }
    
    public final OutputStream getOutputStream() {
        return this.a.a(this, super.getOutputStream());
    }
    
    public final void startHandshake() {
        try {
            super.startHandshake();
        }
        catch (IOException ex) {
            this.a.a(ex, (SSLSocket)this);
            throw ex;
        }
    }
}
