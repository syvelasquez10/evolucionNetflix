// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

public interface Video extends BasicVideo
{
    String getBoxshotUrl();
    
    VideoType getErrorType();
    
    String getHorzDispSmallUrl();
    
    String getHorzDispUrl();
    
    String getStoryDispUrl();
    
    String getTvCardUrl();
    
    boolean hasWatched();
    
    boolean isNSRE();
    
    boolean isOriginal();
    
    boolean isPreRelease();
}
