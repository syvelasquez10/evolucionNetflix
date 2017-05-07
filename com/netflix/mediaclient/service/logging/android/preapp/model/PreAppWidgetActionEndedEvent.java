// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.android.preapp.model;

import java.util.Iterator;
import com.netflix.mediaclient.Log;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.service.pservice.logging.PreAppWidgetLogData;
import com.netflix.mediaclient.service.logging.uiaction.model.BaseUIActionSessionEndedEvent;

public class PreAppWidgetActionEndedEvent extends BaseUIActionSessionEndedEvent
{
    private static final String TAG = "nf_preapp_actionEndedEvent";
    boolean isMember;
    private String widgetActionData;
    private String widgetLogData;
    private PreAppWidgetLogData widgetLogObj;
    
    public PreAppWidgetActionEndedEvent(final String s, final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final String widgetLogData, final String widgetActionData) {
        super(s, deviceUniqueId, n, clientLogging$ModalView, userActionLogging$CommandName, clientLogging$CompletionReason, uiError);
        this.widgetLogData = widgetLogData;
        this.widgetActionData = widgetActionData;
        this.widgetLogObj = FalkorParseUtils.getGson().fromJson(widgetLogData, PreAppWidgetLogData.class);
    }
    
    @Override
    protected JSONObject getCustomData() {
        while (true) {
            try {
                final JSONObject jsonObject = new JSONObject(this.widgetLogData);
                JSONObject jsonObject2 = null;
                try {
                    jsonObject2 = new JSONObject(this.widgetActionData);
                    final Iterator keys = jsonObject2.keys();
                    while (keys.hasNext()) {
                        final String s = keys.next();
                        jsonObject.put(s, jsonObject2.get(s));
                    }
                    return jsonObject;
                }
                catch (JSONException ex) {}
                Log.e("nf_preapp_actionEndedEvent", "failed to merge widget data", (Throwable)jsonObject2);
                return jsonObject;
            }
            catch (JSONException jsonObject2) {
                final JSONObject jsonObject = null;
                continue;
            }
            break;
        }
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return this.widgetLogObj.isMember();
    }
}
