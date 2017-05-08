// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.event.nrdp.media.SubtitleUrl;
import com.netflix.mediaclient.service.player.PlayerAgent;

public class SubtitleParserFactory
{
    protected static final String TAG = "nf_subtitles";
    
    public static SubtitleParser createParser(final PlayerAgent playerAgent, final SubtitleUrl subtitleUrl, final TextStyle textStyle, final TextStyle textStyle2, final float n, final long n2, final SubtitleParser$DownloadFailedCallback subtitleParser$DownloadFailedCallback, final long n3) {
        if (subtitleUrl == null) {
            throw new IllegalArgumentException("Metadata object is null!");
        }
        final IMedia$SubtitleProfile profile = subtitleUrl.getProfile();
        if (profile == IMedia$SubtitleProfile.IMAGE) {
            return new ImageSubtitleParser(playerAgent, subtitleUrl, n2, subtitleParser$DownloadFailedCallback, n3);
        }
        if (profile == IMedia$SubtitleProfile.ENHANCED_ENC || profile == IMedia$SubtitleProfile.SIMPLE_ENC) {
            return new EncryptedTextSubtitleParser(playerAgent, subtitleUrl, textStyle, textStyle2, n, subtitleParser$DownloadFailedCallback, n3);
        }
        if (profile == IMedia$SubtitleProfile.ENHANCED || profile == IMedia$SubtitleProfile.SIMPLE) {
            return new TextSubtitleParser(playerAgent, subtitleUrl, textStyle, textStyle2, n, subtitleParser$DownloadFailedCallback, n3);
        }
        if (profile == IMedia$SubtitleProfile.IMAGE_ENC) {
            return new ImageV2SubtitleParser(playerAgent, subtitleUrl, n2, subtitleParser$DownloadFailedCallback, n3);
        }
        throw new IllegalArgumentException("Not supported profile: " + profile);
    }
}
