// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;

public final class UIModelessViewSessionStartedEvent extends SessionStartedEvent
{
    public static final String MODELESS_VIEW = "modelessView";
    public static final String ORIENTATION = "orientation";
    private static final String UI_SESSION_NAME = "uiModelessView";
    private IClientLogging.ModalView modelessView;
    private Orientation orientation;
    
    public UIModelessViewSessionStartedEvent(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        jsonObject = JsonUtils.getJSONObject(jsonObject, "data", null);
        if (jsonObject != null) {
            final String string = JsonUtils.getString(jsonObject, "orientation", null);
            if (string != null) {
                this.orientation = Orientation.valueOf(string);
            }
            else {
                this.orientation = Orientation.portrait;
            }
            final String string2 = JsonUtils.getString(jsonObject, "modelessView", null);
            if (string2 != null) {
                this.modelessView = IClientLogging.ModalView.valueOf(string2);
            }
        }
    }
    
    public UIModelessViewSessionStartedEvent(final boolean b, final IClientLogging.ModalView modalView) {
        super("uiModelessView");
        Orientation orientation;
        if (b) {
            orientation = Orientation.portrait;
        }
        else {
            orientation = Orientation.landscape;
        }
        this.orientation = orientation;
        this.modalView = modalView;
        this.modelessView = modalView;
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        data.put("orientation", (Object)this.orientation.name());
        if (this.modelessView != null) {
            data.put("modelessView", (Object)this.modelessView.name());
        }
        return data;
    }
    
    public IClientLogging.ModalView getModelessView() {
        return this.modelessView;
    }
    
    public Orientation getOrientation() {
        return this.orientation;
    }
}
