// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public interface AdvertiserIdLogging
{
    public static final String ONSIGNUP = "com.netflix.mediaclient.intent.action.ONSIGNUP";
    
    AdvertiserIdLogging$AdverisingATrackingPreference getAdverisingTrackingPreference();
    
    String getAdvertiserId();
    
    boolean isSupported();
    
    void sendAdvertiserId(final AdvertiserIdLogging$EventType p0);
}
