// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.util.WebApiUtils;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import android.util.Pair;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import android.annotation.SuppressLint;

@SuppressLint({ "UseSparseArrays" })
public interface IMdx
{
    public static final String CATEGORY_NFMDX = "com.netflix.mediaclient.intent.category.MDX";
    public static final String CATEGORY_NFMDXRCC = "com.netflix.mediaclient.intent.category.MDXRCC";
    public static final String MDXUPDATE_AUDIOSUB = "com.netflix.mediaclient.intent.action.MDXUPDATE_AUDIOSUB";
    public static final String MDXUPDATE_CAPABILITY = "com.netflix.mediaclient.intent.action.MDXUPDATE_CAPABILITY";
    public static final String MDXUPDATE_DIALOGCANCEL = "com.netflix.mediaclient.intent.action.MDXUPDATE_DIALOGCANCEL";
    public static final String MDXUPDATE_DIALOGSHOW = "com.netflix.mediaclient.intent.action.MDXUPDATE_DIALOGSHOW";
    public static final String MDXUPDATE_ERROR = "com.netflix.mediaclient.intent.action.MDXUPDATE_ERROR";
    public static final String MDXUPDATE_METADATA = "com.netflix.mediaclient.intent.action.MDXUPDATE_METADATA";
    public static final String MDXUPDATE_MOVIEMETADATA = "com.netflix.mediaclient.intent.action.MDXUPDATE_MOVIEMETADA";
    public static final String MDXUPDATE_MOVIEMETADATA_AVAILABLE = "com.netflix.mediaclient.intent.action.MDXUPDATE_MOVIEMETADATA_AVAILABLE";
    public static final String MDXUPDATE_NOTREADY = "com.netflix.mediaclient.intent.action.MDXUPDATE_NOTREADY";
    public static final String MDXUPDATE_PIN_VERIFICATION_SHOW = "com.netflix.mediaclient.intent.action.PIN_VERIFICATION_SHOW";
    public static final String MDXUPDATE_PLAYBACKEND = "com.netflix.mediaclient.intent.action.MDXUPDATE_PLAYBACKEND";
    public static final String MDXUPDATE_PLAYBACKSTART = "com.netflix.mediaclient.intent.action.MDXUPDATE_PLAYBACKSTART";
    public static final String MDXUPDATE_POSTPLAY = "com.netflix.mediaclient.intent.action.MDXUPDATE_POSTPLAY";
    public static final String MDXUPDATE_READY = "com.netflix.mediaclient.intent.action.MDXUPDATE_READY";
    public static final String MDXUPDATE_SIMPLE_PLAYBACKSTATE = "com.netflix.mediaclient.intent.action.MDXUPDATE_SIMPLE_PLAYBACKSTATE";
    public static final String MDXUPDATE_STATE = "com.netflix.mediaclient.intent.action.MDXUPDATE_STATE";
    public static final String MDXUPDATE_TARGETLIST = "com.netflix.mediaclient.intent.action.MDXUPDATE_TARGETLIST";
    public static final String MDX_AUTOADV = "com.netflix.mediaclient.intent.action.MDX_AUTOADV";
    public static final String MDX_DIALOGRESP = "com.netflix.mediaclient.intent.action.MDX_DIALOGRESP";
    public static final int MDX_ERROR_INIT_ERROR = 103;
    public static final int MDX_ERROR_LAUNCH_ERROR = 106;
    @SuppressLint({ "UseSparseArrays" })
    public static final Map<Integer, Integer> MDX_ERROR_MAP = new HashMap<Integer, Integer>() {
        {
            this.put(100, 2131493233);
            this.put(104, 2131493234);
            this.put(105, 2131493235);
        }
    };
    public static final int MDX_ERROR_PAIR_ERROR = 104;
    public static final int MDX_ERROR_SESSION_ERROR = 105;
    public static final int MDX_ERROR_SHOW_TOAST = 300;
    public static final int MDX_ERROR_STATE_TIMEOUT = 100;
    public static final int MDX_ERROR_TARGETCONTEXT_GONE = 201;
    public static final int MDX_ERROR_TARGET_GONE = 200;
    public static final String MDX_EXTRA_AUDIO_TRACK_ID = "audioTrackId";
    public static final String MDX_EXTRA_CATALOG_ID = "catalogId";
    public static final String MDX_EXTRA_CURRENT_STATE = "currentState";
    public static final String MDX_EXTRA_DATA = "data";
    public static final String MDX_EXTRA_DURATION = "duration";
    public static final String MDX_EXTRA_EPISODE_ID = "episodeId";
    public static final String MDX_EXTRA_ERROR_CODE = "errorCode";
    public static final String MDX_EXTRA_ERROR_DESC = "errorDesc";
    public static final String MDX_EXTRA_PAUSED = "paused";
    public static final String MDX_EXTRA_PIN_ANCESTOR_VIDEOID = "ancestorVideoId";
    public static final String MDX_EXTRA_PIN_ANCESTOR_VIDEOTYPE = "ancestorVideoType";
    public static final String MDX_EXTRA_PIN_ISVERIFIED = "isPinVerified";
    public static final String MDX_EXTRA_PIN_TITLE = "title";
    public static final String MDX_EXTRA_PIN_VIDEOID = "videoId";
    public static final String MDX_EXTRA_POSITION_SECONDS = "time";
    public static final String MDX_EXTRA_POSTPLAYSTATE = "postplayState";
    public static final String MDX_EXTRA_POSTPLAY_PLAYNEXT = "playNext";
    public static final String MDX_EXTRA_SPEED = "speed";
    public static final String MDX_EXTRA_STRING_BLOB = "stringBlob";
    public static final String MDX_EXTRA_SUBTITLE_TRACK_ID = "subtitleTrackId";
    public static final String MDX_EXTRA_TRACKID = "trackId";
    public static final String MDX_EXTRA_TRANSITIONING = "transitioning";
    public static final String MDX_EXTRA_TYPE = "type";
    public static final String MDX_EXTRA_UID = "uid";
    public static final String MDX_EXTRA_UUID = "uuid";
    public static final String MDX_EXTRA_VOLUME = "volume";
    public static final String MDX_GETAUDIOSUB = "com.netflix.mediaclient.intent.action.MDX_GETAUDIOSUB";
    public static final String MDX_GETCAPABILITY = "com.netflix.mediaclient.intent.action.MDX_GETCAPABILITY";
    public static final String MDX_GETSTATE = "com.netflix.mediaclient.intent.action.MDX_GETSTATE";
    public static final String MDX_METADATA = "com.netflix.mediaclient.intent.action.MDX_METADATA";
    public static final String MDX_PAUSE = "com.netflix.mediaclient.intent.action.MDX_PAUSE";
    public static final String MDX_PINCANCELLED = "com.netflix.mediaclient.intent.action.MDX_PINCANCELLED";
    public static final String MDX_PINCONFIRMED = "com.netflix.mediaclient.intent.action.MDX_PINCONFIRMED";
    public static final String MDX_PLAY = "com.netflix.mediaclient.intent.action.MDX_PLAY";
    public static final String MDX_PLAY_VIDEOIDS = "com.netflix.mediaclient.intent.action.MDX_PLAY_VIDEOIDS";
    public static final String MDX_RESUME = "com.netflix.mediaclient.intent.action.MDX_RESUME";
    public static final String MDX_SEEK = "com.netflix.mediaclient.intent.action.MDX_SEEK";
    public static final String MDX_SELECT_TARGET = "com.netflix.mediaclient.intent.action.MDX_SELECT_TARGET";
    public static final String MDX_SETAUDIOSUB = "com.netflix.mediaclient.intent.action.MDX_SETAUDIOSUB";
    public static final String MDX_SETVOLUME = "com.netflix.mediaclient.intent.action.MDX_SETVOLUME";
    public static final String MDX_SKIP = "com.netflix.mediaclient.intent.action.MDX_SKIP";
    public static final String MDX_STOP = "com.netflix.mediaclient.intent.action.MDX_STOP";
    public static final String MDX_TOGGLE_PAUSE = "com.netflix.mediaclient.intent.action.MDX_TOGGLE_PAUSE";
    public static final String PIN_VERIFICATION_NOT_REQUIRED = "com.netflix.mediaclient.intent.action.PIN_VERIFICATION_NOT_REQUIRED";
    public static final String PLAYER_STATE_AUTO_ADVANCE = "AUTO_ADVANCE";
    public static final String PLAYER_STATE_END_PLAYBACK = "END_PLAYBACK";
    public static final String PLAYER_STATE_FATAL_ERROR = "FATAL_ERROR";
    public static final String PLAYER_STATE_PAUSE = "PAUSE";
    public static final String PLAYER_STATE_PLAY = "PLAY";
    public static final String PLAYER_STATE_PLAYING = "PLAYING";
    public static final String PLAYER_STATE_PREPAUSE = "prepause";
    public static final String PLAYER_STATE_PREPLAY = "preplay";
    public static final String PLAYER_STATE_PRESEEK = "preseek";
    public static final String PLAYER_STATE_PROGRESS = "PROGRESS";
    public static final String PLAYER_STATE_STALLED = "STALLED";
    public static final String PLAYER_STATE_STOP = "STOP";
    
    ByteBuffer getBifFrame(final int p0);
    
    String getCurrentTarget();
    
    IMdxSharedState getSharedState();
    
    Pair<String, String>[] getTargetList();
    
    VideoDetails getVideoDetail();
    
    WebApiUtils.VideoIds getVideoIds();
    
    boolean isBifReady();
    
    boolean isPaused();
    
    boolean isReady();
    
    boolean isTargetLaunchingOrLaunched();
    
    void setCurrentTarget(final String p0);
    
    boolean setDialUuidAsCurrentTarget(final String p0);
    
    void switchPlaybackFromTarget(final String p0, final int p1);
}
