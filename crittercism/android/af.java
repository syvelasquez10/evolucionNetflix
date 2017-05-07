// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.apache.http.util.CharArrayBuffer;

public abstract class af
{
    al a;
    CharArrayBuffer b;
    protected int c;
    private int d;
    
    public af(final af af) {
        this.a(af.a, af.c);
    }
    
    public af(final al al) {
        this.a(al, 0);
    }
    
    private void a(final al a, final int c) {
        this.a = a;
        this.d = this.e();
        this.b = new CharArrayBuffer(this.d());
        this.c = c;
    }
    
    private void g() {
        this.a.a(as.d);
    }
    
    public final int a() {
        return this.c;
    }
    
    public final void a(final byte[] array, final int n, final int n2) {
        int b2;
        for (int b = this.b(array, n, n2); b > 0 && b < n2; b += b2) {
            b2 = this.a.a().b(array, n + b, n2 - b);
            if (b2 <= 0) {
                break;
            }
        }
    }
    
    public boolean a(final int n) {
        if (n == -1) {
            this.g();
        }
        else {
            ++this.c;
            final char c = (char)n;
            af af;
            if (c == '\n') {
                if (this.a(this.b)) {
                    af = this.b();
                }
                else {
                    af = as.d;
                }
            }
            else if (this.b.length() < this.d) {
                this.b.append(c);
                af = this;
            }
            else {
                af = this.c();
            }
            if (af != this) {
                this.a.a(af);
            }
            if (af == this) {
                return false;
            }
        }
        return true;
    }
    
    public abstract boolean a(final CharArrayBuffer p0);
    
    protected int b(final byte[] array, final int n, final int n2) {
        boolean a = false;
        final int n3 = -1;
        int n4;
        if (n2 == -1) {
            this.g();
            n4 = n3;
        }
        else {
            n4 = n3;
            if (array != null) {
                n4 = n3;
                if (n2 != 0) {
                    int n5 = 0;
                    while (true) {
                        n4 = n5;
                        if (a || (n4 = n5) >= n2) {
                            break;
                        }
                        a = this.a((char)array[n + n5]);
                        ++n5;
                    }
                }
            }
        }
        return n4;
    }
    
    public abstract af b();
    
    public final void b(final int c) {
        this.c = c;
    }
    
    public abstract af c();
    
    protected abstract int d();
    
    protected abstract int e();
    
    public void f() {
        if (this.a != null) {
            this.a.a(as.d);
        }
    }
    
    @Override
    public final String toString() {
        return this.b.toString();
    }
}
