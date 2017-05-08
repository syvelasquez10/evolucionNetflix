// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class ad extends aa
{
    private ae d;
    private int e;
    private int f;
    
    public ad(final ae d, final int e) {
        super(d);
        this.f = 0;
        this.d = d;
        this.e = e;
    }
    
    @Override
    public final boolean a(int n) {
        if (this.f < this.e + 2) {
            if (n == -1) {
                super.a.b(this.a());
                super.a.a(ao.d);
                return true;
            }
            ++this.c;
            n = (char)n;
            ++this.f;
            if (this.f > this.e) {
                if (n == 10) {
                    this.d.b(this.a());
                    super.a.a(this.d);
                    return true;
                }
                if (this.f == this.e + 2 && n != 10) {
                    super.a.a(ao.d);
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public final boolean a(final ab ab) {
        return true;
    }
    
    @Override
    public final aa b() {
        return this.d;
    }
    
    @Override
    public final aa c() {
        return null;
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
