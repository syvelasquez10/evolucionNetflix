// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import java.util.Arrays;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import java.util.List;

public class Language
{
    private static final String JSON_AUDIO_ARRAY = "audio_array";
    private static final String JSON_CURRENT_NCCP_AUDIO_INDEX = "CurrentNccpAudioIndex";
    private static final String JSON_CURRENT_NCCP_SUBTITLE_INDEX = "CurrentNccpSubtitleIndex";
    private static final String JSON_PREVIOUS_NCCP_AUDIO_INDEX = "PreviousNccpAudioIndex";
    private static final String JSON_PREVIOUS_NCCP_SUBTITLE_INDEX = "PreviousNccpSubtitleIndex";
    private static final String JSON_SUBTITLE_ARRAY = "subtitle_array";
    private static final String JSON_SUBTITLE_VISIBLE = "subtitle_visible";
    private static final String TAG = "nf_language";
    private AudioSource[] mAltAudios;
    private int mCurrentNccpAudioIndex;
    private int mCurrentNccpSubtitleIndex;
    private int mPreviousNccpAudioIndex;
    private int mPreviousNccpSubtitleIndex;
    private AudioSource mSelectedAudio;
    private Subtitle mSelectedSubtitle;
    private boolean mSubtitleVisible;
    private Subtitle[] mSubtitles;
    private final List<Subtitle> mUsedSubtitles;
    
    public Language(final AudioSource[] mAltAudios, final int mCurrentNccpAudioIndex, final Subtitle[] mSubtitles, final int mCurrentNccpSubtitleIndex, final boolean mSubtitleVisible) {
        this.mPreviousNccpSubtitleIndex = -1;
        this.mPreviousNccpAudioIndex = 0;
        this.mUsedSubtitles = new ArrayList<Subtitle>();
        if (mAltAudios != null) {
            this.mAltAudios = mAltAudios;
        }
        else {
            this.mAltAudios = new AudioSource[0];
        }
        if (mSubtitles != null) {
            this.mSubtitles = mSubtitles;
        }
        else {
            this.mSubtitles = new Subtitle[0];
        }
        this.mSubtitleVisible = mSubtitleVisible;
        this.mCurrentNccpSubtitleIndex = mCurrentNccpSubtitleIndex;
        this.mCurrentNccpAudioIndex = mCurrentNccpAudioIndex;
    }
    
    private static int countAllowedSubtitles(final Subtitle[] array, final AudioSource audioSource) {
        final boolean b = false;
        int n;
        if (audioSource == null) {
            n = (b ? 1 : 0);
        }
        else {
            n = (b ? 1 : 0);
            if (array != null) {
                n = (b ? 1 : 0);
                if (array.length >= 1) {
                    int n2 = 0;
                    final int length = array.length;
                    int n3 = 0;
                    while (true) {
                        n = n2;
                        if (n3 >= length) {
                            break;
                        }
                        int n4 = n2;
                        if (audioSource.isAllowedSubtitle(array[n3])) {
                            n4 = n2 + 1;
                        }
                        ++n3;
                        n2 = n4;
                    }
                }
            }
        }
        return n;
    }
    
    private static AudioSource getAudioSource(final AudioSource[] array, final int n) {
        if (array == null) {
            Log.e("nf_language", "Audios are null!");
            return null;
        }
        for (int length = array.length, i = 0; i < length; ++i) {
            final AudioSource audioSource = array[i];
            if (Log.isLoggable("nf_language", 3)) {
                Log.d("nf_language", "Testing " + audioSource + " for NCCP order number " + n);
            }
            if (audioSource.getNccpOrderNumber() == n) {
                Log.d("nf_language", "Found!");
                return audioSource;
            }
        }
        if (Log.isLoggable("nf_language", 5)) {
            Log.w("nf_language", "SHould NOT happen! Audio source NOT found for NCCP order number " + n);
        }
        return null;
    }
    
    private AudioSource getAudioSourceByPosition(final int n) {
        if (n < 0 || n >= this.mAltAudios.length) {
            Log.e("nf_language", "getAudioSourceByIndex: position invalid: " + n);
            return null;
        }
        return this.mAltAudios[n];
    }
    
    private static Subtitle getSubtitle(final Subtitle[] array, final int n) {
        if (array == null) {
            Log.e("nf_language", "Subtitles are null!");
            return null;
        }
        for (int length = array.length, i = 0; i < length; ++i) {
            final Subtitle subtitle = array[i];
            if (Log.isLoggable("nf_language", 3)) {
                Log.d("nf_language", "Testing " + subtitle + " for NCCP order number " + n);
            }
            if (subtitle.getNccpOrderNumber() == n) {
                Log.d("nf_language", "Found!");
                return subtitle;
            }
        }
        if (Log.isLoggable("nf_language", 5)) {
            Log.w("nf_language", "Should NOT happen! Subtitle NOT found for NCCP order number " + n);
        }
        return null;
    }
    
    public static Language restoreLanguage(final String s) throws JSONException {
        final JSONObject jsonObject = new JSONObject(s);
        final int int1 = jsonObject.getInt("CurrentNccpSubtitleIndex");
        final int int2 = jsonObject.getInt("CurrentNccpAudioIndex");
        final int int3 = jsonObject.getInt("CurrentNccpSubtitleIndex");
        final int int4 = jsonObject.getInt("CurrentNccpAudioIndex");
        final boolean boolean1 = jsonObject.getBoolean("subtitle_visible");
        final JSONArray optJSONArray = jsonObject.optJSONArray("audio_array");
        final JSONArray optJSONArray2 = jsonObject.optJSONArray("subtitle_array");
        Subtitle[] array = null;
        AudioSource[] array2 = null;
        if (optJSONArray2 != null) {
            final Subtitle[] array3 = new Subtitle[optJSONArray2.length()];
            int n = 0;
            while (true) {
                array = array3;
                if (n >= array3.length) {
                    break;
                }
                array3[n] = Subtitle.restore(optJSONArray2.getJSONObject(n));
                ++n;
            }
        }
        if (optJSONArray != null) {
            final AudioSource[] array4 = new AudioSource[optJSONArray.length()];
            int n2 = 0;
            while (true) {
                array2 = array4;
                if (n2 >= array4.length) {
                    break;
                }
                array4[n2] = AudioSource.restore(optJSONArray.getJSONObject(n2));
                ++n2;
            }
        }
        final Language language = new Language(array2, int2, array, int1, boolean1);
        language.mPreviousNccpAudioIndex = int4;
        language.mPreviousNccpSubtitleIndex = int3;
        return language;
    }
    
    private void updateUsedSubtitles(final AudioSource audioSource) {
        this.mUsedSubtitles.clear();
        if (audioSource == null || audioSource.isAllowedSubtitle(null)) {
            if (Log.isLoggable("nf_language", 3)) {
                Log.d("nf_language", "Subtitle off is allowed for audio " + audioSource + ". Add to list of used subtitles.");
            }
            this.mUsedSubtitles.add(null);
        }
        else if (this.mSubtitles == null || countAllowedSubtitles(this.mSubtitles, audioSource) < 1) {
            if (Log.isLoggable("nf_language", 3)) {
                Log.d("nf_language", "Subtitle off is NOT allowed for audio " + audioSource + ", but there are no other ALLOWED subtitles. NCCP error. Add to subtitle spinner.");
            }
            this.mUsedSubtitles.add(null);
        }
        else if (Log.isLoggable("nf_language", 3)) {
            Log.d("nf_language", "Subtitle off is NOT allowed for audio " + audioSource + ".");
        }
        if (this.mSubtitles != null) {
            final Subtitle[] mSubtitles = this.mSubtitles;
            for (int length = mSubtitles.length, i = 0; i < length; ++i) {
                final Subtitle subtitle = mSubtitles[i];
                if (audioSource == null || audioSource.isAllowedSubtitle(subtitle)) {
                    if (Log.isLoggable("nf_language", 3)) {
                        Log.d("nf_language", subtitle + " is allowed for audio " + audioSource + ". Add to subtitle spinner.");
                    }
                    this.mUsedSubtitles.add(subtitle);
                }
                else if (Log.isLoggable("nf_language", 3)) {
                    Log.d("nf_language", subtitle + " is not allowed for audio " + audioSource + ". Skip.");
                }
            }
        }
    }
    
    public void commit() {
        this.mPreviousNccpAudioIndex = this.mCurrentNccpAudioIndex;
        if (this.mSelectedAudio != null) {
            this.mCurrentNccpAudioIndex = this.mSelectedAudio.getNccpOrderNumber();
        }
        this.mPreviousNccpSubtitleIndex = this.mCurrentNccpSubtitleIndex;
        if (this.mSelectedSubtitle != null) {
            this.mCurrentNccpSubtitleIndex = this.mSelectedSubtitle.getNccpOrderNumber();
            this.mSubtitleVisible = true;
            return;
        }
        this.mCurrentNccpSubtitleIndex = -1;
        this.mSubtitleVisible = false;
    }
    
    public AudioSource[] getAltAudios() {
        return this.mAltAudios;
    }
    
    public AudioSource getCurrentAudioSource() {
        return getAudioSource(this.mAltAudios, this.mCurrentNccpAudioIndex);
    }
    
    public int getCurrentNccpAudioIndex() {
        return this.mCurrentNccpAudioIndex;
    }
    
    public int getCurrentNccpSubtitleIndex() {
        return this.mCurrentNccpSubtitleIndex;
    }
    
    public Subtitle getCurrentSubtitle() {
        if (!this.mSubtitleVisible) {
            return null;
        }
        return getSubtitle(this.mSubtitles, this.mCurrentNccpSubtitleIndex);
    }
    
    public AudioSource getSelectedAudio() {
        if (Log.isLoggable("nf_language", 3)) {
            Log.d("nf_language", "Get selected audio: " + this.mSelectedAudio);
        }
        return this.mSelectedAudio;
    }
    
    public Subtitle getSelectedSubtitle() {
        return this.mSelectedSubtitle;
    }
    
    public Subtitle[] getSubtitles() {
        return this.mSubtitles;
    }
    
    public List<Subtitle> getUsedSubtitles() {
        return this.mUsedSubtitles;
    }
    
    public boolean isLanguageSwitchEnabled() {
        if (Log.isLoggable("nf_language", 2)) {
            final StringBuilder append = new StringBuilder().append("isLanguageSwitchEnabled mSubtitles count: ");
            Serializable value;
            if (this.mSubtitles == null) {
                value = "null";
            }
            else {
                value = this.mSubtitles.length;
            }
            Log.v("nf_language", append.append(value).toString());
        }
        if (this.mSubtitles != null && this.mSubtitles.length > 0) {
            return true;
        }
        if (Log.isLoggable("nf_language", 2)) {
            final StringBuilder append2 = new StringBuilder().append("isLanguageSwitchEnabled mAltAudios count: ");
            Serializable value2;
            if (this.mAltAudios == null) {
                value2 = "null";
            }
            else {
                value2 = this.mAltAudios.length;
            }
            Log.v("nf_language", append2.append(value2).toString());
        }
        return this.mAltAudios != null && this.mAltAudios.length > 1;
    }
    
    public boolean isSubtitleVisible() {
        return this.mSubtitleVisible;
    }
    
    public void restorePreviousAudio() {
        if (this.mPreviousNccpAudioIndex < 0) {
            Log.e("nf_language", "This should not happen!");
            return;
        }
        final AudioSource audioSource = getAudioSource(this.getAltAudios(), this.mPreviousNccpAudioIndex);
        if (Log.isLoggable("nf_language", 3)) {
            Log.d("nf_language", "Restore to previous audio in UI: " + audioSource);
        }
        if (this.setSelectedAudio(audioSource) == null) {
            Log.w("nf_language", "Unable to restore to previous audio. Not allowed!");
            return;
        }
        this.mPreviousNccpAudioIndex = 0;
    }
    
    public void restorePreviousSubtitle() {
        Subtitle subtitle = null;
        if (this.mPreviousNccpSubtitleIndex > -1) {
            subtitle = getSubtitle(this.getSubtitles(), this.mPreviousNccpSubtitleIndex);
        }
        if (Log.isLoggable("nf_language", 3)) {
            Log.d("nf_language", "Restore to previous subtitle in UI: " + subtitle);
        }
        this.mSelectedSubtitle = subtitle;
        this.mPreviousNccpSubtitleIndex = -1;
    }
    
    public AudioSource setSelectedAudio(final AudioSource mSelectedAudio) {
        if (Log.isLoggable("nf_language", 3)) {
            Log.d("nf_language", "Set selected audio: " + mSelectedAudio);
        }
        this.updateUsedSubtitles(this.mSelectedAudio = mSelectedAudio);
        return this.mSelectedAudio;
    }
    
    public Subtitle setSelectedSubtitle(final Subtitle mSelectedSubtitle) {
        if (Log.isLoggable("nf_language", 3)) {
            Log.d("nf_language", "Sets selected subtitle " + mSelectedSubtitle);
        }
        for (int i = 0; i < this.mUsedSubtitles.size(); ++i) {
            if (this.mUsedSubtitles.get(i) == null && mSelectedSubtitle == null) {
                return this.mSelectedSubtitle = null;
            }
            if (this.mUsedSubtitles.get(i) == mSelectedSubtitle) {
                return this.mSelectedSubtitle = mSelectedSubtitle;
            }
        }
        Log.w("nf_language", "We tried to select subtitle that is NOT in list of used subtitles!");
        return null;
    }
    
    public String toJsonString() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("CurrentNccpAudioIndex", this.mCurrentNccpAudioIndex);
        jsonObject.put("CurrentNccpSubtitleIndex", this.mCurrentNccpSubtitleIndex);
        jsonObject.put("PreviousNccpAudioIndex", this.mPreviousNccpAudioIndex);
        jsonObject.put("PreviousNccpSubtitleIndex", this.mPreviousNccpSubtitleIndex);
        jsonObject.put("subtitle_visible", this.mSubtitleVisible);
        if (this.mSubtitles != null && this.mSubtitles.length > 0) {
            final JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < this.mSubtitles.length; ++i) {
                jsonArray.put((Object)this.mSubtitles[i].toJson());
            }
            jsonObject.put("subtitle_array", (Object)jsonArray);
        }
        if (this.mAltAudios != null && this.mAltAudios.length > 0) {
            final JSONArray jsonArray2 = new JSONArray();
            for (int j = 0; j < this.mAltAudios.length; ++j) {
                jsonArray2.put((Object)this.mAltAudios[j].toJson());
            }
            jsonObject.put("audio_array", (Object)jsonArray2);
        }
        return jsonObject.toString();
    }
    
    @Override
    public String toString() {
        return "Language [mSubtitles=" + Arrays.toString(this.mSubtitles) + ", mAltAudios=" + Arrays.toString(this.mAltAudios) + ", mSubtitleVisible=" + this.mSubtitleVisible + ", mCurrentNccpSubtitleIndex=" + this.mCurrentNccpSubtitleIndex + ", mCurrentNccpAudioIndex=" + this.mCurrentNccpAudioIndex + ", mPreviousNccpSubtitleIndex=" + this.mPreviousNccpSubtitleIndex + ", mPreviousNccpAudioIndex=" + this.mPreviousNccpAudioIndex + ", mUsedSubtitles=" + this.mUsedSubtitles + ", mSelectedAudio=" + this.mSelectedAudio + ", mSelectedSubtitle=" + this.mSelectedSubtitle + "]";
    }
}
