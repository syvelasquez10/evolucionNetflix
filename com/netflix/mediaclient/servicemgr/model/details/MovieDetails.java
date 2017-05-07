// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.details;

import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.FriendProfilesProvider;

public interface MovieDetails extends VideoDetails, FriendProfilesProvider
{
    String getDirectors();
    
    int getNumDirectors();
    
    List<Video> getSimilars();
    
    int getSimilarsListPos();
    
    String getSimilarsRequestId();
    
    int getSimilarsTrackId();
}
