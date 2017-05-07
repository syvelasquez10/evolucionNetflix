// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.customerSupport.model;

import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.customerSupport.HelpRequestSession;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$Action;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public class HelpRequestSessionEndedEvent extends SessionEndedEvent
{
    public static final String ACTION = "action";
    public static final String ENTRY = "entry";
    public static final String ERROR = "error";
    public static final String REASON = "reason";
    private static final String SESSION_NAME = "helpRequest";
    public static final String URL = "url";
    private CustomerServiceLogging$Action mAction;
    private IClientLogging$CompletionReason mCompletionReason;
    private CustomerServiceLogging$EntryPoint mEntry;
    private Error mError;
    private String mUrl;
    
    public HelpRequestSessionEndedEvent(final HelpRequestSession helpRequestSession, final CustomerServiceLogging$EntryPoint mEntry, final CustomerServiceLogging$Action mAction, final String mUrl, final IClientLogging$CompletionReason mCompletionReason, final Error mError) {
        super("helpRequest", helpRequestSession.getId(), System.currentTimeMillis() - helpRequestSession.getStarted());
        this.mEntry = mEntry;
        this.mAction = mAction;
        this.mUrl = mUrl;
        this.mCompletionReason = mCompletionReason;
        this.mError = mError;
        this.category = helpRequestSession.getCategory();
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        if (this.mAction != null) {
            data.put("action", (Object)this.mAction.name());
        }
        if (this.mEntry != null) {
            data.put("entry", (Object)this.mEntry.name());
        }
        if (StringUtils.isNotEmpty(this.mUrl)) {
            data.put("url", (Object)this.mUrl);
        }
        if (this.mCompletionReason != null) {
            data.put("reason", (Object)this.mCompletionReason.name());
        }
        if (this.mError != null) {
            data.put("error", (Object)this.mError.toJSONObject());
        }
        return data;
    }
}
