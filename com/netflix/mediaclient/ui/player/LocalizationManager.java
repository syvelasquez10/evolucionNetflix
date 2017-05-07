// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;
import java.util.Arrays;
import com.netflix.mediaclient.util.LanguageChoice;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import com.netflix.mediaclient.media.AudioSource;

public class LocalizationManager
{
    private static final String TAG = "nf-l10n";
    private AudioSource[] audioSources;
    private AudioSubtitleDefaultOrderInfo[] mDefaults;
    private Subtitle[] subtitles;
    private AudioSource userChoiceAudio;
    private Subtitle userChoiceSubtitle;
    
    public LocalizationManager(final Subtitle[] subtitles, final AudioSource[] audioSources, final AudioSubtitleDefaultOrderInfo[] mDefaults, final LanguageChoice languageChoice) {
        AudioSource.dumpLog(audioSources, "nf-l10n");
        Subtitle.dumpLog(subtitles, "nf-l10n");
        AudioSubtitleDefaultOrderInfo.dumpLog(mDefaults, "nf-l10n");
        if (subtitles == null) {
            this.subtitles = new Subtitle[0];
        }
        else {
            Arrays.sort(subtitles);
            Subtitle.dumpLog(subtitles, "nf-l10n");
            this.subtitles = subtitles;
        }
        if (audioSources == null) {
            this.audioSources = new AudioSource[0];
        }
        else {
            Arrays.sort(audioSources);
            AudioSource.dumpLog(audioSources, "nf-l10n");
            this.audioSources = audioSources;
        }
        if (mDefaults == null) {
            this.mDefaults = new AudioSubtitleDefaultOrderInfo[0];
        }
        else {
            Arrays.sort(mDefaults);
            AudioSubtitleDefaultOrderInfo.dumpLog(mDefaults, "nf-l10n");
            this.mDefaults = mDefaults;
        }
        if (Log.isLoggable()) {
            Log.d("nf-l10n", "User choice for language was " + languageChoice);
        }
        if (languageChoice == null) {
            Log.d("nf-l10n", "User choice for audio AND subtitle did not existed!");
            return;
        }
        if (languageChoice.getSubtitle() != null) {
            this.userChoiceSubtitle = this.getSubtitleById(languageChoice.getSubtitle().getId());
            if (Log.isLoggable()) {
                Log.d("nf-l10n", "User choice for subtitle was " + languageChoice.getSubtitle().getId() + ". In movie medatadata we found match: " + this.userChoiceSubtitle);
            }
        }
        else {
            Log.d("nf-l10n", "User choice for subtitle did not existed!");
        }
        if (languageChoice.getAudio() != null) {
            this.userChoiceAudio = this.getAudioSourceById(languageChoice.getAudio().getId());
            if (Log.isLoggable()) {
                Log.d("nf-l10n", "User choice for audio was " + languageChoice.getAudio().getId() + ". In movie medatadata we found match: " + this.userChoiceAudio);
            }
            return;
        }
        Log.d("nf-l10n", "User choice for audio did not existed!");
    }
    
    private AudioSource findInitialAudio() {
        AudioSource audioSource = null;
        if (this.audioSources.length < 1) {
            Log.w("nf-l10n", "No audio source found!");
        }
        else {
            if (this.mDefaults.length < 1) {
                Log.d("nf-l10n", "No defaults found. Return null to keep initial audio source.");
                return null;
            }
            final String audioTrackId = this.mDefaults[0].getAudioTrackId();
            if (audioTrackId == null) {
                Log.e("nf-l10n", "Audio source track id is NULL for default: " + this.mDefaults[0]);
                return null;
            }
            final AudioSource audioSourceById = this.getAudioSourceById(audioTrackId);
            if (audioSourceById == null) {
                Log.e("nf-l10n", "Default exist: " + audioTrackId + ", but source with it not found!");
                return audioSourceById;
            }
            audioSource = audioSourceById;
            if (Log.isLoggable()) {
                Log.d("nf-l10n", "Initial audio source defined by defauls: " + audioSourceById);
                return audioSourceById;
            }
        }
        return audioSource;
    }
    
    private Subtitle findInitialSubtitle() {
        Subtitle subtitle = null;
        if (this.subtitles.length >= 1) {
            if (this.mDefaults.length < 1) {
                Log.w("nf-l10n", "No defaults found. No subtitles.");
                return null;
            }
            final String subtitleTrackId = this.mDefaults[0].getSubtitleTrackId();
            if (subtitleTrackId == null || "none".equalsIgnoreCase(subtitleTrackId) || "".equals(subtitleTrackId)) {
                if (Log.isLoggable()) {
                    Log.d("nf-l10n", "Subtitle track id is NULL for default, no subtitles: " + this.mDefaults[0]);
                    return null;
                }
            }
            else {
                final Subtitle subtitleById = this.getSubtitleById(subtitleTrackId);
                if (subtitleById == null) {
                    Log.e("nf-l10n", "Default exist: " + subtitleTrackId + ", but soubtitle with it not found!");
                    return subtitleById;
                }
                subtitle = subtitleById;
                if (Log.isLoggable()) {
                    Log.d("nf-l10n", "Initial subtitle defined by defauls: " + subtitleById);
                    return subtitleById;
                }
            }
        }
        return subtitle;
    }
    
    private AudioSource getAudioSourceById(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("Audio track id can NOT be null!");
        }
        for (int i = 0; i < this.audioSources.length; ++i) {
            if (s.equals(this.audioSources[i].getId())) {
                return this.audioSources[i];
            }
        }
        return null;
    }
    
    private Subtitle getSubtitleById(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("Subtitle id can NOT be null!");
        }
        for (int i = 0; i < this.subtitles.length; ++i) {
            if (s.equals(this.subtitles[i].getId())) {
                return this.subtitles[i];
            }
        }
        return null;
    }
    
    public LanguageChoice findInitialLanguage() {
        if (this.userChoiceSubtitle != null) {
            Log.d("nf-l10n", "We found user preference for subtitle!");
        }
        if (this.userChoiceAudio != null) {
            if (Log.isLoggable()) {
                Log.d("nf-l10n", "We found user preference for audio: " + this.userChoiceAudio);
            }
            if (this.userChoiceSubtitle != null) {
                if (Log.isLoggable()) {
                    Log.d("nf-l10n", "We found user preference for subtitle: " + this.userChoiceSubtitle);
                }
            }
            else {
                Log.d("nf-l10n", "No user preferences for subtitle.");
            }
            if (this.userChoiceAudio.isAllowedSubtitle(this.userChoiceSubtitle)) {
                Log.d("nf-l10n", "Using user preference for language");
                return new LanguageChoice(this.userChoiceSubtitle, this.userChoiceAudio);
            }
            Log.d("nf-l10n", "Using user preference is not allowed, go for NCCP default");
            return new LanguageChoice(this.findInitialSubtitle(), this.findInitialAudio());
        }
        else {
            Log.d("nf-l10n", "No user preference for audio!");
            final AudioSource initialAudio = this.findInitialAudio();
            if (initialAudio == null) {
                Log.e("nf-l10n", "Initial audio not found!");
                return new LanguageChoice(null, null);
            }
            if (this.userChoiceSubtitle == null) {
                Log.d("nf-l10n", "No user preferences for audio and subtitle. Use NCCP defaults.");
                return new LanguageChoice(this.findInitialSubtitle(), initialAudio);
            }
            if (Log.isLoggable()) {
                Log.d("nf-l10n", "We found user preference for subtitle: " + this.userChoiceSubtitle);
            }
            if (initialAudio.isAllowedSubtitle(this.userChoiceSubtitle)) {
                Log.d("nf-l10n", "Using user preference for language");
                return new LanguageChoice(this.userChoiceSubtitle, initialAudio);
            }
            Log.d("nf-l10n", "Using user preference is not allowed, go for NCCP default");
            return new LanguageChoice(this.findInitialSubtitle(), initialAudio);
        }
    }
    
    public AudioSource[] getAudioSources() {
        return this.audioSources;
    }
    
    public Subtitle[] getSubtitles() {
        return this.subtitles;
    }
}
