// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.net.InetAddress;
import java.util.LinkedList;
import java.util.Queue;

public final class w
{
    k$a a;
    private e b;
    private d c;
    private final Queue d;
    
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
    
    public final void a(final c c) {
        synchronized (this.d) {
            this.d.add(c);
        }
    }
    
    public final c b() {
        synchronized (this.d) {
            return this.d.poll();
        }
    }
}
