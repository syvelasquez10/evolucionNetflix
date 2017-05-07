// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.customerSupport.model;

import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$Action;
import com.netflix.mediaclient.service.logging.client.model.DiscreteEvent;

public class DialScreenDismissedEvent extends DiscreteEvent
{
    public static final String ACTION = "action";
    protected static final String CATEGORY = "customerSupport";
    protected static final String NAME = "dialScreenDismissed";
    private CustomerServiceLogging$Action mAction;
    
    public DialScreenDismissedEvent(final CustomerServiceLogging$Action mAction) {
        this.mAction = mAction;
        this.category = "customerSupport";
        this.name = "dialScreenDismissed";
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
        return data;
    }
}
