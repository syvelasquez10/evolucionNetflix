// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiview.model;

import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;

public class ModalViewStartedEvent extends SessionStartedEvent
{
    private static final String KEY_VIEW_NAME = "view";
    private static final String UIVIEW_SESSION_NAME = "viewName";
    private IClientLogging$ModalView mViewName;
    
    public ModalViewStartedEvent(final IClientLogging$ModalView mViewName) {
        super("viewName");
        this.mViewName = mViewName;
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        if (this.mViewName != null) {
            data.put("view", (Object)this.mViewName.name());
        }
        return data;
    }
}
