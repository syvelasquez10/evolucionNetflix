// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.service.webclient.model.branches.Season;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.netflix.mediaclient.service.webclient.model.SeasonDetails;
import com.netflix.mediaclient.service.webclient.model.PostPlayVideo;
import com.netflix.mediaclient.service.webclient.model.EpisodeDetails;
import com.netflix.mediaclient.service.webclient.model.CWVideo;
import com.netflix.mediaclient.service.webclient.model.BillboardDetails;

public class BrowseVideoSentinels
{
    private static final BillboardDetails bbVideoSentinel;
    private static final CWVideo cwVideoSentinel;
    private static final EpisodeDetails episodeDetailsSentinel;
    private static final PostPlayVideo postPlayVideoSentinel;
    private static final SeasonDetails seasonDetailsSentinel;
    private static final Video.Summary videoSummarySentinel;
    
    static {
        videoSummarySentinel = new Video.Summary();
        episodeDetailsSentinel = new EpisodeDetails();
        seasonDetailsSentinel = new SeasonDetails();
        cwVideoSentinel = new CWVideo();
        bbVideoSentinel = new BillboardDetails();
        postPlayVideoSentinel = new PostPlayVideo();
    }
    
    private BrowseVideoSentinels() {
        BrowseVideoSentinels.videoSummarySentinel.setErrorType(VideoType.UNAVAILABLE);
        ((Video.Summary)(BrowseVideoSentinels.seasonDetailsSentinel.detail = new Season.Detail())).setErrorType(VideoType.UNAVAILABLE);
        (BrowseVideoSentinels.cwVideoSentinel.summary = new Video.Summary()).setErrorType(VideoType.UNAVAILABLE);
    }
    
    public static BillboardDetails getUnavailableBBVideo() {
        return BrowseVideoSentinels.bbVideoSentinel;
    }
    
    public static CWVideo getUnavailableCwVideo() {
        return BrowseVideoSentinels.cwVideoSentinel;
    }
    
    public static EpisodeDetails getUnavailableEpisodeDetails() {
        return BrowseVideoSentinels.episodeDetailsSentinel;
    }
    
    public static PostPlayVideo getUnavailablePostPlayVideo() {
        return BrowseVideoSentinels.postPlayVideoSentinel;
    }
    
    public static SeasonDetails getUnavailableSeasonsDetails() {
        return BrowseVideoSentinels.seasonDetailsSentinel;
    }
    
    public static Video.Summary getUnavailableVideoSummary() {
        return BrowseVideoSentinels.videoSummarySentinel;
    }
}
