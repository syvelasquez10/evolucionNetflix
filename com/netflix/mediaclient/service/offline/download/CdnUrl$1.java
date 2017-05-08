// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import java.util.Comparator;

final class CdnUrl$1 implements Comparator<CdnUrl>
{
    @Override
    public int compare(final CdnUrl cdnUrl, final CdnUrl cdnUrl2) {
        return cdnUrl.mRank - cdnUrl2.mRank;
    }
}
