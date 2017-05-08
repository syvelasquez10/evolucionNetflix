// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public abstract class aa
{
    ah a;
    ab b;
    protected int c;
    private int d;
    
    public aa(final aa aa) {
        this.a(aa.a, aa.c);
    }
    
    public aa(final ah ah) {
        this.a(ah, 0);
    }
    
    private void a(final ah a, final int c) {
        this.a = a;
        this.d = this.e();
        this.b = new ab(this.d());
        this.c = c;
    }
    
    private void g() {
        this.a.a(ao.d);
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
    
    public boolean a(int b) {
        if (b == -1) {
            this.g();
        }
        else {
            ++this.c;
            final char c = (char)b;
            aa aa;
            if (c == '\n') {
                if (this.a(this.b)) {
                    aa = this.b();
                }
                else {
                    aa = ao.d;
                }
            }
            else if (this.b.b < this.d) {
                final ab b2 = this.b;
                b = b2.b + 1;
                if (b > b2.a.length) {
                    final char[] a = new char[Math.max(b2.a.length << 1, b)];
                    System.arraycopy(b2.a, 0, a, 0, b2.b);
                    b2.a = a;
                }
                b2.a[b2.b] = c;
                b2.b = b;
                aa = this;
            }
            else {
                aa = this.c();
            }
            if (aa != this) {
                this.a.a(aa);
            }
            if (aa == this) {
                return false;
            }
        }
        return true;
    }
    
    public abstract boolean a(final ab p0);
    
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
    
    public abstract aa b();
    
    public final void b(final int c) {
        this.c = c;
    }
    
    public abstract aa c();
    
    protected abstract int d();
    
    protected abstract int e();
    
    public void f() {
        if (this.a != null) {
            this.a.a(ao.d);
        }
    }
    
    @Override
    public String toString() {
        return this.b.toString();
    }
}
