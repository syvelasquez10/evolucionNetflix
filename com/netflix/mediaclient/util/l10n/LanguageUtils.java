// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.l10n;

import com.netflix.mediaclient.util.LanguageChoice;
import com.netflix.mediaclient.media.Language;
import org.json.JSONObject;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import com.netflix.mediaclient.media.AudioSource;

public final class LanguageUtils
{
    private static final String PROPERTY_AUDIO_LANGUAGE_CODE_ISO639_1 = "audioLanguageCodeIso639_1";
    private static final String PROPERTY_AUDIO_TRACK_TYPE = "audioTrackType";
    private static final String PROPERTY_SUBTITLE_LANGUAGE_CODE_ISO639_1 = "subtitleLanguageCodeIso639_1";
    private static final String PROPERTY_SUBTITLE_TRACK_TYPE = "subtitleTrackType";
    private static final String PROPERTY_TIMESTAMP = "timestamp";
    private static final String TAG = "nf_loc";
    
    private static AudioSource findAudioById(final AudioSource[] array, final String s) {
        for (int length = array.length, i = 0; i < length; ++i) {
            final AudioSource audioSource = array[i];
            if (audioSource != null && audioSource.getId() != null && audioSource.getId().equals(s)) {
                return audioSource;
            }
        }
        return null;
    }
    
    private static AudioSource findAudioByOrderInfo(final AudioSource[] array, final AudioSubtitleDefaultOrderInfo[] array2) {
        if (array2 == null || array2.length < 1) {
            Log.w("nf_loc", "Default order info in manifest does not found, this should not happen!");
            return null;
        }
        return findAudioById(array, array2[0].getAudioTrackId());
    }
    
    private static AudioSource findAudioForUserOverride(final LanguageUtils$SelectedLanguage languageUtils$SelectedLanguage, final AudioSource[] array, final AudioSubtitleDefaultOrderInfo[] array2, final long n) {
        if (Log.isLoggable()) {
            Log.d("nf_loc", "findAudioForUserOverride:: user selection was language: " + languageUtils$SelectedLanguage.audioLanguageCodeIso639_1 + ", track type: " + languageUtils$SelectedLanguage.audioTrackType);
        }
        AudioSource audioByOrderInfo;
        if (StringUtils.isEmpty(languageUtils$SelectedLanguage.audioLanguageCodeIso639_1)) {
            Log.w("nf_loc", "Audio was not selected, use manifest override...");
            audioByOrderInfo = findAudioByOrderInfo(array, array2);
        }
        else {
            AudioSource audioSource = null;
            final int length = array.length;
            int i = 0;
        Label_0191_Outer:
            while (i < length) {
                final AudioSource audioSource2 = array[i];
                if (languageUtils$SelectedLanguage.audioLanguageCodeIso639_1.equals(audioSource2.getLanguageCodeIso639_1())) {
                    if (Log.isLoggable()) {
                        Log.d("nf_loc", "findAudioForUserOverride:: match found: " + audioSource2);
                    }
                    if (audioSource2.getTrackType() == languageUtils$SelectedLanguage.audioTrackType) {
                        audioByOrderInfo = audioSource2;
                        if (Log.isLoggable()) {
                            Log.d("nf_loc", "findAudioForUserOverride:: exact match, use it!");
                            return audioSource2;
                        }
                        return audioByOrderInfo;
                    }
                    else if (audioSource == null) {
                        Log.d("nf_loc", "No default audio before, save this one.");
                        audioSource = audioSource2;
                    }
                    else if (audioSource2.getTrackType() == 0) {
                        Log.d("nf_loc", "Default audio track found, save this one.");
                        audioSource = audioSource2;
                    }
                }
                while (true) {
                    ++i;
                    continue Label_0191_Outer;
                    continue;
                }
            }
            if (audioSource == null) {
                Log.d("nf_loc", "Same audio as one for user override is not found, use one supplied by manifest.");
                return findAudioByOrderInfo(array, array2);
            }
            if (Log.isLoggable()) {
                Log.d("nf_loc", "Use default audio that matches user override " + audioSource);
            }
            return audioSource;
        }
        return audioByOrderInfo;
    }
    
    private static Subtitle findSubtitleById(final Subtitle[] array, final String s) {
        for (int length = array.length, i = 0; i < length; ++i) {
            final Subtitle subtitle = array[i];
            if (subtitle != null && subtitle.getId() != null && subtitle.getId().equals(s)) {
                return subtitle;
            }
        }
        return null;
    }
    
    private static Subtitle findSubtitleByOrderInfo(final Subtitle[] array, final AudioSubtitleDefaultOrderInfo[] array2) {
        if (array2 == null || array2.length < 1) {
            Log.w("nf_loc", "Default order info in manifest does not found, this should not happen!");
            return null;
        }
        return findSubtitleById(array, array2[0].getSubtitleTrackId());
    }
    
    private static Subtitle findSubtitleForUserOverride(final LanguageUtils$SelectedLanguage languageUtils$SelectedLanguage, final Subtitle[] array, final AudioSource audioSource, final AudioSubtitleDefaultOrderInfo[] array2, final long n) {
        if (Log.isLoggable()) {
            Log.d("nf_loc", "findSubtitleForUserOverride:: user selection was language: " + languageUtils$SelectedLanguage.subtitleLanguageCodeIso639_1 + ", track type: " + languageUtils$SelectedLanguage.subtitleTrackType);
        }
        if (!StringUtils.isEmpty(languageUtils$SelectedLanguage.subtitleLanguageCodeIso639_1)) {
            final int length = array.length;
            int i = 0;
            Subtitle subtitle = null;
            while (i < length) {
                final Subtitle subtitle2 = array[i];
                Subtitle subtitle3 = subtitle;
                if (languageUtils$SelectedLanguage.subtitleLanguageCodeIso639_1.equals(subtitle2.getLanguageCodeIso639_1())) {
                    if (Log.isLoggable()) {
                        Log.d("nf_loc", "findSubtitleForUserOverride:: match found: " + subtitle2);
                    }
                    if (subtitle2.getTrackType() == languageUtils$SelectedLanguage.subtitleTrackType) {
                        Log.d("nf_loc", "findSubtitleForUserOverride:: exact match, use it if it is allowed!");
                        if (audioSource.isAllowedSubtitle(subtitle2)) {
                            Log.d("nf_loc", "findSubtitleForUserOverride:: exact match, it is allowed, use it!");
                            return subtitle2;
                        }
                        Log.w("nf_loc", "findSubtitleForUserOverride:: exact match, it is NOT allowed, skip it!");
                        subtitle3 = subtitle;
                    }
                    else if (subtitle == null) {
                        Log.d("nf_loc", "No default subtitle before, save this one.");
                        subtitle3 = subtitle2;
                    }
                    else {
                        subtitle3 = subtitle;
                        if (subtitle2.getTrackType() == 1) {
                            Log.d("nf_loc", "Default subtitle track found, save this one.");
                            subtitle3 = subtitle2;
                        }
                    }
                }
                ++i;
                subtitle = subtitle3;
            }
            if (subtitle != null) {
                if (Log.isLoggable()) {
                    Log.d("nf_loc", "findSubtitleForUserOverride:: default subtitle found, check if it is allowed: " + subtitle);
                }
                if (audioSource.isAllowedSubtitle(subtitle)) {
                    Log.d("nf_loc", "findSubtitleForUserOverride:: default subtitle match, it is allowed, use it!");
                    return subtitle;
                }
                Log.w("nf_loc", "findSubtitleForUserOverride:: default subtitle match, it is NOT allowed, skip it!");
            }
            Log.d("nf_loc", "Same subtitle as one for user override is not found, use one supplied by manifest.");
            return findSubtitleByOrderInfo(array, array2);
        }
        Log.w("nf_loc", "Subtitle is off...");
        if (audioSource.isAllowedSubtitle(null)) {
            Log.d("nf_loc", "findSubtitleForUserOverride:: off subtitle is allowed, use it.");
            return null;
        }
        Log.d("nf_loc", "findSubtitleForUserOverride:: off subtitle is NOT allowed, use manifest default");
        return findSubtitleByOrderInfo(array, array2);
    }
    
    public static LanguageUtils$SelectedLanguage getSelectedLanguage(final Context context) {
        if (context != null) {
            final String stringPref = PreferenceUtils.getStringPref(context, "prefs_user_selected_language", null);
            if (!StringUtils.isEmpty(stringPref)) {
                try {
                    return new LanguageUtils$SelectedLanguage(new JSONObject(stringPref));
                }
                catch (Throwable t) {
                    Log.e("nf_loc", t, "Unable to load used selection!", new Object[0]);
                    return null;
                }
            }
        }
        return null;
    }
    
    public static void saveUserChoice(final Context context, final Language language) {
        if (language == null || context == null) {
            return;
        }
        final LanguageUtils$SelectedLanguage languageUtils$SelectedLanguage = new LanguageUtils$SelectedLanguage(language);
        try {
            PreferenceUtils.putStringPref(context, "prefs_user_selected_language", languageUtils$SelectedLanguage.toJson().toString());
        }
        catch (Throwable t) {
            Log.e("nf_loc", t, "Unable to save use selection!", new Object[0]);
        }
    }
    
    public static LanguageChoice toLanguageChoice(final LanguageUtils$SelectedLanguage languageUtils$SelectedLanguage, final Subtitle[] array, final AudioSource[] array2, final AudioSubtitleDefaultOrderInfo[] array3) {
        if (languageUtils$SelectedLanguage == null) {
            return null;
        }
        long creationTimeInMs = 0L;
        final long timestamp = languageUtils$SelectedLanguage.timestamp;
        if (array3 == null || array3.length < 1) {
            Log.w("nf_loc", "No defaults! User override");
        }
        else {
            creationTimeInMs = array3[0].getCreationTimeInMs();
            Log.d("nf_loc", "Manifest creation date %d", creationTimeInMs);
        }
        Log.d("nf_loc", "Last user override %d", timestamp);
        if (creationTimeInMs > timestamp) {
            Log.d("nf_loc", "Manifest defaults are newer than last user selection, use them...");
            return null;
        }
        Log.d("nf_loc", "Manifest defaults are older than last user selection, use user overrides...");
        final AudioSource audioForUserOverride = findAudioForUserOverride(languageUtils$SelectedLanguage, array2, array3, creationTimeInMs);
        return new LanguageChoice(findSubtitleForUserOverride(languageUtils$SelectedLanguage, array, audioForUserOverride, array3, creationTimeInMs), audioForUserOverride);
    }
}
