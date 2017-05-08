// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.media.SubtitleUrl;
import com.netflix.mediaclient.media.SubtitleTrackData;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class SubtitleDownloadableInfo implements DownloadableInfo
{
    private static final String TAG = "nf_subtitleDlInfo";
    private final List<CdnUrl> mCdnUrls;
    private final String mDownloadableId;
    private final long mSize;
    
    private SubtitleDownloadableInfo(final List<CdnUrl> list, final long mSize, final String mDownloadableId) {
        (this.mCdnUrls = new ArrayList<CdnUrl>()).addAll(list);
        CdnUrl.sortByRank(this.mCdnUrls);
        this.mSize = mSize;
        this.mDownloadableId = mDownloadableId;
    }
    
    public static SubtitleDownloadableInfo create(final SubtitleTrackData subtitleTrackData, final List<SubtitleUrl> list) {
        if (list != null) {
            final ArrayList<CdnUrl> list2 = new ArrayList<CdnUrl>();
            final Iterator<SubtitleUrl> iterator = list.iterator();
            String s = null;
            long size = 0L;
            while (iterator.hasNext()) {
                final SubtitleUrl subtitleUrl = iterator.next();
                if (StringUtils.isNotEmpty(subtitleUrl.getDownloadUrl())) {
                    String downloadableId;
                    if (s == null) {
                        downloadableId = subtitleUrl.getDownloadableId();
                    }
                    else {
                        downloadableId = s;
                        if (!s.equals(subtitleUrl.getDownloadableId())) {
                            Log.e("nf_subtitleDlInfo", "subtitleUrl has different downloadableId.. ignore");
                            continue;
                        }
                    }
                    list2.add(new CdnUrl(subtitleUrl.getDownloadUrl(), subtitleTrackData.getRankForCdn(subtitleUrl.getCdnId() + ""), subtitleUrl.getCdnId()));
                    size = subtitleUrl.getSize();
                    s = downloadableId;
                }
            }
            if (list2.size() > 0 && size > 0L) {
                return new SubtitleDownloadableInfo(list2, size, s);
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
        return DownloadableType.Subtitle;
    }
    
    @Override
    public long getSizeOfDownloadable() {
        return this.mSize;
    }
}
