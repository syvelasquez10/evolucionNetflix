// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import com.android.org.conscrypt.SSLParametersImpl;
import java.net.Socket;
import com.android.org.conscrypt.OpenSSLSocketImplWrapper;

public final class v extends OpenSSLSocketImplWrapper implements z
{
    private w a;
    
    protected v(final e e, final d d, final Socket socket, final String s, final int n, final boolean b, final SSLParametersImpl sslParametersImpl) {
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
}
