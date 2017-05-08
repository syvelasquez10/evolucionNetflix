// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pdslogging;

import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import org.json.JSONArray;
import com.netflix.mediaclient.NetflixApplication;
import org.json.JSONObject;

public class PdsPlayEventParamBuilder
{
    private static final String TAG;
    private String mAppSessionId;
    private DownloadContext mDc;
    private long mDurationMs;
    private String mDxid;
    private long mEndPositionMs;
    private JSONObject mEventLink;
    private boolean mHasErrors;
    private int mListPos;
    private String mOxid;
    private JSONObject mPlayTimes;
    private String mPlayableId;
    private long mSessionEndTimeMs;
    private long mSessionStartTimeMs;
    private long mStartPositionMs;
    private int mTrackId;
    private String mUserSessionId;
    private int mVideoPos;
    private String mXid;
    
    static {
        TAG = PdsDownloadEventParamBuilder.class.getSimpleName();
    }
    
    public PdsPlayEventParamBuilder(final String mXid, final String mPlayableId, final JSONObject mEventLink, final String mAppSessionId, final String mUserSessionId) {
        this.mXid = mXid;
        this.mPlayableId = mPlayableId;
        this.mEventLink = mEventLink;
        this.mAppSessionId = mAppSessionId;
        this.mUserSessionId = mUserSessionId;
    }
    
    final String build() {
        final JSONObject jsonObject = new JSONObject();
        if (this.mEventLink == null) {
            return jsonObject.toString();
        }
        try {
            jsonObject.put("version", 2);
            jsonObject.put("method", (Object)this.mEventLink.optString("rel"));
            jsonObject.put("url", (Object)this.mEventLink.optString("href"));
            final long currentTimeMillis = System.currentTimeMillis();
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("xid", (Object)this.mXid);
            jsonObject2.put("oxid", (Object)this.mOxid);
            jsonObject2.put("dxid", (Object)this.mDxid);
            jsonObject2.put("movieId", (Object)this.mPlayableId);
            jsonObject2.put("clientTime", currentTimeMillis);
            jsonObject2.put("appid", (Object)this.mAppSessionId);
            jsonObject2.put("sessionid", (Object)this.mUserSessionId);
            jsonObject2.put("isInBackground", !NetflixApplication.isActivityVisible());
            jsonObject2.put("trackid", this.mTrackId);
            jsonObject2.put("startPosition", this.mStartPositionMs);
            jsonObject2.put("position", this.mEndPositionMs);
            jsonObject2.put("sessionStartEpoch", this.mSessionStartTimeMs);
            jsonObject2.put("sessionEndEpoch", this.mSessionEndTimeMs);
            if (this.mHasErrors) {
                jsonObject2.put("hasError", this.mHasErrors);
            }
            final JSONObject jsonObject3 = new JSONObject();
            final JSONObject jsonObject4 = new JSONObject();
            jsonObject4.put("trackId", this.mTrackId);
            jsonObject4.put("rank", this.mVideoPos);
            jsonObject4.put("row", this.mListPos);
            if (this.mDc != null) {
                jsonObject4.put("uiDownloadContext", (Object)this.mDc.getJsonObject());
            }
            jsonObject3.put("uiplaycontext", (Object)jsonObject4.toString());
            jsonObject2.put("sessionparams", (Object)jsonObject3);
            if (this.mPlayTimes != null) {
                jsonObject2.put("playTimes", (Object)this.mPlayTimes);
            }
            final JSONArray jsonArray = new JSONArray();
            jsonArray.put((Object)jsonObject2);
            final JSONObject jsonObject5 = new JSONObject();
            jsonObject5.putOpt("stopEvents", (Object)jsonArray);
            jsonObject.put("params", (Object)jsonObject5);
            return jsonObject.toString();
        }
        catch (Exception ex) {
            Log.e(PdsPlayEventParamBuilder.TAG, ex, "error creating pds download event params", new Object[0]);
            return jsonObject.toString();
        }
    }
    
    public PdsPlayEventParamBuilder setBookmarkPosition(final long mStartPositionMs, final long mEndPositionMs) {
        this.mStartPositionMs = mStartPositionMs;
        this.mEndPositionMs = mEndPositionMs;
        return this;
    }
    
    public PdsPlayEventParamBuilder setDownloadContext(final DownloadContext mDc) {
        this.mDc = mDc;
        return this;
    }
    
    public PdsPlayEventParamBuilder setError(final String s, final String s2) {
        this.mHasErrors = StringUtils.isNotEmpty(s);
        return this;
    }
    
    public PdsPlayEventParamBuilder setOfflineIds(final String mOxid, final String mDxid) {
        this.mOxid = mOxid;
        this.mDxid = mDxid;
        return this;
    }
    
    public PdsPlayEventParamBuilder setPlayContext(final PlayContext playContext) {
        if (playContext != null) {
            this.mTrackId = playContext.getTrackId();
            this.mVideoPos = playContext.getVideoPos();
            this.mListPos = playContext.getListPos();
        }
        return this;
    }
    
    public PdsPlayEventParamBuilder setPlayDuration(final long mDurationMs) {
        this.mDurationMs = mDurationMs;
        return this;
    }
    
    public PdsPlayEventParamBuilder setPlayTimes(final JSONObject mPlayTimes) {
        this.mPlayTimes = mPlayTimes;
        return this;
    }
    
    public PdsPlayEventParamBuilder setSessionTimes(final long mSessionStartTimeMs, final long mSessionEndTimeMs) {
        this.mSessionStartTimeMs = mSessionStartTimeMs;
        this.mSessionEndTimeMs = mSessionEndTimeMs;
        return this;
    }
}
