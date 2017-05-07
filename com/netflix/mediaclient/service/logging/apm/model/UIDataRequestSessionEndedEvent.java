// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONArray;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import java.util.ArrayList;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.service.logging.client.model.HttpResponse;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.FalcorPathResult;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public final class UIDataRequestSessionEndedEvent extends SessionEndedEvent
{
    public static final String ERROR = "error";
    public static final String FALCOR_PATH_RESULTS = "falcorPathResults";
    public static final String REASON = "reason";
    public static final String REQUEST_ID = "requestId";
    public static final String RESPONSE = "response";
    private static final String UI_DATA_NAME = "uiDataRequest";
    public static final String URL = "url";
    private Error error;
    private List<FalcorPathResult> falcorPathResults;
    private IClientLogging.CompletionReason reason;
    private String requestId;
    private HttpResponse response;
    private String url;
    
    public UIDataRequestSessionEndedEvent(final long n) {
        super("uiDataRequest", new DeviceUniqueId(), n);
        this.falcorPathResults = new ArrayList<FalcorPathResult>();
    }
    
    public UIDataRequestSessionEndedEvent(final JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        this.falcorPathResults = new ArrayList<FalcorPathResult>();
        final JSONObject jsonObject2 = JsonUtils.getJSONObject(jsonObject, "data", null);
        if (jsonObject2 != null) {
            final String string = JsonUtils.getString(jsonObject2, "reason", null);
            if (string != null) {
                this.reason = Enum.valueOf(IClientLogging.CompletionReason.class, string);
            }
            this.url = JsonUtils.getString(jsonObject2, "url", null);
            this.error = Error.createInstance(JsonUtils.getJSONObject(jsonObject2, "error", null));
            this.response = HttpResponse.createInstance(JsonUtils.getJSONObject(jsonObject2, "response", null));
            this.requestId = JsonUtils.getString(jsonObject2, "requestId", null);
            final JSONArray jsonArray = JsonUtils.getJSONArray(jsonObject2, "falcorPathResults");
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); ++i) {
                    final FalcorPathResult instance = FalcorPathResult.createInstance(jsonObject);
                    if (instance != null) {
                        this.falcorPathResults.add(instance);
                    }
                }
            }
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
        if (this.falcorPathResults != null) {
            final JSONArray jsonArray = new JSONArray();
            data.put("falcorPathResults", (Object)jsonArray);
            final Iterator<FalcorPathResult> iterator = this.falcorPathResults.iterator();
            while (iterator.hasNext()) {
                jsonArray.put((Object)iterator.next().toJSONObject());
            }
        }
        return data;
    }
    
    public Error getError() {
        return this.error;
    }
    
    public List<FalcorPathResult> getFalcorPathResults() {
        return this.falcorPathResults;
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
    
    public void setFalcorPathResults(final List<FalcorPathResult> falcorPathResults) {
        this.falcorPathResults = falcorPathResults;
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
}
