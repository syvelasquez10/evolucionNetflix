// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleProfile;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.media.SubtitleUrl;
import com.netflix.mediaclient.service.player.PlayerAgent;

public class SubtitleParserFactory
{
    protected static final String TAG = "nf_subtitles";
    
    public static SubtitleParser createParser(final PlayerAgent playerAgent, final SubtitleUrl subtitleUrl, final TextStyle textStyle, final TextStyle textStyle2, final float n, final long n2, final SubtitleParser$DownloadFailedCallback subtitleParser$DownloadFailedCallback, final long n3) {
        if (subtitleUrl == null) {
            throw new IllegalArgumentException("Metadata object is null!");
        }
        final ISubtitleDef$SubtitleProfile profile = subtitleUrl.getProfile();
        if (profile == ISubtitleDef$SubtitleProfile.IMAGE) {
            return new ImageSubtitleParser(playerAgent, subtitleUrl, n2, subtitleParser$DownloadFailedCallback, n3);
        }
        if (profile == ISubtitleDef$SubtitleProfile.ENHANCED_ENC || profile == ISubtitleDef$SubtitleProfile.SIMPLE_ENC) {
            return new StreamingEncryptedTextSubtitleParser(playerAgent, subtitleUrl, textStyle, textStyle2, n, subtitleParser$DownloadFailedCallback, n3);
        }
        if (profile == ISubtitleDef$SubtitleProfile.ENHANCED || profile == ISubtitleDef$SubtitleProfile.SIMPLE) {
            return new StreamingTextSubtitleParser(playerAgent, subtitleUrl, textStyle, textStyle2, n, subtitleParser$DownloadFailedCallback, n3);
        }
        if (profile == ISubtitleDef$SubtitleProfile.IMAGE_ENC) {
            return new ImageV2SubtitleParser(playerAgent, subtitleUrl, n2, subtitleParser$DownloadFailedCallback, n3);
        }
        throw new IllegalArgumentException("Not supported profile: " + profile);
    }
}
