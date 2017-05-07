// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model;

public interface KubrickVideo extends HdEnabledProvider, Ratable, Video
{
    String getCertification();
    
    String getKubrickHorzImgUrl();
    
    String getKubrickStoryImgUrl();
    
    String getNarrative();
    
    int getRuntime();
    
    int getSeasonCount();
    
    String getTitleImgUrl();
    
    int getYear();
}
