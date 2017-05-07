// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.service.logging.client.model.HttpResponse;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public final class UIDataRequestSessionEndedEvent extends SessionEndedEvent
{
    public static final String ERROR = "error";
    public static final String REASON = "reason";
    public static final String REQUEST_ID = "requestId";
    public static final String RESPONSE = "response";
    private static final String UI_DATA_NAME = "uiDataRequest";
    public static final String URL = "url";
    private Error error;
    private IClientLogging.CompletionReason reason;
    private String requestId;
    private HttpResponse response;
    private String url;
    
    public UIDataRequestSessionEndedEvent(final long n) {
        super("uiDataRequest", new DeviceUniqueId(), n);
    }
    
    public UIDataRequestSessionEndedEvent(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        jsonObject = JsonUtils.getJSONObject(jsonObject, "data", null);
        if (jsonObject != null) {
            final String string = JsonUtils.getString(jsonObject, "reason", null);
            if (string != null) {
                this.reason = Enum.valueOf(IClientLogging.CompletionReason.class, string);
            }
            this.url = JsonUtils.getString(jsonObject, "url", null);
            this.error = Error.createInstance(JsonUtils.getJSONObject(jsonObject, "error", null));
            this.response = HttpResponse.createInstance(JsonUtils.getJSONObject(jsonObject, "response", null));
            this.requestId = JsonUtils.getString(jsonObject, "requestId", null);
        }
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        if (this.url != null) {
            data.put("url", (Object)this.url);
        }
        if (this.error != null) {
            data.put("error", (Object)this.error.toJSONObject());
        }
        if (this.response != null) {
            data.put("response", (Object)this.response.toJSONObject());
        }
        if (this.reason != null) {
            data.put("reason", (Object)this.reason.name());
        }
        if (this.requestId != null) {
            data.put("requestId", (Object)this.requestId);
        }
        return data;
    }
    
    public Error getError() {
        return this.error;
    }
    
    public IClientLogging.CompletionReason getReason() {
        return this.reason;
    }
    
    public String getRequestId() {
        return this.requestId;
    }
    
    public HttpResponse getResponse() {
        return this.response;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setError(final Error error) {
        this.error = error;
    }
    
    public void setReason(final IClientLogging.CompletionReason reason) {
        this.reason = reason;
    }
    
    public void setRequestId(final String requestId) {
        this.requestId = requestId;
    }
    
    public void setResponse(final HttpResponse response) {
        this.response = response;
    }
    
    public void setUrl(final String url) {
        this.url = url;
    }
    
    @Override
    public String toString() {
        return "UIDataRequestSessionEndedEvent [url=" + this.url + ", requestId=" + this.requestId + ", error=" + this.error + ", response=" + this.response + ", reason=" + this.reason + "]";
    }
}
