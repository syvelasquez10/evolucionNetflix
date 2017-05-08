// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.subtitles;

import com.netflix.mediaclient.service.player.subtitles.SubtitleParser$DownloadFailedCallback;
import com.netflix.mediaclient.ui.offline.OfflineImageSubtitle;
import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleProfile;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.ui.offline.OfflineSubtitle;
import com.netflix.mediaclient.servicemgr.IPlayer;

public class SubtitleParserFactory
{
    protected static final String TAG = "nf_subtitles";
    
    public static OfflineSubtitleParser createParser(final IPlayer player, final OfflineSubtitle offlineSubtitle, final TextStyle textStyle, final TextStyle textStyle2, final float n, final long n2) {
        if (offlineSubtitle == null) {
            throw new IllegalArgumentException("Metadata object is null!");
        }
        final ISubtitleDef$SubtitleProfile profile = offlineSubtitle.getProfile();
        if (profile == ISubtitleDef$SubtitleProfile.IMAGE) {
            return new OfflineImageSubtitleParser(player, (OfflineImageSubtitle)offlineSubtitle, n2, null, 0L);
        }
        if (profile == ISubtitleDef$SubtitleProfile.ENHANCED_ENC || profile == ISubtitleDef$SubtitleProfile.SIMPLE_ENC) {
            return new OfflineEncryptedTextSubtitleParser(player, offlineSubtitle, textStyle, textStyle2, n);
        }
        if (profile == ISubtitleDef$SubtitleProfile.ENHANCED || profile == ISubtitleDef$SubtitleProfile.SIMPLE) {
            return new OfflineTextSubtitleParser(player, offlineSubtitle, textStyle, textStyle2, n);
        }
        if (profile == ISubtitleDef$SubtitleProfile.IMAGE_ENC) {
            return new OfflineImageV2SubtitleParser(player, (OfflineImageSubtitle)offlineSubtitle, n2, null, 0L);
        }
        throw new IllegalArgumentException("Not supported profile: " + profile);
    }
}
