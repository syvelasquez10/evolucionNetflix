// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.target;

import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;

public class PlayerState
{
    public static final String PLAYER_STATE_AUTO_ADVANCE = "AUTO_ADVANCE";
    public static final String PLAYER_STATE_END_PLAYBACK = "END_PLAYBACK";
    public static final String PLAYER_STATE_FATAL_ERROR = "FATAL_ERROR";
    public static final String PLAYER_STATE_PAUSE = "PAUSE";
    public static final String PLAYER_STATE_PLAY = "PLAY";
    public static final String PLAYER_STATE_PLAYING = "PLAYING";
    public static final String PLAYER_STATE_PROGRESS = "PROGRESS";
    public static final String PLAYER_STATE_STALLED = "STALLED";
    public static final String PLAYER_STATE_STOP = "STOP";
    private static final String PROPERTY_autoAdvanceIncrement = "autoAdvanceIncrement";
    private static final String PROPERTY_catalogId = "catalogId";
    private static final String PROPERTY_currentState = "currentState";
    private static final String PROPERTY_duration = "duration";
    private static final String PROPERTY_episodeId = "episodeId";
    private static final String PROPERTY_time = "time";
    private static final String PROPERTY_volume = "volume";
    private static final String PROPERTY_xid = "xid";
    private String mAutoAdvanceIncrement;
    private String mCatalogId;
    private String mCurrentState;
    private int mDuration;
    private String mEpisodeId;
    private int mTime;
    private int mVolume;
    private String mXid;
    
    public PlayerState(final JSONObject jsonObject) throws JSONException {
        this.mTime = -1;
        this.mVolume = -1;
        this.mDuration = -1;
        this.mXid = jsonObject.optString("xid");
        this.mCatalogId = jsonObject.optString("catalogId");
        this.mDuration = jsonObject.optInt("duration", -1);
        this.mTime = jsonObject.optInt("time", -1);
        this.mVolume = jsonObject.optInt("volume", -1);
        this.mCurrentState = jsonObject.optString("currentState");
        this.mEpisodeId = jsonObject.optString("episodeId");
        this.mAutoAdvanceIncrement = JsonUtils.getString(jsonObject, "autoAdvanceIncrement", "0");
    }
    
    public String getAutoAdvanceIncrement() {
        return this.mAutoAdvanceIncrement;
    }
    
    public String getCatalogId() {
        return this.mCatalogId;
    }
    
    public String getCurrentState() {
        return this.mCurrentState;
    }
    
    public int getDuration() {
        return this.mDuration;
    }
    
    public String getEpisodeId() {
        return this.mEpisodeId;
    }
    
    public int getTime() {
        return this.mTime;
    }
    
    public int getVolume() {
        return this.mVolume;
    }
    
    public String getXid() {
        return this.mXid;
    }
    
    @Override
    public String toString() {
        return "PlayerCurrentState [currentState=" + this.mCurrentState + ", xid=" + this.mXid + ", catalogId=" + this.mCatalogId + ", episodeId=" + this.mEpisodeId + "]";
    }
}
