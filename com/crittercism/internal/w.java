// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.Queue;

public final class w
{
    k$a a;
    private e b;
    private d c;
    private final Queue d;
    private l e;
    private m f;
    
    public w(final k$a a, final e b, final d c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = new LinkedList();
    }
    
    public final c a(final InetAddress inetAddress) {
        return this.a(inetAddress, null, this.a);
    }
    
    final c a(final InetAddress a, final Integer n, final k$a d) {
        final c c = new c();
        if (a != null) {
            c.i = null;
            c.h.a = a;
        }
        if (n != null && n > 0) {
            final int intValue = n;
            final k h = c.h;
            if (intValue > 0) {
                h.e = intValue;
            }
        }
        if (d != null) {
            c.h.d = d;
        }
        if (this.c != null) {
            c.j = this.c.a();
        }
        if (ba.b()) {
            c.a(ba.a());
        }
        return c;
    }
    
    public final InputStream a(final z z, final InputStream inputStream) {
        if (inputStream != null) {
            while (true) {
                while (true) {
                    Label_0067: {
                        try {
                            if (this.f != null) {
                                if (this.f.b != inputStream) {
                                    break Label_0067;
                                }
                                final int n = 1;
                                if (n != 0) {
                                    return this.f;
                                }
                            }
                            return this.f = new m(z, inputStream, this.b);
                        }
                        catch (ThreadDeath threadDeath) {
                            throw threadDeath;
                        }
                        catch (Throwable t) {
                            dw.b(t);
                        }
                        break;
                    }
                    final int n = 0;
                    continue;
                }
            }
        }
        return inputStream;
    }
    
    public final OutputStream a(final z z, final OutputStream outputStream) {
        if (outputStream != null) {
            while (true) {
                while (true) {
                    Label_0063: {
                        try {
                            if (this.e != null) {
                                if (this.e.a != outputStream) {
                                    break Label_0063;
                                }
                                final int n = 1;
                                if (n != 0) {
                                    return this.e;
                                }
                            }
                            return this.e = new l(z, outputStream);
                        }
                        catch (ThreadDeath threadDeath) {
                            throw threadDeath;
                        }
                        catch (Throwable t) {
                            dw.b(t);
                        }
                        break;
                    }
                    final int n = 0;
                    continue;
                }
            }
        }
        return outputStream;
    }
    
    public final void a() {
        try {
            if (this.f != null) {
                final m f = this.f;
                if (f.a != null) {
                    final cl g = f.a.g;
                    final ck a = ck.a;
                    boolean b;
                    if (g.a == cm.d - 1 && g.b == a.C) {
                        b = true;
                    }
                    else {
                        b = false;
                    }
                    if (b && f.c != null) {
                        f.c.f();
                    }
                }
            }
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    public final void a(final c c) {
        synchronized (this.d) {
            this.d.add(c);
        }
    }
    
    final void a(final IOException ex, final InetAddress inetAddress, final int n, final String s, final k$a k$a) {
        final c a = this.a(inetAddress, n, k$a);
        if (s != null) {
            a.b(s);
        }
        a.b();
        a.c();
        a.h.f = true;
        a.a(ex);
        this.b.a(a);
    }
    
    public final void a(final IOException ex, final SSLSocket sslSocket) {
        try {
            this.a(ex, sslSocket.getInetAddress(), sslSocket.getPort(), null, this.a);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    public final c b() {
        synchronized (this.d) {
            return this.d.poll();
        }
    }
}
