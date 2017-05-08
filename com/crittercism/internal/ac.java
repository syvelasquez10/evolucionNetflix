// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class ac extends aa
{
    private int d;
    private int e;
    
    public ac(final aa aa, final int d) {
        super(aa);
        this.e = 0;
        this.d = d;
    }
    
    @Override
    public final boolean a(final int n) {
        if (n == -1) {
            super.a.a(ao.d);
            return true;
        }
        ++this.e;
        ++this.c;
        if (this.e == this.d) {
            super.a.b(this.a());
            super.a.a(super.a.b());
            return true;
        }
        return false;
    }
    
    @Override
    public final boolean a(final ab ab) {
        return true;
    }
    
    public final int b(final byte[] array, int n, final int n2) {
        if (n2 == -1) {
            super.a.a(ao.d);
            return -1;
        }
        if (this.e + n2 < this.d) {
            this.e += n2;
            this.c += n2;
            return n2;
        }
        n = this.d - this.e;
        this.c += n;
        super.a.b(this.a());
        super.a.a(super.a.b());
        return n;
    }
    
    @Override
    public final aa b() {
        return ao.d;
    }
    
    @Override
    public final aa c() {
        return ao.d;
    }
    
    @Override
    protected final int d() {
        return 0;
    }
    
    @Override
    protected final int e() {
        return 0;
    }
    
    @Override
    public final void f() {
        super.a.b(this.a());
        super.a.a(ao.d);
    }
}
