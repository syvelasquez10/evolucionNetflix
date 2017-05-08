// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class ak extends ag
{
    private int g;
    
    public ak(final aa aa, final int g) {
        super(aa);
        this.g = g;
    }
    
    @Override
    protected final aa g() {
        int n;
        if (super.a.c().equals("HEAD") || (this.g >= 100 && this.g <= 199) || this.g == 204 || this.g == 304) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            super.a.b(this.a());
            return super.a.b();
        }
        if (super.f) {
            return new ae(this);
        }
        if (super.d) {
            if (super.e > 0) {
                return new ac(this, super.e);
            }
            super.a.b(this.a());
            return super.a.b();
        }
        else {
            if (super.a.c().equals("CONNECT")) {
                super.a.b(this.a());
                return super.a.b();
            }
            return new af(this);
        }
    }
}
