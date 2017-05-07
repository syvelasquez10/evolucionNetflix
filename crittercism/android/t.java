// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.io.IOException;
import java.io.InputStream;

public final class t extends InputStream
{
    private final InputStream a;
    private final e b;
    private final c c;
    
    public t(final InputStream a, final e b, final c c) {
        if (a == null) {
            throw new NullPointerException("delegate was null");
        }
        if (b == null) {
            throw new NullPointerException("dispatch was null");
        }
        if (c == null) {
            throw new NullPointerException("stats were null");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    private void a(final int n, final int n2) {
        try {
            if (this.c != null) {
                if (n == -1) {
                    this.b.a(this.c);
                    return;
                }
                this.c.a((long)n2);
            }
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
        }
    }
    
    private void a(final Exception ex) {
        try {
            this.c.a(ex);
            this.b.a(this.c);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
        }
    }
    
    @Override
    public final int available() {
        return this.a.available();
    }
    
    @Override
    public final void close() {
        this.a.close();
    }
    
    @Override
    public final void mark(final int n) {
        this.a.mark(n);
    }
    
    @Override
    public final boolean markSupported() {
        return this.a.markSupported();
    }
    
    @Override
    public final int read() {
        try {
            final int read = this.a.read();
            this.a(read, 1);
            return read;
        }
        catch (IOException ex) {
            this.a(ex);
            throw ex;
        }
    }
    
    @Override
    public final int read(final byte[] array) {
        try {
            final int read = this.a.read(array);
            this.a(read, read);
            return read;
        }
        catch (IOException ex) {
            this.a(ex);
            throw ex;
        }
    }
    
    @Override
    public final int read(final byte[] array, int read, final int n) {
        try {
            read = this.a.read(array, read, n);
            this.a(read, read);
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
            this.a.reset();
        }
    }
    
    @Override
    public final long skip(long skip) {
        skip = this.a.skip(skip);
        try {
            if (this.c != null) {
                this.c.a(skip);
            }
            return skip;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
            return skip;
        }
    }
}
