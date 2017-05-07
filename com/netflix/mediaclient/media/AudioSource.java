// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import org.json.JSONException;
import com.netflix.mediaclient.ui.player.NccpAudioSource;
import com.netflix.mediaclient.ui.mdx.MdxAudioSource;
import org.json.JSONObject;
import com.netflix.mediaclient.Log;

public abstract class AudioSource implements Comparable<AudioSource>
{
    public static final int ASSISTIVE_AUDIO = 2;
    protected static final String ATTR_DISSALOWED_TIMED_TEXT_TRACK = "disallowedSubtitleTracks";
    protected static final String ATTR_ID = "id";
    protected static final String ATTR_IS_NATIVE = "isNative";
    protected static final String ATTR_LANGUAGE = "language";
    protected static final String ATTR_LANGUAGE_DESCRIPTION = "languageDescription";
    protected static final String ATTR_NUM_CHANNELS = "channels";
    protected static final String ATTR_ORDER = "order";
    protected static final String ATTR_SELECTED = "selected";
    protected static final String ATTR_TRACK_TYPE = "trackType";
    public static final int COMMENTARY_AUDIO = 1;
    public static final int DDPLUS_AUDIO = 4;
    public static final int DOLBY_AC3_AUDIO = 1;
    public static final int HEAAC_AUDIO = 3;
    protected static final String IMPL = "impl";
    protected static final String NONE = "none";
    public static final int OGG_VORBIS_AUDIO = 2;
    public static final int PRIMARY_AUDIO = 0;
    protected static final String TAG = "nf_audio_source";
    public static final int UNKNOWN_AUDIO = 3;
    public static final int UNKNOWN_AUDIO_CODEC = -1;
    public static final int WMA_AUDIO = 0;
    protected int codecType;
    protected String[] disallowedSubtitles;
    protected String id;
    protected boolean isNative;
    protected String languageCodeIso639_1;
    protected String languageCodeIso639_2;
    protected String languageDescription;
    protected int nccpOrderNumber;
    protected int numChannels;
    protected int trackType;
    
    public static void dumpLog(final AudioSource[] array, final String s) {
        if (array != null) {
            if (Log.isLoggable()) {
                Log.d(s, "Audios: " + array.length);
                for (int i = 0; i < array.length; ++i) {
                    Log.d(s, i + " " + array[i]);
                }
            }
        }
        else {
            Log.e(s, "Audios are null!");
        }
    }
    
    public static AudioSource restore(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        final int optInt = jsonObject.optInt("impl", -1);
        if (optInt == 2) {
            return MdxAudioSource.newInstance(jsonObject, jsonObject.getInt("order"));
        }
        if (optInt == 1) {
            return NccpAudioSource.newInstance(jsonObject, jsonObject.getInt("order"));
        }
        throw new JSONException("Implementation does not support restore " + optInt);
    }
    
    @Override
    public int compareTo(final AudioSource audioSource) {
        final boolean b = true;
        if (audioSource != null) {
            if (this.trackType > audioSource.trackType) {
                return 1;
            }
            if (this.trackType >= audioSource.trackType && this.languageDescription != null) {
                if (audioSource.languageDescription == null) {
                    return 1;
                }
                int n;
                if ((n = String.CASE_INSENSITIVE_ORDER.compare(this.languageDescription, audioSource.languageDescription)) == 0 && (n = this.languageDescription.compareTo(audioSource.languageDescription)) == 0) {
                    if (this.numChannels < audioSource.numChannels) {
                        n = (b ? 1 : 0);
                    }
                    else if (this.numChannels == audioSource.numChannels) {
                        n = 0;
                    }
                    else {
                        n = -1;
                    }
                }
                return n;
            }
        }
        return -1;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof AudioSource)) {
                return false;
            }
            if (this.nccpOrderNumber != ((AudioSource)o).nccpOrderNumber) {
                return false;
            }
        }
        return true;
    }
    
    public int getCodecType() {
        return this.codecType;
    }
    
    public String[] getDisallowedSubtitles() {
        return this.disallowedSubtitles;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getLanguageCodeIso639_1() {
        return this.languageCodeIso639_1;
    }
    
    public String getLanguageCodeIso639_2() {
        return this.languageCodeIso639_2;
    }
    
    public String getLanguageDescription() {
        return this.languageDescription;
    }
    
    public String getLanguageDescriptionDisplayLabel() {
        if (this.numChannels == 6) {
            return this.languageDescription + " (5.1)";
        }
        return this.languageDescription;
    }
    
    public int getNccpOrderNumber() {
        return this.nccpOrderNumber;
    }
    
    public int getNumChannels() {
        return this.numChannels;
    }
    
    public int getTrackType() {
        return this.trackType;
    }
    
    @Override
    public int hashCode() {
        return this.nccpOrderNumber + 31;
    }
    
    public boolean isAllowedSubtitle(final Subtitle subtitle) {
        final boolean b = false;
        if (this.disallowedSubtitles.length >= 1) {
            final String s = "none";
            String id;
            if (subtitle == null) {
                Log.d("nf_audio_source", "Checking if subtitle off is allowed");
                id = s;
            }
            else {
                Log.d("nf_audio_source", "Checking if subtitle is allowed");
                id = subtitle.getId();
            }
            for (int i = 0; i < this.disallowedSubtitles.length; ++i) {
                if (this.disallowedSubtitles[i] != null) {
                    final boolean b2 = b;
                    if (this.disallowedSubtitles[i].equalsIgnoreCase(id)) {
                        return b2;
                    }
                }
            }
            return true;
        }
        return true;
    }
    
    public boolean isNative() {
        return this.isNative;
    }
    
    public abstract JSONObject toJson();
    
    @Override
    public String toString() {
        return "AudioSource [id=" + this.id + ", languageCodeIso639_1=" + this.languageCodeIso639_1 + ", languageCodeIso639_2=" + this.languageCodeIso639_2 + ", languageDescription=" + this.languageDescription + ", trackType=" + this.trackType + ", codecType=" + this.codecType + ", isNative=" + this.isNative + ", numChannels=" + this.numChannels + ", dissalowed subtitles # " + this.disallowedSubtitles.length + ", nccpOrderNumber=" + this.nccpOrderNumber + "]";
    }
}
