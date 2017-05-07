// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiview.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.Error;

public class ImpressionSessionEndedEvent extends BaseUIViewSessionEndedEvent
{
    protected static final String ERROR = "error";
    protected static final String GUID = "originatingRequestGuid";
    private static final String SESSION_NAME = "impression";
    protected static final String SUCCESS = "success";
    protected static final String VIEW = "view";
    private Error mError;
    private boolean mSuccess;
    private IClientLogging.ModalView mView;
    
    public ImpressionSessionEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging.ModalView mView, final boolean mSuccess, final Error mError) {
        super("impression", deviceUniqueId, n);
        this.mView = mView;
        this.mSuccess = mSuccess;
        this.mError = mError;
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
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
        data.put("originatingRequestGuid", (Object)ConsolidatedLoggingUtils.createGUID());
        data.put("success", this.mSuccess);
        return data;
    }
}
