// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class de extends di
{
    private Runnable a;
    
    public de(final Runnable a) {
        this.a = a;
    }
    
    @Override
    public final void a() {
        this.a.run();
    }
}
