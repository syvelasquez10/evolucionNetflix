// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.io.IOException;
import javax.net.ssl.SSLSocket;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import com.android.org.conscrypt.SSLParametersImpl;
import com.android.org.conscrypt.OpenSSLSocketImpl;

public final class t extends OpenSSLSocketImpl implements z
{
    private w a;
    
    protected t(final e e, final d d, final SSLParametersImpl sslParametersImpl) {
        super(sslParametersImpl);
        this.a = new w(k$a.b, e, d);
    }
    
    protected t(final e e, final d d, final String s, final int n, final SSLParametersImpl sslParametersImpl) {
        super(s, n, sslParametersImpl);
        this.a = new w(k$a.b, e, d);
    }
    
    protected t(final e e, final d d, final String s, final int n, final InetAddress inetAddress, final int n2, final SSLParametersImpl sslParametersImpl) {
        super(s, n, inetAddress, n2, sslParametersImpl);
        this.a = new w(k$a.b, e, d);
    }
    
    protected t(final e e, final d d, final InetAddress inetAddress, final int n, final SSLParametersImpl sslParametersImpl) {
        super(inetAddress, n, sslParametersImpl);
        this.a = new w(k$a.b, e, d);
    }
    
    protected t(final e e, final d d, final InetAddress inetAddress, final int n, final InetAddress inetAddress2, final int n2, final SSLParametersImpl sslParametersImpl) {
        super(inetAddress, n, inetAddress2, n2, sslParametersImpl);
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
    
    public final int getSoTimeout() {
        synchronized (this) {
            return super.getSoTimeout();
        }
    }
    
    public final void setSoTimeout(final int soTimeout) {
        synchronized (this) {
            super.setSoTimeout(soTimeout);
        }
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
