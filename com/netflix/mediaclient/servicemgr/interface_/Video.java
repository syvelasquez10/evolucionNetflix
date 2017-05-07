// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

public interface Video extends BasicVideo
{
    String getBoxshotUrl();
    
    VideoType getErrorType();
    
    String getHorzDispUrl();
    
    String getSquareUrl();
    
    String getStoryDispUrl();
    
    String getTvCardUrl();
}
