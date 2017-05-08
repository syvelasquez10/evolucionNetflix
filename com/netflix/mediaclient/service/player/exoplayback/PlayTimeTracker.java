// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

import com.netflix.mediaclient.util.StringUtils;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

class PlayTimeTracker
{
    String mAdlid;
    Map<String, Integer> mAudioPlayTime;
    private long mMovieTotal;
    Map<String, Integer> mSubtitlePlayTime;
    String mSubtitledlid;
    String mVdlid;
    Map<String, Integer> mVideoPlayTime;
    
    public PlayTimeTracker() {
        this.mMovieTotal = 0L;
        this.mVideoPlayTime = new HashMap<String, Integer>();
        this.mAudioPlayTime = new HashMap<String, Integer>();
        this.mSubtitlePlayTime = new HashMap<String, Integer>();
    }
    
    public static JSONObject getDefaultTimeJson() {
        final JSONObject jsonObject = new JSONObject();
        final JSONArray jsonArray = new JSONArray();
        try {
            jsonObject.put("total", 0);
            jsonObject.put("video", (Object)jsonArray);
            jsonObject.put("audio", (Object)jsonArray);
            return jsonObject;
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return jsonObject;
        }
    }
    
    private JSONArray mapToJsonArray(final Map<String, Integer> map) {
        final JSONArray jsonArray = new JSONArray();
        for (final String s : map.keySet()) {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("downloadableId", (Object)s);
            jsonObject.put("duration", (Object)map.get(s));
            jsonArray.put((Object)jsonObject);
        }
        return jsonArray;
    }
    
    public long getMovieTotalInMs() {
        return this.mMovieTotal;
    }
    
    public JSONObject getPlayTimeJson() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("total", this.mMovieTotal);
            jsonObject.put("video", (Object)this.mapToJsonArray(this.mVideoPlayTime));
            jsonObject.put("audio", (Object)this.mapToJsonArray(this.mAudioPlayTime));
            if (this.mSubtitlePlayTime.size() > 0) {
                jsonObject.put("timedtext", (Object)this.mapToJsonArray(this.mSubtitlePlayTime));
            }
            return jsonObject;
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return jsonObject;
        }
    }
    
    @Override
    public String toString() {
        return "PlayTimeTracker{mSubtitlePlayTime=" + this.mSubtitlePlayTime + ", mAudioPlayTime=" + this.mAudioPlayTime + ", mVideoPlayTime=" + this.mVideoPlayTime + '}';
    }
    
    void updateCurrrentPlayDlids(final String mVdlid, final String mAdlid, final String mSubtitledlid) {
        this.mVdlid = mVdlid;
        this.mAdlid = mAdlid;
        this.mSubtitledlid = mSubtitledlid;
        if (StringUtils.isNotEmpty(mVdlid) && !this.mVideoPlayTime.containsKey(mVdlid)) {
            this.mVideoPlayTime.put(mVdlid, 0);
        }
        if (StringUtils.isNotEmpty(mAdlid) && !this.mAudioPlayTime.containsKey(mAdlid)) {
            this.mAudioPlayTime.put(mAdlid, 0);
        }
        if (StringUtils.isNotEmpty(mSubtitledlid) && !this.mSubtitlePlayTime.containsKey(this.mSubtitledlid)) {
            this.mSubtitlePlayTime.put(this.mSubtitledlid, 0);
        }
    }
    
    void updatePlayTime(final int n) {
        this.mMovieTotal += n;
        this.mVideoPlayTime.put(this.mVdlid, this.mVideoPlayTime.get(this.mVdlid) + n);
        this.mAudioPlayTime.put(this.mAdlid, this.mAudioPlayTime.get(this.mAdlid) + n);
        if (StringUtils.isNotEmpty(this.mSubtitledlid)) {
            this.mSubtitlePlayTime.put(this.mSubtitledlid, this.mSubtitlePlayTime.get(this.mSubtitledlid) + n);
        }
    }
}
