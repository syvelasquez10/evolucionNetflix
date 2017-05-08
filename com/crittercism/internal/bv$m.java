// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class bv$m implements bu
{
    private Long a;
    
    public bv$m() {
        this.a = null;
        this.a = Runtime.getRuntime().maxMemory();
    }
    
    @Override
    public final String a() {
        return "memory_total";
    }
}
