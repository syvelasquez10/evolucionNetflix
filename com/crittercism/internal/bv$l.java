// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.content.Context;
import org.json.JSONArray;

public final class bv$l implements bu
{
    private JSONArray a;
    
    public bv$l() {
        this.a = null;
        if (bv.c.a) {
            this.a = bv.d.a();
        }
    }
    
    @Override
    public final String a() {
        return "logcat";
    }
}
