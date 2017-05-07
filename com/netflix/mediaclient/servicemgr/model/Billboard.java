// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model;

public interface Billboard extends Playable, Video
{
    String getCertification();
    
    int getNumOfSeasons();
    
    String getStoryUrl();
    
    String getSynopsis();
    
    int getYear();
}
