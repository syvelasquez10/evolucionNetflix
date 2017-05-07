// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model;

public interface Video extends BasicVideo
{
    String getBoxshotURL();
    
    VideoType getErrorType();
    
    String getHorzDispUrl();
    
    String getSquareUrl();
    
    String getTvCardUrl();
}
