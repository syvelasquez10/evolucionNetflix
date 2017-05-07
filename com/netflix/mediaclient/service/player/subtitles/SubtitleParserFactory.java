// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.service.player.subtitles.text.TextSubtitleParser;
import com.netflix.mediaclient.service.player.subtitles.image.ImageSubtitleParser;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.event.nrdp.media.SubtitleUrl;
import com.netflix.mediaclient.service.player.PlayerAgent;

public class SubtitleParserFactory
{
    protected static final String TAG = "nf_subtitles";
    
    public static SubtitleParser createParser(final PlayerAgent playerAgent, final SubtitleUrl subtitleUrl, final TextStyle textStyle, final TextStyle textStyle2, final float n, final long n2, final SubtitleParser$DownloadFailedCallback subtitleParser$DownloadFailedCallback) {
        if (subtitleUrl == null) {
            throw new IllegalArgumentException("Metadata object is null!");
        }
        if (subtitleUrl.getProfile() == IMedia$SubtitleProfile.IMAGE) {
            return new ImageSubtitleParser(playerAgent, subtitleUrl, textStyle, textStyle2, n, n2, subtitleParser$DownloadFailedCallback);
        }
        return new TextSubtitleParser(playerAgent, subtitleUrl, textStyle, textStyle2, n, subtitleParser$DownloadFailedCallback);
    }
}
