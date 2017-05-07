// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiview.model;

import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.Error;

public class ImpressionSessionEndedEvent extends BaseUIViewSessionEndedEvent
{
    protected static final String ERROR = "error";
    protected static final String GUID = "originatingRequestGuid";
    protected static final String MODEL = "model";
    private static final String SESSION_NAME = "impression";
    protected static final String SUCCESS = "success";
    protected static final String VIEW = "view";
    private Error mError;
    private JSONObject mModel;
    private String mOriginatingRequestGuid;
    private boolean mSuccess;
    private IClientLogging$ModalView mView;
    
    public ImpressionSessionEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$ModalView mView, final String mOriginatingRequestGuid, final boolean mSuccess, final Error mError) {
        super("impression", deviceUniqueId, n);
        this.mView = mView;
        this.mOriginatingRequestGuid = mOriginatingRequestGuid;
        this.mSuccess = mSuccess;
        this.mError = mError;
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        if (this.mView != null) {
            data.put("view", (Object)this.mView);
        }
        if (this.mError != null) {
            data.put("error", (Object)this.mError.toJSONObject());
        }
        if (this.mOriginatingRequestGuid != null) {
            data.put("originatingRequestGuid", (Object)this.mOriginatingRequestGuid);
        }
        if (this.mModel != null) {
            data.put("model", (Object)this.mModel);
        }
        data.put("success", this.mSuccess);
        return data;
    }
    
    public void setModel(final JSONObject mModel) {
        this.mModel = mModel;
    }
}
