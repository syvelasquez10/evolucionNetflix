// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import org.json.JSONArray;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.media.AudioSource;

public class NccpAudioSource extends AudioSource
{
    public static final int IMPL_VALUE = 1;
    private static final String TRACK_TYPE_ASSISTIVE = "ASSISTIVE";
    private static final String TRACK_TYPE_COMMENTARY = "COMMENTARY";
    private static final String TRACK_TYPE_PRIMARY = "PRIMARY";
    
    protected NccpAudioSource(final JSONObject jsonObject, int i) {
        final int n = 0;
        this.id = JsonUtils.getString(jsonObject, "id", null);
        this.numChannels = JsonUtils.getInt(jsonObject, "channels", 0);
        this.languageCodeIso639_1 = JsonUtils.getString(jsonObject, "language", "en");
        this.languageDescription = JsonUtils.getString(jsonObject, "languageDescription", "English");
        this.nccpOrderNumber = i;
        final String string = JsonUtils.getString(jsonObject, "trackType", null);
        this.isNative = JsonUtils.getBoolean(jsonObject, "isNative", true);
        if ("ASSISTIVE".equalsIgnoreCase(string)) {
            this.trackType = 2;
        }
        else if ("COMMENTARY".equalsIgnoreCase(string)) {
            this.trackType = 1;
        }
        else if ("PRIMARY".equalsIgnoreCase(string)) {
            this.trackType = 0;
        }
        else {
            this.trackType = -1;
        }
        final JSONArray jsonArray = JsonUtils.getJSONArray(jsonObject, "disallowedSubtitleTracks");
        if (jsonArray != null) {
            Log.d("nf_audio_source", "DisallowedSubtitleTracks found: " + jsonArray.length());
            this.disallowedSubtitles = new String[jsonArray.length()];
            for (i = n; i < jsonArray.length(); ++i) {
                this.disallowedSubtitles[i] = jsonArray.getString(i);
            }
        }
        else {
            Log.d("nf_audio_source", "No disallowedSubtitleTracks!");
            this.disallowedSubtitles = new String[0];
        }
    }
    
    public static AudioSource newInstance(final JSONObject jsonObject, final int n) {
        return new NccpAudioSource(jsonObject, n);
    }
    
    @Override
    public JSONObject toJson() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("impl", 1);
        jsonObject.put("id", (Object)this.id);
        jsonObject.put("languageDescription", (Object)this.languageDescription);
        jsonObject.put("order", this.nccpOrderNumber);
        jsonObject.put("channels", this.numChannels);
        jsonObject.put("language", (Object)this.languageCodeIso639_1);
        jsonObject.put("languageDescription", (Object)this.languageDescription);
        jsonObject.put("isNative", this.isNative);
        Object o = null;
        if (this.trackType == 2) {
            o = "ASSISTIVE";
        }
        else if (this.trackType == 1) {
            o = "COMMENTARY";
        }
        else if (this.trackType == 0) {
            o = "PRIMARY";
        }
        jsonObject.put("trackType", o);
        if (this.disallowedSubtitles != null && this.disallowedSubtitles.length > 0) {
            final JSONArray jsonArray = new JSONArray();
            jsonObject.put("disallowedSubtitleTracks", (Object)jsonArray);
            for (int i = 0; i < this.disallowedSubtitles.length; ++i) {
                jsonArray.put((Object)this.disallowedSubtitles[i]);
            }
        }
        return jsonObject;
    }
}
