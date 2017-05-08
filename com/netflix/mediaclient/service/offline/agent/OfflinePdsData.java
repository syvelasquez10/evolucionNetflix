// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import org.json.JSONObject;
import com.netflix.mediaclient.service.pdslogging.DownloadContext;

public class OfflinePdsData
{
    private final DownloadContext mDownloadContext;
    private final String mDxId;
    private final JSONObject mLinks;
    private final String mOxId;
    
    public OfflinePdsData(final JSONObject mLinks, final String mOxId, final String mDxId, final DownloadContext mDownloadContext) {
        this.mLinks = mLinks;
        this.mOxId = mOxId;
        this.mDxId = mDxId;
        this.mDownloadContext = mDownloadContext;
    }
    
    public DownloadContext getDownloadContext() {
        return this.mDownloadContext;
    }
    
    public String getDxId() {
        return this.mDxId;
    }
    
    public JSONObject getLinks() {
        return this.mLinks;
    }
    
    public String getOxId() {
        return this.mOxId;
    }
}
