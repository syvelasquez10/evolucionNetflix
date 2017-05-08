// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui;

import com.netflix.mediaclient.media.VideoResolutionRange;
import android.view.Surface;
import com.netflix.mediaclient.service.webclient.model.leafs.PreviewContentConfigData;
import com.netflix.mediaclient.media.bitrate.AudioBitrateRange;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.event.nrdp.media.SubtitleUrl;
import com.netflix.mediaclient.util.ConnectivityUtils$NetType;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.media.Watermark;
import com.netflix.mediaclient.media.TrickplayUrl;
import com.netflix.mediaclient.media.PlayoutMetadata;
import android.graphics.Point;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import com.netflix.mediaclient.util.PlaybackVolumeMetric;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.javabridge.invoke.media.AuthorizationParams;
import com.netflix.mediaclient.servicemgr.IManifestCache$CacheScheduleRequest;

public interface IMedia
{
    public static final int CLOSED = 4;
    public static final String NAME = "media";
    public static final int OPENING = 0;
    public static final String PATH = "nrdp.media";
    public static final int PAUSED = 2;
    public static final int PLAYING = 1;
    public static final int STOPPED = 3;
    
    void addEventListener(final String p0, final EventListener p1);
    
    void cacheFlush();
    
    void cachePause();
    
    void cacheResume();
    
    void cacheSchedule(final IManifestCache$CacheScheduleRequest[] p0, final AuthorizationParams p1);
    
    void changePlayer(final PlayerType p0);
    
    void close(final String p0, final PlaybackVolumeMetric p1);
    
    AudioSubtitleDefaultOrderInfo[] getAudioSubtitleDefaultOrderInfo();
    
    AudioSource[] getAudioTrackList();
    
    AudioSource getCurrentAudioTrack();
    
    int getCurrentPosition();
    
    Subtitle getCurrentSubtitleTrack();
    
    float getDisplayAspectRatio();
    
    Point getDisplayAspectRatioDimension();
    
    int getDuration();
    
    int getMediaPosition();
    
    PlayoutMetadata getPlayoutMetadata();
    
    int getState();
    
    IMedia$SubtitleOutputMode getSubtitleOutputMode();
    
    IMedia$SubtitleProfile getSubtitleProfile();
    
    Subtitle[] getSubtitleTrackList();
    
    TrickplayUrl[] getTrickplayUrlList();
    
    int getVideoHeight();
    
    int getVideoWidth();
    
    Watermark getWatermark();
    
    void open(final long p0, final PlayContext p1, final ConnectivityUtils$NetType p2, final long p3, final boolean p4, final PlaybackVolumeMetric p5, final long p6);
    
    void pause();
    
    void play(final long p0);
    
    void removeEventListener(final String p0, final EventListener p1);
    
    void reportFailedSubtitle(final String p0, final SubtitleUrl p1, final IMedia$SubtitleFailure p2, final boolean p3, final Status p4, final String[] p5);
    
    void reportSubtitleQoe(final String p0, final int p1, final int p2);
    
    void reset();
    
    void seekTo(final int p0, final boolean p1);
    
    boolean selectTracks(final AudioSource p0, final Subtitle p1);
    
    void setAudioBitrateRange(final AudioBitrateRange p0);
    
    void setBytesReport(final int p0, final int p1);
    
    void setCacheManifestType(final int p0);
    
    void setMaxCacheByteSize(final int p0);
    
    void setMaxCacheSize(final int p0);
    
    void setMaxVideoBufferSize(final int p0);
    
    void setNetworkProfile(final int p0);
    
    void setPreviewContentConfig(final PreviewContentConfigData p0);
    
    void setStreamingQoe(final String p0, final boolean p1, final boolean p2);
    
    void setSubtitleOutputMode(final IMedia$SubtitleOutputMode p0);
    
    void setSubtitleProfile(final IMedia$SubtitleProfile p0);
    
    void setSurface(final Surface p0);
    
    void setThrotteled(final boolean p0);
    
    void setVOapi(final long p0, final long p1);
    
    void setVideoBitrateRange(final int p0, final int p1);
    
    void setVideoResolutionRange(final VideoResolutionRange p0);
    
    void stop();
    
    void swim(final int p0, final boolean p1, final int p2, final boolean p3);
    
    void unpause();
    
    void volumeChange(final PlaybackVolumeMetric p0, final PlaybackVolumeMetric p1);
}
