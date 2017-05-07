// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.identifier;

public final class AdvertisingIdClient$Info
{
    private final String ln;
    private final boolean lo;
    
    public AdvertisingIdClient$Info(final String ln, final boolean lo) {
        this.ln = ln;
        this.lo = lo;
    }
    
    public String getId() {
        return this.ln;
    }
    
    public boolean isLimitAdTrackingEnabled() {
        return this.lo;
    }
    
    @Override
    public String toString() {
        return "{" + this.ln + "}" + this.lo;
    }
}
