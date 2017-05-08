// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.io.OutputStream;

public final class l extends OutputStream implements ah
{
    OutputStream a;
    private z b;
    private c c;
    private aa d;
    
    public l(final z b, final OutputStream a) {
        if (b == null) {
            throw new NullPointerException("socket was null");
        }
        if (a == null) {
            throw new NullPointerException("output stream was null");
        }
        this.b = b;
        this.a = a;
        this.d = this.b();
        if (this.d == null) {
            throw new NullPointerException("parser was null");
        }
    }
    
    private void a(final byte[] array, final int n, final int n2) {
        try {
            this.d.a(array, n, n2);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
            this.d = ao.d;
        }
    }
    
    private c d() {
        if (this.c == null) {
            this.c = this.b.a();
        }
        return this.c;
    }
    
    @Override
    public final aa a() {
        return this.d;
    }
    
    @Override
    public final void a(final int n) {
    }
    
    @Override
    public final void a(final aa d) {
        this.d = d;
    }
    
    @Override
    public final void a(final String s) {
        final c d = this.d();
        if (d != null) {
            d.b(s);
        }
    }
    
    @Override
    public final void a(final String f, final String c) {
        final c d = this.d();
        d.b();
        d.f = f;
        d.i = null;
        final k h = d.h;
        if (c != null) {
            h.c = c;
        }
        this.b.a(d);
    }
    
    @Override
    public final aa b() {
        return new aj(this);
    }
    
    @Override
    public final void b(final int n) {
        final c c = this.c;
        this.c = null;
        if (c != null) {
            c.b(n);
        }
    }
    
    @Override
    public final String c() {
        final c d = this.d();
        String f = null;
        if (d != null) {
            f = d.f;
        }
        return f;
    }
    
    @Override
    public final void close() {
        this.a.close();
    }
    
    @Override
    public final void flush() {
        this.a.flush();
    }
    
    @Override
    public final void write(final int n) {
        this.a.write(n);
        try {
            this.d.a(n);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
            this.d = ao.d;
        }
    }
    
    @Override
    public final void write(final byte[] array) {
        this.a.write(array);
        if (array != null) {
            this.a(array, 0, array.length);
        }
    }
    
    @Override
    public final void write(final byte[] array, final int n, final int n2) {
        this.a.write(array, n, n2);
        if (array != null) {
            this.a(array, n, n2);
        }
    }
}
