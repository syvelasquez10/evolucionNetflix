// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

public interface Video extends BasicVideo
{
    String getBoxartId();
    
    String getBoxshotUrl();
    
    VideoType getErrorType();
    
    boolean isOriginal();
    
    boolean isPreRelease();
}
