// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.netflix.mediaclient.media.manifest.Stream;
import java.util.List;

public class VideoDownloadableInfo extends StreamBasedDownloadableInfo
{
    private VideoDownloadableInfo(final List<CdnUrl> list, final Long n, final String s) {
        super(list, n, s, DownloadableType.Video);
    }
    
    public static VideoDownloadableInfo create(final Stream stream) {
        final List<CdnUrl> buildCdnUrls = StreamBasedDownloadableInfo.buildCdnUrls(stream);
        if (buildCdnUrls != null) {
            return new VideoDownloadableInfo(buildCdnUrls, stream.size, stream.downloadable_id);
        }
        return null;
    }
}
