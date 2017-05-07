// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.media.AudioSource;

public final class LanguageChoice
{
    private AudioSource audio;
    private Subtitle subtitle;
    
    public LanguageChoice(final Subtitle subtitle, final AudioSource audio) {
        this.subtitle = subtitle;
        this.audio = audio;
    }
    
    public AudioSource getAudio() {
        return this.audio;
    }
    
    public Subtitle getSubtitle() {
        return this.subtitle;
    }
    
    @Override
    public String toString() {
        return "LanguageChoice [subtitle=" + this.subtitle + ", audio=" + this.audio + "]";
    }
}
