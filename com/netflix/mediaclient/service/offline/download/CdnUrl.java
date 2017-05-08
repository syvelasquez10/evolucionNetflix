// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import java.util.Comparator;
import java.util.Collections;
import java.util.List;

public class CdnUrl
{
    public final long mCdnId;
    public final int mRank;
    public final String mUrl;
    
    public CdnUrl(final String mUrl, final int mRank, final long mCdnId) {
        this.mUrl = mUrl;
        this.mRank = mRank;
        this.mCdnId = mCdnId;
    }
    
    public static void sortByRank(final List<CdnUrl> list) {
        Collections.sort((List<Object>)list, (Comparator<? super Object>)new CdnUrl$1());
    }
}
