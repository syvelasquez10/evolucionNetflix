// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.Map;

public class HitBuilders$SocialBuilder extends HitBuilders$HitBuilder<HitBuilders$SocialBuilder>
{
    public HitBuilders$SocialBuilder() {
        t.eq().a(t$a.zP);
        this.set("&t", "social");
    }
    
    public HitBuilders$SocialBuilder setAction(final String s) {
        this.set("&sa", s);
        return this;
    }
    
    public HitBuilders$SocialBuilder setNetwork(final String s) {
        this.set("&sn", s);
        return this;
    }
    
    public HitBuilders$SocialBuilder setTarget(final String s) {
        this.set("&st", s);
        return this;
    }
}
