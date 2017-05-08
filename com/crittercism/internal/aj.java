// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class aj extends aa
{
    public aj(final ah ah) {
        super(ah);
    }
    
    @Override
    public final boolean a(final ab ab) {
        final String[] split = ab.toString().split(" ");
        if (split.length == 3) {
            super.a.a(split[0], split[1]);
            return true;
        }
        return false;
    }
    
    @Override
    public final aa b() {
        return new ai(this);
    }
    
    @Override
    public final aa c() {
        return ao.d;
    }
    
    @Override
    protected final int d() {
        return 64;
    }
    
    @Override
    protected final int e() {
        return 2048;
    }
}
