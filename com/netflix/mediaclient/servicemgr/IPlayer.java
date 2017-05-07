// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.media.Language;
import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import com.netflix.mediaclient.event.nrdp.media.NccpError;
import com.netflix.mediaclient.event.nrdp.media.Error;
import android.view.SurfaceHolder;
import android.view.Surface;
import com.netflix.mediaclient.media.JPlayer.JPlayer;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.javabridge.ui.IMedia;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.media.PlayoutMetadata;
import com.netflix.mediaclient.media.Subtitle;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;

public interface IPlayer
{
    public static final String EXTRA_TYPE = "lookupType";
    public static final String LOCAL_INTENT_CATEGORY = "com.netflix.mediaclient.intent.category.PLAYER";
    public static final String PLAYER_SUBTITLE_CONFIG_CHANGED = "com.netflix.mediaclient.intent.action.PLAYER_SUBTITLE_CONFIG_CHANGED";
    
    void addPlayerListener(final PlayerListener p0);
    
    boolean canUpdatePosition(final int p0);
    
    void close();
    
    AudioSubtitleDefaultOrderInfo[] getAudioSubtitleDefaultOrderInfo();
    
    AudioSource[] getAudioTrackList();
    
    ByteBuffer getBifFrame(final int p0);
    
    AudioSource getCurrentAudioTrack();
    
    int getCurrentPositionMs();
    
    int getCurrentProgress();
    
    Subtitle getCurrentSubtitleTrack();
    
    int getDuration();
    
    PlayoutMetadata getPlayoutMetadata();
    
    SubtitleConfiguration getSubtitleConfiguration();
    
    IMedia.SubtitleProfile getSubtitleProfileFromMetadata();
    
    Subtitle[] getSubtitleTrackList();
    
    int getVideoHeight();
    
    int getVideoWidth();
    
    boolean isBufferingCompleted();
    
    boolean isPlaying();
    
    void open(final long p0, final PlayContext p1, final long p2);
    
    void pause();
    
    void play();
    
    void removePlayerListener(final PlayerListener p0);
    
    void seekTo(final int p0, final boolean p1);
    
    boolean selectTracks(final AudioSource p0, final Subtitle p1);
    
    void setJPlayerListener(final JPlayer.JplayerListener p0);
    
    void setSurface(final Surface p0);
    
    void setSurfaceHolder(final SurfaceHolder p0);
    
    void unpause();
    
    public interface PlayerListener
    {
        void onAudioChange(final int p0);
        
        void onBandwidthChange(final int p0);
        
        void onBufferingUpdate(final int p0);
        
        void onCompletion();
        
        void onMediaError(final Error p0);
        
        void onNccpError(final NccpError p0);
        
        void onNrdFatalError();
        
        void onPlaying();
        
        void onPrepared();
        
        void onSeekComplete();
        
        void onStalled();
        
        void onStarted();
        
        void onSubtitleChange(final SubtitleScreen p0);
        
        void onSubtitleFailed();
        
        void onSubtitleRemove();
        
        void onSubtitleShow(final String p0);
        
        void onUpdatePts(final int p0);
        
        void onVideoSizeChanged(final int p0, final int p1);
        
        void playbackClosed();
        
        void restartPlayback(final NccpError p0);
        
        void setLanguage(final Language p0);
    }
}
