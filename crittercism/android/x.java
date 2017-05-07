// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.io.IOException;
import java.io.InputStream;

public final class x extends InputStream implements al
{
    private ae a;
    private c b;
    private InputStream c;
    private e d;
    private af e;
    
    public x(final ae a, final InputStream c, final e d) {
        if (a == null) {
            throw new NullPointerException("socket was null");
        }
        if (c == null) {
            throw new NullPointerException("delegate was null");
        }
        if (d == null) {
            throw new NullPointerException("dispatch was null");
        }
        this.a = a;
        this.c = c;
        this.d = d;
        this.e = this.b();
        if (this.e == null) {
            throw new NullPointerException("parser was null");
        }
    }
    
    private void a(final Exception ex) {
        try {
            final c e = this.e();
            e.a(ex);
            this.d.a(e, c$a.h);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
        }
        catch (IllegalStateException ex2) {}
    }
    
    private void a(final byte[] array, final int n, final int n2) {
        try {
            this.e.a(array, n, n2);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (IllegalStateException ex) {
            this.e = as.d;
        }
        catch (Throwable t) {
            this.e = as.d;
            dy.a(t);
        }
    }
    
    private c e() {
        if (this.b == null) {
            this.b = this.a.b();
        }
        if (this.b == null) {
            throw new IllegalStateException("No statistics were queued up.");
        }
        return this.b;
    }
    
    @Override
    public final af a() {
        return this.e;
    }
    
    @Override
    public final void a(final int e) {
        final c e2 = this.e();
        e2.c();
        e2.e = e;
    }
    
    @Override
    public final void a(final af e) {
        this.e = e;
    }
    
    @Override
    public final void a(final String s) {
    }
    
    @Override
    public final void a(final String s, final String s2) {
    }
    
    public final boolean a(final InputStream inputStream) {
        return this.c == inputStream;
    }
    
    @Override
    public final int available() {
        return this.c.available();
    }
    
    @Override
    public final af b() {
        return new ap(this);
    }
    
    @Override
    public final void b(final int n) {
        c b = null;
        final c c = null;
        final c b2 = this.b;
        if (this.b != null) {
            final int e = this.b.e;
            b = c;
            if (e >= 100) {
                b = c;
                if (e < 200) {
                    b = new c(this.b.a());
                    b.e(this.b.a);
                    b.d(this.b.d);
                    b.f = this.b.f;
                }
            }
            this.b.b(n);
            this.d.a(this.b, c$a.g);
        }
        this.b = b;
    }
    
    @Override
    public final String c() {
        return this.e().f;
    }
    
    @Override
    public final void close() {
        while (true) {
            try {
                this.e.f();
                this.c.close();
            }
            catch (ThreadDeath threadDeath) {
                throw threadDeath;
            }
            catch (Throwable t) {
                dy.a(t);
                continue;
            }
            break;
        }
    }
    
    public final void d() {
        if (this.b != null) {
            final cn g = this.b.g;
            final cm a = cm.a;
            boolean b;
            if (g.a == co.d.ordinal() && g.b == a.a()) {
                b = true;
            }
            else {
                b = false;
            }
            if (b && this.e != null) {
                this.e.f();
            }
        }
    }
    
    @Override
    public final void mark(final int n) {
        this.c.mark(n);
    }
    
    @Override
    public final boolean markSupported() {
        return this.c.markSupported();
    }
    
    @Override
    public final int read() {
        int read;
        try {
            read = this.c.read();
            final x x = this;
            final af af = x.e;
            final int n = read;
            af.a(n);
            return read;
        }
        catch (IOException ex) {
            this.a(ex);
            throw ex;
        }
        try {
            final x x = this;
            final af af = x.e;
            final int n = read;
            af.a(n);
            return read;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (IllegalStateException ex2) {
            this.e = as.d;
            return read;
        }
        catch (Throwable t) {
            this.e = as.d;
            dy.a(t);
            return read;
        }
    }
    
    @Override
    public final int read(final byte[] array) {
        try {
            final int read = this.c.read(array);
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
            read = this.c.read(array, n, read);
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
            this.c.reset();
        }
    }
    
    @Override
    public final long skip(final long n) {
        return this.c.skip(n);
    }
}
