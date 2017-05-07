// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.branches;

import java.util.List;
import com.netflix.mediaclient.service.webclient.model.leafs.TrackableListSummary;

public abstract class Video
{
    public TrackableListSummary similarListSummary;
    public List<com.netflix.mediaclient.servicemgr.model.Video> similarVideos;
}
