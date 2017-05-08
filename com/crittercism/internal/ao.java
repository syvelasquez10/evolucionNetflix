// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class ao extends aa
{
    public static final ao d;
    
    static {
        d = new ao();
    }
    
    private ao() {
        super((ah)null);
    }
    
    @Override
    public final boolean a(final int n) {
        ++this.c;
        return false;
    }
    
    @Override
    public final boolean a(final ab ab) {
        return true;
    }
    
    public final int b(final byte[] array, final int n, final int n2) {
        this.c += n2;
        return -1;
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
