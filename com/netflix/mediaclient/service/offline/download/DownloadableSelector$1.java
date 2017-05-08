// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleProfile;
import java.util.Comparator;

final class DownloadableSelector$1 implements Comparator<ISubtitleDef$SubtitleProfile>
{
    @Override
    public int compare(final ISubtitleDef$SubtitleProfile subtitleDef$SubtitleProfile, final ISubtitleDef$SubtitleProfile subtitleDef$SubtitleProfile2) {
        return subtitleDef$SubtitleProfile2.getValue() - subtitleDef$SubtitleProfile.getValue();
    }
}
