// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.identifier;

public final class AdvertisingIdClient$Info
{
    private final String zzoh;
    private final boolean zzoi;
    
    public AdvertisingIdClient$Info(final String zzoh, final boolean zzoi) {
        this.zzoh = zzoh;
        this.zzoi = zzoi;
    }
    
    public String getId() {
        return this.zzoh;
    }
    
    public boolean isLimitAdTrackingEnabled() {
        return this.zzoi;
    }
    
    @Override
    public String toString() {
        return "{" + this.zzoh + "}" + this.zzoi;
    }
}
