// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.social.model;

import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.IClientLogging;

public final class SocialConnectImpressionEvent extends BaseSocialDiscreteEvent
{
    protected static final String NAME = "socialConnectImpression";
    private IClientLogging.ModalView mView;
    
    public SocialConnectImpressionEvent(final IClientLogging.ModalView mView) {
        super("socialConnectImpression");
        this.mView = mView;
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        if (this.mView != null) {
            data.put("view", (Object)this.mView.name());
        }
        return data;
    }
}
