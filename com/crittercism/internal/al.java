// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class al extends aa
{
    private int d;
    
    public al(final ah ah) {
        super(ah);
        this.d = -1;
    }
    
    @Override
    public final boolean a(final ab ab) {
        final String[] split = ab.toString().split(" ");
        if (split.length >= 3) {
            try {
                this.d = Integer.parseInt(split[1]);
                super.a.a(this.d);
                return true;
            }
            catch (NumberFormatException ex) {}
        }
        return false;
    }
    
    @Override
    public final aa b() {
        return new ak(this, this.d);
    }
    
    @Override
    public final aa c() {
        return ao.d;
    }
    
    @Override
    protected final int d() {
        return 20;
    }
    
    @Override
    protected final int e() {
        return 64;
    }
}
