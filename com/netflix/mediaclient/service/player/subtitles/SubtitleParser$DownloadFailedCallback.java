// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleFailure;
import com.netflix.mediaclient.media.SubtitleUrl;

public interface SubtitleParser$DownloadFailedCallback
{
    boolean downloadFailed(final SubtitleUrl p0, final ISubtitleDef$SubtitleFailure p1, final String p2);
}
