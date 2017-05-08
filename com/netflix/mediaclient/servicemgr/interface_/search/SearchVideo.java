// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.search;

import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.interface_.ArtworkUrlProvider;

public interface SearchVideo extends ArtworkUrlProvider, Video
{
    String getCertification();
    
    int getYear();
    
    boolean isOriginal();
    
    boolean isPreRelease();
}
