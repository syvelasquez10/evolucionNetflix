// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.search;

import com.netflix.mediaclient.servicemgr.interface_.Video;

public interface SearchVideo extends Video
{
    String getCertification();
    
    int getYear();
    
    boolean isOriginal();
    
    boolean isPreRelease();
}
