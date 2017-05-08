// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.offline;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.logging.logblob.BaseLogblob;

public abstract class OfflineBaseLogblob extends BaseLogblob
{
    public OfflineBaseLogblob(final String s, final String s2) {
        this.mJson.put("playbackoffline", true);
        if (StringUtils.isNotEmpty(s)) {
            this.mJson.put("oxid", (Object)s);
        }
        if (StringUtils.isNotEmpty(s2)) {
            this.mJson.put("dxid", (Object)s2);
        }
    }
}
