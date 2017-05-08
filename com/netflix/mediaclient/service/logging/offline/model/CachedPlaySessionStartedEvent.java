// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.offline.model;

import org.json.JSONObject;
import com.netflix.mediaclient.util.LogUtils;
import org.json.JSONException;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;

public class CachedPlaySessionStartedEvent extends SessionStartedEvent
{
    private static final String LOGICAL_END = "logicalEnd";
    private static final String LOGICAL_START = "logicalStart";
    private static final String OXID = "oxid";
    private static final String RUNTIME = "runtime";
    private static final String UIA_NAME = "CachedPlay";
    private static final String VIDEOID = "videoId";
    private int logicalEnd;
    private int logicalStart;
    private String oxid;
    private int runtime;
    private String videoId;
    
    public CachedPlaySessionStartedEvent(final String oxid, final String videoId, final int runtime, final int logicalStart, final int logicalEnd) {
        super("CachedPlay");
        this.videoId = videoId;
        this.runtime = runtime;
        this.logicalStart = logicalStart;
        this.logicalEnd = logicalEnd;
        if (StringUtils.isEmpty(oxid)) {
            LogUtils.reportErrorSafely("CachedPlaySessionEndedEvent: oxid is missing!", (Throwable)new JSONException("CachedPlaySessionEndedEvent: oxid is missing!"));
            return;
        }
        this.oxid = oxid;
    }
    
    public CachedPlaySessionStartedEvent(final JSONObject jsonObject) {
        super(jsonObject);
        if (jsonObject != null) {
            this.oxid = jsonObject.optString("oxid", (String)null);
            if (this.oxid == null) {
                LogUtils.reportErrorSafely("CachedPlaySessionEndedEvent: oxid is missing!", (Throwable)new JSONException("CachedPlaySessionEndedEvent: oxid is missing!"));
            }
        }
    }
    
    @Override
    protected JSONObject getData() {
        final JSONObject data = super.getData();
        if (StringUtils.isNotEmpty(this.oxid)) {
            data.put("oxid", (Object)this.oxid);
        }
        if (StringUtils.isNotEmpty(this.videoId)) {
            data.put("videoId", (Object)this.videoId);
        }
        data.put("runtime", this.runtime);
        data.put("logicalStart", this.logicalStart);
        data.put("logicalEnd", this.logicalEnd);
        return data;
    }
}
