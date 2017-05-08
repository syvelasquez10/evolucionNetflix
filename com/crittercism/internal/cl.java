// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class cl
{
    public int a;
    public int b;
    
    public cl(final int a, final int b) {
        this.a = cm.d - 1;
        this.b = ck.a.ordinal();
        this.a = a;
        this.b = b;
    }
    
    public cl(final Throwable t) {
        this.a = cm.d - 1;
        this.b = ck.a.ordinal();
        if (t != null) {
            this.a = cm.a(t);
            if (this.a != cm.d - 1) {
                this.b = Integer.parseInt(t.getMessage());
                return;
            }
            this.b = ck.a(t).C;
        }
    }
}
