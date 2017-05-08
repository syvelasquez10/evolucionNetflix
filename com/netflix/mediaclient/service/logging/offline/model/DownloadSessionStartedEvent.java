// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.offline.model;

import org.json.JSONObject;
import com.netflix.mediaclient.util.LogUtils;
import org.json.JSONException;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;

public final class DownloadSessionStartedEvent extends SessionStartedEvent
{
    private static final String DXID = "dxid";
    private static final String UIA_NAME = "Download";
    private String dxid;
    
    public DownloadSessionStartedEvent(final String dxid) {
        super("Download");
        if (StringUtils.isEmpty(dxid)) {
            LogUtils.reportErrorSafely("DownloadSessionStartedEvent: dxid is missing!", (Throwable)new JSONException("DownloadSessionStartedEvent: dxid is missing!"));
            return;
        }
        this.dxid = dxid;
    }
    
    public DownloadSessionStartedEvent(final JSONObject jsonObject) {
        super(jsonObject);
        if (jsonObject != null) {
            this.dxid = jsonObject.optString("dxid", (String)null);
            if (this.dxid == null) {
                LogUtils.reportErrorSafely("CachedPlaySessionEndedEvent: oxid is missing!", (Throwable)new JSONException("CachedPlaySessionEndedEvent: oxid is missing!"));
            }
        }
    }
    
    @Override
    protected JSONObject getData() {
        final JSONObject data = super.getData();
        if (StringUtils.isNotEmpty(this.dxid)) {
            data.put("dxid", (Object)this.dxid);
        }
        return data;
    }
}
