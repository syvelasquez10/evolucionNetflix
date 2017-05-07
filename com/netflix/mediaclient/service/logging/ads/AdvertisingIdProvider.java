// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.ads;

public interface AdvertisingIdProvider
{
    String getId();
    
    boolean isLimitAdTrackingEnabled();
    
    boolean isResettable();
}
