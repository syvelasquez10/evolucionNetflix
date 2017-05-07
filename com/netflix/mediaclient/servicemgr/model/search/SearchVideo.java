// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.search;

import com.netflix.mediaclient.servicemgr.model.Video;

public interface SearchVideo extends Video
{
    String getCertification();
    
    int getYear();
}
