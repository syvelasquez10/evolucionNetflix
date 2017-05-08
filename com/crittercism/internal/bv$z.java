// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.content.Context;

public final class bv$z implements bu
{
    private Float a;
    
    public bv$z() {
        this.a = null;
        this.a = bv.b.getResources().getDisplayMetrics().xdpi;
    }
    
    @Override
    public final String a() {
        return "xdpi";
    }
}
