// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.service.player.subtitles.text.TextSubtitleParser;
import com.netflix.mediaclient.service.player.subtitles.image.ImageSubtitleParser;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.event.nrdp.media.SubtitleData;
import com.netflix.mediaclient.service.player.PlayerAgent;

public class SubtitleParserFactory
{
    protected static final String TAG = "nf_subtitles";
    
    public static SubtitleParser createParser(final PlayerAgent playerAgent, final SubtitleData subtitleData, final TextStyle textStyle, final TextStyle textStyle2, final float n, final long n2) {
        if (subtitleData == null) {
            throw new IllegalArgumentException("Metadata object is null!");
        }
        if (subtitleData.getProfile() == IMedia$SubtitleProfile.IMAGE) {
            return new ImageSubtitleParser(playerAgent, subtitleData, textStyle, textStyle2, n, n2);
        }
        return new TextSubtitleParser(playerAgent, subtitleData, textStyle, textStyle2, n);
    }
}
