// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.Map;

public class HitBuilders$TimingBuilder extends HitBuilders$HitBuilder<HitBuilders$TimingBuilder>
{
    public HitBuilders$TimingBuilder() {
        t.eq().a(t$a.zO);
        this.set("&t", "timing");
    }
    
    public HitBuilders$TimingBuilder(final String category, final String variable, final long value) {
        this();
        this.setVariable(variable);
        this.setValue(value);
        this.setCategory(category);
    }
    
    public HitBuilders$TimingBuilder setCategory(final String s) {
        this.set("&utc", s);
        return this;
    }
    
    public HitBuilders$TimingBuilder setLabel(final String s) {
        this.set("&utl", s);
        return this;
    }
    
    public HitBuilders$TimingBuilder setValue(final long n) {
        this.set("&utt", Long.toString(n));
        return this;
    }
    
    public HitBuilders$TimingBuilder setVariable(final String s) {
        this.set("&utv", s);
        return this;
    }
}
