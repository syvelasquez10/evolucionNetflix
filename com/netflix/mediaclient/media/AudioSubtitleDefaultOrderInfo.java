// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;

public final class AudioSubtitleDefaultOrderInfo implements Comparable<AudioSubtitleDefaultOrderInfo>
{
    private String audioTrackId;
    private int preferenceOrder;
    private String subtitleTrackId;
    
    public AudioSubtitleDefaultOrderInfo(final String audioTrackId, final String subtitleTrackId, final int preferenceOrder) {
        this.audioTrackId = audioTrackId;
        this.subtitleTrackId = subtitleTrackId;
        this.preferenceOrder = preferenceOrder;
    }
    
    public AudioSubtitleDefaultOrderInfo(final JSONObject jsonObject) {
        this.audioTrackId = JsonUtils.getString(jsonObject, "audioTrackId", null);
        this.subtitleTrackId = JsonUtils.getString(jsonObject, "subtitleTrackId", null);
        this.preferenceOrder = JsonUtils.getInt(jsonObject, "preferenceOrder", -1);
    }
    
    public static void dumpLog(final AudioSubtitleDefaultOrderInfo[] array, final String s) {
        if (array != null) {
            if (Log.isLoggable(s, 3)) {
                Log.d(s, "Defalts: " + array.length);
                for (int i = 0; i < array.length; ++i) {
                    Log.d(s, i + " " + array[i]);
                }
            }
        }
        else {
            Log.e(s, "Defaults are null!");
        }
    }
    
    @Override
    public int compareTo(final AudioSubtitleDefaultOrderInfo audioSubtitleDefaultOrderInfo) {
        if (audioSubtitleDefaultOrderInfo != null) {
            if (this.preferenceOrder == audioSubtitleDefaultOrderInfo.preferenceOrder) {
                return 0;
            }
            if (this.preferenceOrder < audioSubtitleDefaultOrderInfo.preferenceOrder) {
                return -1;
            }
        }
        return 1;
    }
    
    public String getAudioTrackId() {
        return this.audioTrackId;
    }
    
    public int getPreferenceOrder() {
        return this.preferenceOrder;
    }
    
    public String getSubtitleTrackId() {
        return this.subtitleTrackId;
    }
    
    @Override
    public String toString() {
        return "AudioSubtitleDefaultOrderInfo [audioTrackId=" + this.audioTrackId + ", subtitleTrackId=" + this.subtitleTrackId + ", preferenceOrder=" + this.preferenceOrder + "]";
    }
}
