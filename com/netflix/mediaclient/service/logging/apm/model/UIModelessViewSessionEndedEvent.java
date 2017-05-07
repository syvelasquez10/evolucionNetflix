// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public final class UIModelessViewSessionEndedEvent extends SessionEndedEvent
{
    public static final String MODELESS_VIEW = "modelessView";
    private static final String UI_SESSION_NAME = "uiModelessView";
    private IClientLogging.ModalView modelessView;
    
    public UIModelessViewSessionEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging.ModalView modalView) {
        super("uiModelessView", deviceUniqueId, n);
        this.modalView = modalView;
        this.modelessView = modalView;
    }
    
    public UIModelessViewSessionEndedEvent(final SessionStartedEvent sessionStartedEvent, final long n, final IClientLogging.ModalView modalView) {
        super(sessionStartedEvent, n);
        this.modalView = modalView;
        this.modelessView = modalView;
    }
    
    public UIModelessViewSessionEndedEvent(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        jsonObject = JsonUtils.getJSONObject(jsonObject, "data", null);
        if (jsonObject != null) {
            final String string = JsonUtils.getString(jsonObject, "modelessView", null);
            if (string != null) {
                this.modelessView = IClientLogging.ModalView.valueOf(string);
            }
        }
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        if (this.modelessView != null) {
            data.put("modelessView", (Object)this.modelessView.name());
        }
        return data;
    }
    
    public IClientLogging.ModalView getModelessView() {
        return this.modelessView;
    }
}
