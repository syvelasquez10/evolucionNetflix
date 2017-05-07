// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model;

public interface CWVideo extends Video
{
    String getInterestingUrl();
    
    Playable getPlayable();
    
    String getStillUrl();
}
