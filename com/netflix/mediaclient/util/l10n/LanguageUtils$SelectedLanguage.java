// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.l10n;

import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.Language;

public class LanguageUtils$SelectedLanguage
{
    public String audioLanguageCodeIso639_1;
    public int audioTrackType;
    public String subtitleLanguageCodeIso639_1;
    public int subtitleTrackType;
    public long timestamp;
    
    public LanguageUtils$SelectedLanguage(final Language language) {
        if (language == null) {
            throw new IllegalArgumentException("Selected language is null!");
        }
        this.timestamp = System.currentTimeMillis();
        final AudioSource selectedAudio = language.getSelectedAudio();
        if (selectedAudio == null) {
            throw new IllegalArgumentException("Selected audio is null!");
        }
        this.audioLanguageCodeIso639_1 = selectedAudio.getLanguageCodeIso639_1();
        this.audioTrackType = selectedAudio.getTrackType();
        final Subtitle selectedSubtitle = language.getSelectedSubtitle();
        if (selectedSubtitle != null) {
            this.subtitleLanguageCodeIso639_1 = selectedSubtitle.getLanguageCodeIso639_1();
            this.subtitleTrackType = selectedSubtitle.getTrackType();
        }
    }
    
    public LanguageUtils$SelectedLanguage(final String audioLanguageCodeIso639_1, final int audioTrackType, final String subtitleLanguageCodeIso639_1, final int subtitleTrackType) {
        if (StringUtils.isEmpty(audioLanguageCodeIso639_1)) {
            throw new IllegalArgumentException("Audio must be selected!");
        }
        this.timestamp = System.currentTimeMillis();
        this.audioLanguageCodeIso639_1 = audioLanguageCodeIso639_1;
        this.audioTrackType = audioTrackType;
        this.subtitleLanguageCodeIso639_1 = subtitleLanguageCodeIso639_1;
        this.subtitleTrackType = subtitleTrackType;
    }
    
    public LanguageUtils$SelectedLanguage(final JSONObject jsonObject) {
        if (jsonObject == null) {
            throw new IllegalArgumentException("JSON is null!");
        }
        this.audioLanguageCodeIso639_1 = jsonObject.getString("audioLanguageCodeIso639_1");
        this.audioTrackType = jsonObject.getInt("audioTrackType");
        this.subtitleLanguageCodeIso639_1 = jsonObject.optString("subtitleLanguageCodeIso639_1", (String)null);
        this.subtitleTrackType = jsonObject.optInt("subtitleTrackType", 0);
        this.timestamp = jsonObject.getLong("timestamp");
    }
    
    public JSONObject toJson() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("timestamp", this.timestamp);
        jsonObject.put("audioLanguageCodeIso639_1", (Object)this.audioLanguageCodeIso639_1);
        jsonObject.put("audioTrackType", this.audioTrackType);
        if (StringUtils.isNotEmpty(this.subtitleLanguageCodeIso639_1)) {
            jsonObject.put("subtitleLanguageCodeIso639_1", (Object)this.subtitleLanguageCodeIso639_1);
            jsonObject.put("subtitleTrackType", this.subtitleTrackType);
        }
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "SelectedLanguage{timestamp=" + this.timestamp + ", audioLanguageCodeIso639_1='" + this.audioLanguageCodeIso639_1 + '\'' + ", audioTrackType=" + this.audioTrackType + ", subtitleLanguageCodeIso639_1='" + this.subtitleLanguageCodeIso639_1 + '\'' + ", subtitleTrackType=" + this.subtitleTrackType + '}';
    }
}
