// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.volley;

import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.volley.VolleyWebClientRequest;

public abstract class ClientLoggingVolleyWebClientRequest<T> extends VolleyWebClientRequest<T>
{
    private static final String TAG = "nf_volleyrequest";
    
    protected ClientLoggingVolleyWebClientRequest() {
        super(1);
    }
    
    @Override
    public byte[] getBody() {
        try {
            return this.getPayload().getBytes("UTF-8");
        }
        catch (Exception ex) {
            Log.e("nf_volleyrequest", "Failed to create body of client logging request", ex);
            return null;
        }
    }
    
    @Override
    public String getBodyContentType() {
        return "application/json";
    }
    
    @Override
    public Map<String, String> getHeaders() {
        final Map<String, String> headers = super.getHeaders();
        headers.put("X-Netflix.ichnaea.request.type", "UiRequest");
        return headers;
    }
    
    @Override
    protected String getMethodType() {
        return "post";
    }
    
    protected abstract String getPayload();
    
    @Override
    public byte[] getPostBody() {
        return this.getBody();
    }
    
    @Override
    public String getPostBodyContentType() {
        return this.getBodyContentType();
    }
    
    @Override
    protected String getUrl(final String s) {
        this.storeReqNetflixId(this.getCurrentNetflixId());
        if (Log.isLoggable()) {
            Log.v("nf_volleyrequest", "ClientLoggingVolleyWebClientReques URL = " + s);
        }
        return s;
    }
}
