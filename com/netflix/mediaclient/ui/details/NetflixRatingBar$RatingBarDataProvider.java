// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.common.PlayContextProvider;

public interface NetflixRatingBar$RatingBarDataProvider extends PlayContextProvider
{
    boolean destroyed();
    
    ServiceManager getServiceManager();
    
    String getVideoId();
    
    VideoType getVideoType();
}
