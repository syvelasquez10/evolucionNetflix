// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class dn$b extends dl
{
    private String a;
    
    private dn$b(final String a) {
        this.a = a;
    }
    
    @Override
    public final String a() {
        return "market://details?id=" + this.a;
    }
}
