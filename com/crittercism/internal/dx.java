// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class dx extends Thread
{
    public dx(final Runnable runnable) {
        super(new de(runnable));
    }
    
    public dx(final Runnable runnable, final String s) {
        super(new de(runnable), s);
    }
}
