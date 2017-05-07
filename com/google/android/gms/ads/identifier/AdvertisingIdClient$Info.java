// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.identifier;

public final class AdvertisingIdClient$Info
{
    private final String zzol;
    private final boolean zzom;
    
    public AdvertisingIdClient$Info(final String zzol, final boolean zzom) {
        this.zzol = zzol;
        this.zzom = zzom;
    }
    
    public String getId() {
        return this.zzol;
    }
    
    public boolean isLimitAdTrackingEnabled() {
        return this.zzom;
    }
    
    @Override
    public String toString() {
        return "{" + this.zzol + "}" + this.zzom;
    }
}
