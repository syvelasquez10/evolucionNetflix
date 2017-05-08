// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.media.TrickplayUrl;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class TrickPlayDownloadableInfo implements DownloadableInfo
{
    private final List<CdnUrl> mCdnUrls;
    private final String mDownloadableId;
    private final long mSize;
    
    private TrickPlayDownloadableInfo(final List<CdnUrl> list, final String mDownloadableId, final long mSize) {
        (this.mCdnUrls = new ArrayList<CdnUrl>()).addAll(list);
        CdnUrl.sortByRank(this.mCdnUrls);
        this.mDownloadableId = mDownloadableId;
        this.mSize = mSize;
    }
    
    public static TrickPlayDownloadableInfo create(final TrickplayUrl trickplayUrl) {
        if (trickplayUrl != null && trickplayUrl.getDownloadableSize() > 0L) {
            final ArrayList<CdnUrl> list = new ArrayList<CdnUrl>();
            final String[] url = trickplayUrl.getUrl();
            for (int length = url.length, i = 0; i < length; ++i) {
                final String s = url[i];
                if (StringUtils.isNotEmpty(s)) {
                    list.add(new CdnUrl(s, 1, 1L));
                }
            }
            if (list.size() > 0) {
                return new TrickPlayDownloadableInfo(list, trickplayUrl.getDownloadableId(), trickplayUrl.getDownloadableSize());
            }
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
        return DownloadableType.TrickPlay;
    }
    
    @Override
    public long getSizeOfDownloadable() {
        return this.mSize;
    }
}
