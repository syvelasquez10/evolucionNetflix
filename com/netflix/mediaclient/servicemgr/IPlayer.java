// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import android.view.SurfaceHolder;
import android.view.Surface;
import com.netflix.mediaclient.media.JPlayer.JPlayer$JplayerListener;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.media.SubtitleUrl;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.media.PlayoutMetadata;
import android.graphics.Point;
import com.netflix.mediaclient.media.Subtitle;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;

public interface IPlayer
{
    public static final String EXTRA_TYPE = "lookupType";
    public static final String LOCAL_INTENT_CATEGORY = "com.netflix.mediaclient.intent.category.PLAYER";
    public static final String PLAYER_LOCAL_PLAYBACK_ENDED = "com.netflix.mediaclient.intent.action.PLAYER_LOCAL_PLAYBACK_ENDED";
    public static final String PLAYER_LOCAL_PLAYBACK_PAUSED = "com.netflix.mediaclient.intent.action.PLAYER_LOCAL_PLAYBACK_PAUSED";
    public static final String PLAYER_LOCAL_PLAYBACK_STARTED = "com.netflix.mediaclient.intent.action.PLAYER_LOCAL_PLAYBACK_STARTED";
    public static final String PLAYER_LOCAL_PLAYBACK_UNPAUSED = "com.netflix.mediaclient.intent.action.PLAYER_LOCAL_PLAYBACK_UNPAUSED";
    public static final String PLAYER_SUBTITLE_CONFIG_CHANGED = "com.netflix.mediaclient.intent.action.PLAYER_SUBTITLE_CONFIG_CHANGED";
    
    void addPlayerListener(final IPlayer$PlayerListener p0);
    
    boolean canUpdatePosition(final int p0);
    
    void close();
    
    AudioSubtitleDefaultOrderInfo[] getAudioSubtitleDefaultOrderInfo();
    
    AudioSource[] getAudioTrackList();
    
    ByteBuffer getBifFrame(final int p0);
    
    AudioSource getCurrentAudioTrack();
    
    long getCurrentPlayableId();
    
    int getCurrentPositionMs();
    
    int getCurrentProgress();
    
    Subtitle getCurrentSubtitleTrack();
    
    Point getDisplayAspectRatioDimension();
    
    int getDuration();
    
    IManifestCache getManifestCache();
    
    IPlayerFileCache getPlayerFileCache();
    
    PlayoutMetadata getPlayoutMetadata();
    
    SubtitleConfiguration getSubtitleConfiguration();
    
    ISubtitleDef$SubtitleProfile getSubtitleProfileFromMetadata();
    
    Subtitle[] getSubtitleTrackList();
    
    int getVideoHeight();
    
    int getVideoWidth();
    
    String getXid();
    
    boolean isBufferingCompleted();
    
    boolean isManifestCacheEnabled();
    
    boolean isPlaying();
    
    void open(final long p0, final PlayContext p1, final long p2);
    
    void pause();
    
    void play();
    
    void removePlayerListener(final IPlayer$PlayerListener p0);
    
    void reportFailedSubtitle(final String p0, final SubtitleUrl p1, final ISubtitleDef$SubtitleFailure p2, final boolean p3, final Status p4, final String[] p5);
    
    void reportSubtitleQoe(final String p0, final int p1, final int p2);
    
    void seekTo(final int p0, final boolean p1);
    
    void seekWithFuzzRange(final int p0, final int p1);
    
    boolean selectTracks(final AudioSource p0, final Subtitle p1, final boolean p2);
    
    void setAudioDuck(final boolean p0);
    
    void setJPlayerListener(final JPlayer$JplayerListener p0);
    
    void setSurface(final Surface p0);
    
    void setSurfaceHolder(final SurfaceHolder p0);
    
    void setVideoBitrateRange(final int p0, final int p1);
    
    void unpause();
}
