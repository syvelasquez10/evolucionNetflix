// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.io.OutputStream;

public final class w extends OutputStream implements al
{
    private ae a;
    private OutputStream b;
    private c c;
    private af d;
    
    public w(final ae a, final OutputStream b) {
        if (a == null) {
            throw new NullPointerException("socket was null");
        }
        if (b == null) {
            throw new NullPointerException("output stream was null");
        }
        this.a = a;
        this.b = b;
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
            dy.a(t);
            this.d = as.d;
        }
    }
    
    private c d() {
        if (this.c == null) {
            this.c = this.a.a();
        }
        final c c = this.c;
        return this.c;
    }
    
    @Override
    public final af a() {
        return this.d;
    }
    
    @Override
    public final void a(final int n) {
    }
    
    @Override
    public final void a(final af d) {
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
        this.a.a(d);
    }
    
    public final boolean a(final OutputStream outputStream) {
        return this.b == outputStream;
    }
    
    @Override
    public final af b() {
        return new an(this);
    }
    
    @Override
    public final void b(final int n) {
        final c c = this.c;
        this.c = null;
        if (c != null) {
            c.d(n);
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
        this.b.close();
    }
    
    @Override
    public final void flush() {
        this.b.flush();
    }
    
    @Override
    public final void write(final int n) {
        this.b.write(n);
        try {
            this.d.a(n);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
            this.d = as.d;
        }
    }
    
    @Override
    public final void write(final byte[] array) {
        this.b.write(array);
        if (array != null) {
            this.a(array, 0, array.length);
        }
    }
    
    @Override
    public final void write(final byte[] array, final int n, final int n2) {
        this.b.write(array, n, n2);
        if (array != null) {
            this.a(array, n, n2);
        }
    }
}
