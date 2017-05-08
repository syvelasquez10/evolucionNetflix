// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.io.IOException;
import java.io.InputStream;

public final class m extends InputStream implements ah
{
    c a;
    InputStream b;
    aa c;
    private z d;
    private e e;
    
    private void a(final Exception ex) {
        try {
            final c d = this.d();
            d.a(ex);
            this.e.a(d, c$a.h);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
        catch (IllegalStateException ex2) {}
    }
    
    private void a(final byte[] array, final int n, final int n2) {
        try {
            this.c.a(array, n, n2);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (IllegalStateException ex) {
            this.c = ao.d;
        }
        catch (Throwable t) {
            this.c = ao.d;
            dw.b(t);
        }
    }
    
    private c d() {
        if (this.a == null) {
            this.a = this.d.b();
        }
        if (this.a == null) {
            throw new IllegalStateException("No statistics were queued up.");
        }
        return this.a;
    }
    
    @Override
    public final aa a() {
        return this.c;
    }
    
    @Override
    public final void a(final int e) {
        final c d = this.d();
        d.c();
        d.e = e;
    }
    
    @Override
    public final void a(final aa c) {
        this.c = c;
    }
    
    @Override
    public final void a(final String s) {
    }
    
    @Override
    public final void a(final String s, final String s2) {
    }
    
    @Override
    public final int available() {
        return this.b.available();
    }
    
    @Override
    public final aa b() {
        return new al(this);
    }
    
    @Override
    public final void b(final int n) {
        c a = null;
        final c c = null;
        if (this.a != null) {
            final int e = this.a.e;
            a = c;
            if (e >= 100) {
                a = c;
                if (e < 200) {
                    a = new c(this.a.a());
                    a.c(this.a.a);
                    a.b(this.a.d);
                    a.f = this.a.f;
                }
            }
            this.a.a((long)n);
            this.e.a(this.a, c$a.g);
        }
        this.a = a;
    }
    
    @Override
    public final String c() {
        return this.d().f;
    }
    
    @Override
    public final void close() {
        while (true) {
            try {
                this.c.f();
                this.b.close();
            }
            catch (ThreadDeath threadDeath) {
                throw threadDeath;
            }
            catch (Throwable t) {
                dw.b(t);
                continue;
            }
            break;
        }
    }
    
    @Override
    public final void mark(final int n) {
        this.b.mark(n);
    }
    
    @Override
    public final boolean markSupported() {
        return this.b.markSupported();
    }
    
    @Override
    public final int read() {
        int read;
        try {
            read = this.b.read();
            final m m = this;
            final aa aa = m.c;
            final int n = read;
            aa.a(n);
            return read;
        }
        catch (IOException ex) {
            this.a(ex);
            throw ex;
        }
        try {
            final m m = this;
            final aa aa = m.c;
            final int n = read;
            aa.a(n);
            return read;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (IllegalStateException ex2) {
            this.c = ao.d;
            return read;
        }
        catch (Throwable t) {
            this.c = ao.d;
            dw.b(t);
            return read;
        }
    }
    
    @Override
    public final int read(final byte[] array) {
        try {
            final int read = this.b.read(array);
            this.a(array, 0, read);
            return read;
        }
        catch (IOException ex) {
            this.a(ex);
            throw ex;
        }
    }
    
    @Override
    public final int read(final byte[] array, final int n, int read) {
        try {
            read = this.b.read(array, n, read);
            this.a(array, n, read);
            return read;
        }
        catch (IOException ex) {
            this.a(ex);
            throw ex;
        }
    }
    
    @Override
    public final void reset() {
        synchronized (this) {
            this.b.reset();
        }
    }
    
    @Override
    public final long skip(final long n) {
        return this.b.skip(n);
    }
}
