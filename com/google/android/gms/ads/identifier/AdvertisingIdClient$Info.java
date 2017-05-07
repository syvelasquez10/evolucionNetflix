// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.identifier;

public final class AdvertisingIdClient$Info
{
    private final String zzom;
    private final boolean zzon;
    
    public AdvertisingIdClient$Info(final String zzom, final boolean zzon) {
        this.zzom = zzom;
        this.zzon = zzon;
    }
    
    public String getId() {
        return this.zzom;
    }
    
    public boolean isLimitAdTrackingEnabled() {
        return this.zzon;
    }
    
    @Override
    public String toString() {
        return "{" + this.zzom + "}" + this.zzon;
    }
}
