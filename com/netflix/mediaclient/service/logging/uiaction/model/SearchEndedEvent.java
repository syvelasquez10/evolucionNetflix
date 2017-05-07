// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;

public final class SearchEndedEvent extends BaseUIActionSessionEndedEvent
{
    public static final String TERM = "term";
    private static final String UIA_NAME = "search";
    private String mTerm;
    
    public SearchEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging.ModalView modalView, final UserActionLogging.CommandName commandName, final IClientLogging.CompletionReason completionReason, final UIError uiError, final String mTerm) {
        super("search", deviceUniqueId, n, modalView, commandName, completionReason, uiError);
        this.mTerm = mTerm;
    }
    
    public SearchEndedEvent(final JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        this.mTerm = JsonUtils.getString(jsonObject, "term", null);
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        final JSONObject data = super.getData();
        if (this.mTerm != null) {
            data.put("term", (Object)this.mTerm);
        }
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
