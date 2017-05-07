// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model;

public interface Billboard extends Video
{
    String getCertification();
    
    int getNumOfSeasons();
    
    Playable getPlayable();
    
    String getStoryUrl();
    
    String getSynopsis();
    
    int getYear();
}
