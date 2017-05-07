// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.event.nrdp.media.SubtitleUrl;

public interface SubtitleParser$DownloadFailedCallback
{
    boolean downloadFailed(final SubtitleUrl p0);
}
