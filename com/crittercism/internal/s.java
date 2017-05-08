// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import org.apache.harmony.xnet.provider.jsse.SSLParametersImpl;
import java.net.InetAddress;
import org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl;

public final class s extends OpenSSLSocketImpl implements z
{
    private w a;
    
    protected s(final e e, final d d, final String s, final int n, final InetAddress inetAddress, final int n2, final SSLParametersImpl sslParametersImpl) {
        super(s, n, inetAddress, n2, sslParametersImpl);
        this.a = new w(k$a.b, e, d);
    }
    
    protected s(final e e, final d d, final String s, final int n, final SSLParametersImpl sslParametersImpl) {
        super(s, n, sslParametersImpl);
        this.a = new w(k$a.b, e, d);
    }
    
    protected s(final e e, final d d, final InetAddress inetAddress, final int n, final InetAddress inetAddress2, final int n2, final SSLParametersImpl sslParametersImpl) {
        super(inetAddress, n, inetAddress2, n2, sslParametersImpl);
        this.a = new w(k$a.b, e, d);
    }
    
    protected s(final e e, final d d, final InetAddress inetAddress, final int n, final SSLParametersImpl sslParametersImpl) {
        super(inetAddress, n, sslParametersImpl);
        this.a = new w(k$a.b, e, d);
    }
    
    protected s(final e e, final d d, final SSLParametersImpl sslParametersImpl) {
        super(sslParametersImpl);
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
}
