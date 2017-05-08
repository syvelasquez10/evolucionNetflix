// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class am extends aa
{
    private boolean d;
    
    public am(final aa aa) {
        super(aa);
        this.d = false;
    }
    
    @Override
    public final boolean a(final ab ab) {
        this.d = (ab.a(ab.b).length() == 0);
        return true;
    }
    
    @Override
    public final aa b() {
        if (this.d) {
            super.a.b(this.a());
            return super.a.b();
        }
        super.b.b = 0;
        return this;
    }
    
    @Override
    public final aa c() {
        super.b.b = 0;
        return new an(this);
    }
    
    @Override
    protected final int d() {
        return 8;
    }
    
    @Override
    protected final int e() {
        return 128;
    }
}
