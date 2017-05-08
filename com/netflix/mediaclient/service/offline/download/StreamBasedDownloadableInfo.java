// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.media.manifest.Url;
import com.netflix.mediaclient.media.manifest.Stream;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

abstract class StreamBasedDownloadableInfo implements DownloadableInfo
{
    private final List<CdnUrl> mCdnUrls;
    private final String mDownloadableId;
    private final DownloadableType mDownloadableType;
    private final long mSize;
    
    StreamBasedDownloadableInfo(final List<CdnUrl> list, final long mSize, final String mDownloadableId, final DownloadableType mDownloadableType) {
        (this.mCdnUrls = new ArrayList<CdnUrl>()).addAll(list);
        this.mSize = mSize;
        this.mDownloadableId = mDownloadableId;
        this.mDownloadableType = mDownloadableType;
    }
    
    static List<CdnUrl> buildCdnUrls(final Stream stream) {
        if (stream == null || stream.size <= 0L) {
            return null;
        }
        final ArrayList<CdnUrl> list = new ArrayList<CdnUrl>();
        for (final Url url : stream.urls) {
            if (StringUtils.isNotEmpty(url.url)) {
                final String url2 = url.url;
                int rank;
                if (url.store != null) {
                    rank = url.store.rank;
                }
                else {
                    rank = 1;
                }
                list.add(new CdnUrl(url2, rank, url.cdn_id));
            }
        }
        if (list.size() > 0) {
            return list;
        }
        return null;
    }
    
    @Override
    public List<CdnUrl> getCdnUrls() {
        return this.mCdnUrls;
    }
    
    @Override
    public String getDownloadableId() {
        return this.mDownloadableId;
    }
    
    @Override
    public DownloadableType getDownloadableType() {
        return this.mDownloadableType;
    }
    
    @Override
    public long getSizeOfDownloadable() {
        return this.mSize;
    }
}
