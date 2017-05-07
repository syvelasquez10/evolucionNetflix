// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import java.util.Arrays;
import com.netflix.mediaclient.media.Subtitle;
import org.json.JSONArray;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.media.AudioSource;

public final class MdxAudioSource extends AudioSource
{
    protected static final String ATTR_LABEL = "label";
    public static final int IMPL_VALUE = 2;
    private boolean mAvailable;
    private final boolean mSelected;
    private boolean mSupported;
    
    protected MdxAudioSource(final JSONObject jsonObject, int i) {
        final int n = 0;
        this.id = JsonUtils.getString(jsonObject, "id", null);
        this.languageDescription = JsonUtils.getString(jsonObject, "label", "English");
        this.mSelected = JsonUtils.getBoolean(jsonObject, "selected", false);
        this.nccpOrderNumber = i;
        this.isNative = true;
        this.trackType = 0;
        final JSONArray jsonArray = JsonUtils.getJSONArray(jsonObject, "disallowedSubtitleTracks");
        if (jsonArray != null) {
            if (Log.isLoggable("nf_audio_source", 3)) {
                Log.d("nf_audio_source", "DisallowedSubtitleTracks found: " + jsonArray.length());
            }
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
    
    public static MdxAudioSource newInstance(final JSONObject jsonObject, final int n) {
        return new MdxAudioSource(jsonObject, n);
    }
    
    @Override
    public boolean isAllowedSubtitle(final Subtitle subtitle) {
        return subtitle != null && super.isAllowedSubtitle(subtitle);
    }
    
    public boolean isAvailable() {
        return this.mAvailable;
    }
    
    public boolean isSelected() {
        return this.mSelected;
    }
    
    public boolean isSupported() {
        return this.mSupported;
    }
    
    @Override
    public JSONObject toJson() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("impl", 2);
        jsonObject.put("id", (Object)this.id);
        jsonObject.put("label", (Object)this.languageDescription);
        jsonObject.put("order", this.nccpOrderNumber);
        jsonObject.put("selected", this.mSelected);
        if (this.disallowedSubtitles != null && this.disallowedSubtitles.length > 0) {
            final JSONArray jsonArray = new JSONArray();
            jsonObject.put("disallowedSubtitleTracks", (Object)jsonArray);
            for (int i = 0; i < this.disallowedSubtitles.length; ++i) {
                jsonArray.put((Object)this.disallowedSubtitles[i]);
            }
        }
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "MdxAudioSource [mSelected=" + this.mSelected + ", mSupported=" + this.mSupported + ", mAvailable=" + this.mAvailable + ", id=" + this.id + ", languageCodeIso639_1=" + this.languageCodeIso639_1 + ", languageCodeIso639_2=" + this.languageCodeIso639_2 + ", languageDescription=" + this.languageDescription + ", trackType=" + this.trackType + ", codecType=" + this.codecType + ", isNative=" + this.isNative + ", numChannels=" + this.numChannels + ", nccpOrderNumber=" + this.nccpOrderNumber + ", disallowedSubtitles=" + Arrays.toString(this.disallowedSubtitles) + "]";
    }
}
