// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.net.SocketImpl;
import java.net.Socket;
import java.net.SocketImplFactory;

public final class y implements SocketImplFactory
{
    private static boolean a;
    private e b;
    private d c;
    
    static {
        y.a = false;
    }
    
    private y(final e b, final d c) {
        this.b = b;
        this.c = c;
    }
    
    public static boolean a(final e e, final d d) {
        if (y.a) {
            return y.a;
        }
        final y socketImplFactory = new y(e, d);
        try {
            socketImplFactory.createSocketImpl();
            Socket.setSocketImplFactory(socketImplFactory);
            return y.a = true;
        }
        catch (Throwable t) {
            return y.a;
        }
    }
    
    @Override
    public final SocketImpl createSocketImpl() {
        return new x(this.b, this.c);
    }
}
