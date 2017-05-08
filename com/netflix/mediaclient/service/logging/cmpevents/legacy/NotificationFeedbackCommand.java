// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.cmpevents.legacy;

import android.util.Pair;
import org.apache.http.entity.StringEntity;
import org.apache.http.HttpEntity;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.webapi.CommonRequestParameters;
import com.netflix.mediaclient.webapi.AuthorizationCredentials;
import com.netflix.mediaclient.webapi.ResponseWebApiPostCommand;

public class NotificationFeedbackCommand extends ResponseWebApiPostCommand
{
    protected static final String PATH = "/log";
    private String mPayload;
    
    public NotificationFeedbackCommand(final String s, final AuthorizationCredentials authorizationCredentials, final CommonRequestParameters commonRequestParameters, final JSONObject jsonObject, final String s2) {
        super(s, authorizationCredentials, commonRequestParameters);
        this.mPayload = jsonObject.toString();
        if (Log.isLoggable()) {
            Log.d("nf_rest", "Payload: " + this.mPayload);
        }
    }
    
    public String getCommandPath() {
        return "/log";
    }
    
    protected HttpEntity getEntity() {
        return (HttpEntity)new StringEntity(this.mPayload, "UTF-8");
    }
    
    public Pair<String, String>[] getHeaders() {
        return (Pair<String, String>[])new Pair[] { new Pair((Object)"X-Netflix.ichnaea.request.type", (Object)"CMPMessageFeedbackRequest"), new Pair((Object)"Content-Type", (Object)"application/json") };
    }
    
    protected StringBuilder getUrl() {
        return this.getBaseCmsBeaconUrl();
    }
}
