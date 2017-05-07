// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public interface AdvertiserIdLogging
{
    AdverisingATrackingPreference getAdverisingTrackingPreference();
    
    String getAdvertiserId();
    
    boolean isSupported();
    
    void sendAdvertiserId(final EventType p0);
    
    public enum AdverisingATrackingPreference
    {
        OPT_IN, 
        OPT_OUT;
    }
    
    public enum EventType
    {
        check_in, 
        install, 
        sign_in, 
        sign_up;
    }
}
