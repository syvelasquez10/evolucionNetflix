// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

import android.view.Surface;
import android.util.Pair;
import com.netflix.mediaclient.media.PlayoutMetadata;
import com.netflix.mediaclient.media.Subtitle;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;

public interface IPlaybackSession
{
    AudioSubtitleDefaultOrderInfo[] getAudioSubtitleDefaultOrderInfo();
    
    AudioSource[] getAudioTrackList();
    
    ByteBuffer getBifFrame(final int p0);
    
    int getBufferedPercentage();
    
    long getBufferedPosition();
    
    AudioSource getCurrentAudioTrack();
    
    long getCurrentPlayableId();
    
    long getCurrentPosition();
    
    Subtitle getCurrentSubtitleTrack();
    
    long getDuration();
    
    PlayoutMetadata getPlayoutMetadata();
    
    String getSessionId();
    
    Subtitle[] getSubtitleTrackList();
    
    Pair<Integer, Integer> getWidthHeight();
    
    boolean isPlaying();
    
    void pause();
    
    void play();
    
    void release();
    
    void seekTo(final long p0);
    
    boolean selectAudioTrack(final AudioSource p0);
    
    boolean selectTracks(final AudioSource p0, final Subtitle p1, final boolean p2);
    
    void setAudioDuck(final boolean p0);
    
    void setSurface(final Surface p0);
    
    void stop();
}
