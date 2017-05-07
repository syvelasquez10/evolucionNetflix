// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.Map;

public class HitBuilders$EventBuilder extends HitBuilders$HitBuilder<HitBuilders$EventBuilder>
{
    public HitBuilders$EventBuilder() {
        t.eq().a(t$a.Ad);
        this.set("&t", "event");
    }
    
    public HitBuilders$EventBuilder(final String category, final String action) {
        this();
        this.setCategory(category);
        this.setAction(action);
    }
    
    public HitBuilders$EventBuilder setAction(final String s) {
        this.set("&ea", s);
        return this;
    }
    
    public HitBuilders$EventBuilder setCategory(final String s) {
        this.set("&ec", s);
        return this;
    }
    
    public HitBuilders$EventBuilder setLabel(final String s) {
        this.set("&el", s);
        return this;
    }
    
    public HitBuilders$EventBuilder setValue(final long n) {
        this.set("&ev", Long.toString(n));
        return this;
    }
}
