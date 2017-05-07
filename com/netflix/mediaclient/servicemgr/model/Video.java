// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model;

public interface Video
{
    String getBoxshotURL();
    
    VideoType getErrorType();
    
    String getHorzDispUrl();
    
    String getId();
    
    String getSquareUrl();
    
    String getTitle();
    
    String getTvCardUrl();
    
    VideoType getType();
}
