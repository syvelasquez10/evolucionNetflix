// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.details;

import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.FriendProfilesProvider;

public interface MovieDetails extends FriendProfilesProvider, EvidenceDetails, VideoDetails
{
    String getDirectors();
    
    int getNumDirectors();
    
    List<Video> getSimilars();
    
    int getSimilarsListPos();
    
    String getSimilarsRequestId();
    
    int getSimilarsTrackId();
}
