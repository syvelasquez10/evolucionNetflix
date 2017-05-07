// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;

public interface VideoDetailsProvider extends PlayContextProvider
{
    boolean destroyed();
    
    ServiceManager getServiceManager();
    
    VideoDetails getVideoDetails();
    
    String getVideoId();
    
    VideoType getVideoType();
    
    void onActionExecuted();
}
