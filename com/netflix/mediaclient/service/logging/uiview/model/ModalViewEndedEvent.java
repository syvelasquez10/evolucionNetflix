// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiview.model;

import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;

public class ModalViewEndedEvent extends BaseUIViewSessionEndedEvent
{
    private static final String KEY_VIEW_IS_MODAL = "isModal";
    private static final String KEY_VIEW_NAME = "view";
    private static final String UIVIEW_SESSION_NAME = "viewName";
    private boolean mIsModal;
    private IClientLogging$ModalView mViewName;
    
    public ModalViewEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$ModalView mViewName, final boolean mIsModal) {
        super("viewName", deviceUniqueId, n);
        this.mIsModal = false;
        this.mViewName = mViewName;
        this.mIsModal = mIsModal;
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
        data.put("isModal", this.mIsModal);
        return data;
    }
}
