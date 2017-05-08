// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.util.SubtitleUtils;
import com.netflix.mediaclient.event.nrdp.media.SubtitleUrl;
import com.netflix.mediaclient.service.player.PlayerAgent;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;

abstract class BaseTextSubtitleParser extends BaseSubtitleParser
{
    protected final TextStyle mDefault;
    protected final TextStyle mDeviceDefault;
    protected final TextStyle mRegionDefault;
    protected final TextStyle mUserDefaults;
    protected final float mVideoAspectRatio;
    
    public BaseTextSubtitleParser(final PlayerAgent playerAgent, final SubtitleUrl subtitleUrl, final TextStyle mUserDefaults, final TextStyle mRegionDefault, final float mVideoAspectRatio, final SubtitleParser$DownloadFailedCallback subtitleParser$DownloadFailedCallback, final long n) {
        super(playerAgent, subtitleUrl, subtitleParser$DownloadFailedCallback, n);
        this.mDefault = new TextStyle();
        this.mVideoAspectRatio = mVideoAspectRatio;
        this.mUserDefaults = mUserDefaults;
        this.mRegionDefault = mRegionDefault;
        this.mDeviceDefault = SubtitleUtils.getDeviceDefaultTextStyle(mUserDefaults, mRegionDefault);
    }
}
