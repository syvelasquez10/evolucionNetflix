// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import java.util.List;

public interface MovieDetails extends VideoDetails
{
    List<Video> getSimilars();
    
    int getSimilarsListPos();
    
    String getSimilarsRequestId();
    
    int getSimilarsTrackId();
    
    boolean isShared();
}
