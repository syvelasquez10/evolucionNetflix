// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.io.OutputStream;

public final class u extends OutputStream
{
    private final OutputStream a;
    private final c b;
    
    public u(final OutputStream a, final c b) {
        if (a == null) {
            throw new NullPointerException("delegate was null");
        }
        if (b == null) {
            throw new NullPointerException("stats were null");
        }
        this.a = a;
        this.b = b;
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
        while (true) {
            try {
                if (this.b != null) {
                    this.b.b();
                    this.b.c(1L);
                }
                this.a.write(n);
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
    
    @Override
    public final void write(final byte[] array) {
        if (this.b != null) {
            this.b.b();
            if (array != null) {
                this.b.c(array.length);
            }
        }
        this.a.write(array);
    }
    
    @Override
    public final void write(final byte[] array, final int n, final int n2) {
        if (this.b != null) {
            this.b.b();
            if (array != null) {
                this.b.c(n2);
            }
        }
        this.a.write(array, n, n2);
    }
}
