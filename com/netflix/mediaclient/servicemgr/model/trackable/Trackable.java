// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.trackable;

public interface Trackable
{
    int getHeroTrackId();
    
    int getListPos();
    
    String getRequestId();
    
    int getTrackId();
    
    boolean isHero();
}
