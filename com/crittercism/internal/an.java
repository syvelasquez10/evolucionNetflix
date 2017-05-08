// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class an extends aa
{
    private aa d;
    
    public an(final aa d) {
        super(d);
        this.d = d;
    }
    
    @Override
    public final boolean a(final int n) {
        if (n == -1) {
            super.a.a(ao.d);
            return true;
        }
        ++this.c;
        if ((char)n == '\n') {
            this.d.b(this.a());
            super.a.a(this.d);
            return true;
        }
        return false;
    }
    
    @Override
    public final boolean a(final ab ab) {
        return true;
    }
    
    @Override
    public final aa b() {
        return this;
    }
    
    @Override
    public final aa c() {
        return this;
    }
    
    @Override
    protected final int d() {
        return 0;
    }
    
    @Override
    protected final int e() {
        return 0;
    }
}
