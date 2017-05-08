// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class af extends aa
{
    public af(final aa aa) {
        super(aa);
    }
    
    @Override
    public final boolean a(final int n) {
        if (n == -1) {
            super.a.b(this.a());
            super.a.a(ao.d);
            return true;
        }
        ++this.c;
        return false;
    }
    
    @Override
    public final boolean a(final ab ab) {
        return true;
    }
    
    public final int b(final byte[] array, final int n, final int n2) {
        if (n2 == -1) {
            super.a.b(this.a());
            super.a.a(ao.d);
            return -1;
        }
        this.c += n2;
        return n2;
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
        return Integer.MAX_VALUE;
    }
    
    @Override
    public final void f() {
        super.a.b(this.a());
        super.a.a(ao.d);
    }
}
