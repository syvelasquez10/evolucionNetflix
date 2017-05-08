// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

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
}
