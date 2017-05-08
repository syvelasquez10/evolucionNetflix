// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class dt implements bt
{
    public int a;
    
    public dt(final int a) {
        this.a = a;
    }
    
    public final void a(final av av, final String s, final String s2) {
        synchronized (this) {
            av.a(s, s2, Integer.valueOf(this.a));
        }
    }
}
