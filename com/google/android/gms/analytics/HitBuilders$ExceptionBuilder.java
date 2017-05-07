// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.Map;

public class HitBuilders$ExceptionBuilder extends HitBuilders$HitBuilder<HitBuilders$ExceptionBuilder>
{
    public HitBuilders$ExceptionBuilder() {
        t.eq().a(t$a.zM);
        this.set("&t", "exception");
    }
    
    public HitBuilders$ExceptionBuilder setDescription(final String s) {
        this.set("&exd", s);
        return this;
    }
    
    public HitBuilders$ExceptionBuilder setFatal(final boolean b) {
        this.set("&exf", aj.C(b));
        return this;
    }
}
