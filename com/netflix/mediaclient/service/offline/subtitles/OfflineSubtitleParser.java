// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.subtitles;

import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.service.player.subtitles.SubtitleParser;

public interface OfflineSubtitleParser extends SubtitleParser
{
    Subtitle getCurrentSubtitle();
}
