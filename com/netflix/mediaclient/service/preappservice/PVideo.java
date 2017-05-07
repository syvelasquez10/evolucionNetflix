// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preappservice;

import com.netflix.mediaclient.service.webclient.model.branches.Video$Summary;
import com.netflix.mediaclient.service.webclient.model.branches.Video$UserRating;
import com.netflix.mediaclient.service.webclient.model.branches.Video$InQueue;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Detail;
import com.netflix.mediaclient.service.webclient.model.branches.Episode$Detail;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Bookmark;

public class PVideo
{
    public Video$Bookmark bookmark;
    public Episode$Detail currentEpisode;
    public Video$Bookmark currentEpisodeBookmark;
    public Video$Detail detail;
    public Video$InQueue inQueue;
    public Video$UserRating rating;
    public Video$Summary summary;
}
