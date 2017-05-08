// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.content.Context;

public final class bv$h implements bu
{
    private Float a;
    
    public bv$h() {
        this.a = null;
        this.a = bv.b.getResources().getDisplayMetrics().density;
    }
    
    @Override
    public final String a() {
        return "dpi";
    }
}
