// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui;

import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import com.netflix.mediaclient.media.VideoResolutionRange;
import android.view.Surface;
import com.netflix.mediaclient.media.bitrate.AudioBitrateRange;
import com.netflix.mediaclient.javabridge.invoke.media.Open;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.media.TrickplayUrl;
import com.netflix.mediaclient.media.PlayoutMetadata;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import com.netflix.mediaclient.media.PlayerType;

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
    
    void changePlayer(final PlayerType p0);
    
    void close();
    
    AudioSubtitleDefaultOrderInfo[] getAudioSubtitleDefaultOrderInfo();
    
    AudioSource[] getAudioTrackList();
    
    AudioSource getCurrentAudioTrack();
    
    int getCurrentPosition();
    
    Subtitle getCurrentSubtitleTrack();
    
    float getDisplayAspectRatio();
    
    int getDuration();
    
    int getMediaPosition();
    
    PlayoutMetadata getPlayoutMetadata();
    
    int getState();
    
    SubtitleOutputMode getSubtitleOutputMode();
    
    SubtitleProfile getSubtitleProfile();
    
    Subtitle[] getSubtitleTrackList();
    
    TrickplayUrl[] getTrickplayUrlList();
    
    int getVideoHeight();
    
    int getVideoWidth();
    
    void open(final long p0, final PlayContext p1, final Open.NetType p2);
    
    void pause();
    
    void play(final long p0);
    
    void removeEventListener(final String p0, final EventListener p1);
    
    void reset();
    
    void seekTo(final int p0, final boolean p1);
    
    boolean selectTracks(final AudioSource p0, final Subtitle p1);
    
    void setAudioBitrateRange(final AudioBitrateRange p0);
    
    void setBytesReport(final int p0, final int p1);
    
    void setMaxVideoBufferSize(final int p0);
    
    void setNetworkProfile(final int p0);
    
    void setStreamingQoe(final String p0);
    
    void setSubtitleOutputMode(final SubtitleOutputMode p0);
    
    void setSubtitleProfile(final SubtitleProfile p0);
    
    void setSurface(final Surface p0);
    
    void setThrotteled(final boolean p0);
    
    void setVOapi(final long p0, final long p1);
    
    void setVideoBitrateRange(final int p0, final int p1);
    
    void setVideoResolutionRange(final VideoResolutionRange p0);
    
    void setWifiApsInfo(final Context p0, final String p1, final boolean p2);
    
    void setWifiLinkSpeed(final Context p0);
    
    void stop();
    
    void swim(final int p0, final boolean p1, final int p2, final boolean p3);
    
    void unpause();
    
    public enum MediaEventEnum
    {
        generic_background("background"), 
        media_audioTrackChanged("audioTrackChanged"), 
        media_buffering("buffering"), 
        media_bufferingComplete("bufferingComplete"), 
        media_bufferrange("bufferrange"), 
        media_endOfStream("endOfStream"), 
        media_error("error"), 
        media_exception("exception"), 
        media_nccp("Nccp"), 
        media_nccpError("nccperror"), 
        media_newStream("newStream"), 
        media_openComplete("openComplete"), 
        media_removeSubtitle("removeSubtitle"), 
        media_setvideobitraterange("setvideobitraterange"), 
        media_setvideoresolutionrange("setvideoresolutionrange"), 
        media_showSubtitle("showSubtitle"), 
        media_skip("skip"), 
        media_stateChanged("statechanged"), 
        media_streamSelected("streamSelected"), 
        media_subtitleTrackChanged("subtitleTrackChanged"), 
        media_subtitledata("subtitledata"), 
        media_swim("swim"), 
        media_underflow("underflow"), 
        media_updatePts("updatePts"), 
        media_updateVideoBitrate("updateVideoBitrate"), 
        media_videoWindowChanged("videoWindowChanged"), 
        media_videobitraterangechanged("videobitraterangechanged"), 
        media_warning("warning");
        
        protected String eventName;
        
        private MediaEventEnum(final String eventName) {
            this.eventName = eventName;
        }
        
        public final String getName() {
            return this.eventName;
        }
    }
    
    public enum SubtitleOutputMode
    {
        DATA_JSON(2), 
        DATA_XML(1), 
        EVENTS(0);
        
        protected int mValue;
        
        private SubtitleOutputMode(final int mValue) {
            this.mValue = mValue;
        }
        
        public final int getValue() {
            return this.mValue;
        }
    }
    
    public enum SubtitleProfile
    {
        ENHANCED(1, "dfxp-ls-sdh"), 
        SIMPLE(0, "simplesdh");
        
        private String mNccpCode;
        private int mValue;
        
        private SubtitleProfile(final int mValue, final String mNccpCode) {
            this.mValue = mValue;
            this.mNccpCode = mNccpCode;
        }
        
        public static SubtitleProfile fromNccpCode(final String s) {
            if (!StringUtils.isEmpty(s)) {
                final String trim = s.trim();
                final SubtitleProfile[] values = values();
                for (int length = values.length, i = 0; i < length; ++i) {
                    final SubtitleProfile simple;
                    if ((simple = values[i]).getNccpCode().equalsIgnoreCase(trim)) {
                        return simple;
                    }
                }
                return SubtitleProfile.SIMPLE;
            }
            return SubtitleProfile.SIMPLE;
        }
        
        public String getNccpCode() {
            return this.mNccpCode;
        }
        
        public final int getValue() {
            return this.mValue;
        }
    }
}
