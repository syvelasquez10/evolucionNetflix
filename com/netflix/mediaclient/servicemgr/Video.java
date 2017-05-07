// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public interface Video
{
    String getBoxshotURL();
    
    VideoType getErrorType();
    
    String getId();
    
    String getTitle();
    
    VideoType getType();
}
