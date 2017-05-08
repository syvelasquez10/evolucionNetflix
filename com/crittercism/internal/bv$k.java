// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.content.Context;

public final class bv$k implements bu
{
    public String a;
    
    public bv$k() {
        this.a = null;
        this.a = bv.b.getResources().getConfiguration().locale.getLanguage();
        if (this.a == null || this.a.length() == 0) {
            this.a = "en";
        }
    }
    
    @Override
    public final String a() {
        return "locale";
    }
}
