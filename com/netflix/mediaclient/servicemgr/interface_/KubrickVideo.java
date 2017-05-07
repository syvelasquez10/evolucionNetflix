// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

public interface KubrickVideo extends FeatureEnabledProvider, Ratable, Video
{
    String getCertification();
    
    String getKubrickStoryImgUrl();
    
    String getNarrative();
    
    int getRuntime();
    
    int getSeasonCount();
    
    String getTitleImgUrl();
    
    int getYear();
}
