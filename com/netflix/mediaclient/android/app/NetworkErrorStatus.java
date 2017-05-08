// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.util.VolleyUtils;
import com.android.volley.VolleyError;
import com.netflix.mediaclient.service.logging.client.model.Error;

public class NetworkErrorStatus implements Status
{
    private Error mError;
    private VolleyError mVolleyError;
    
    public NetworkErrorStatus(final VolleyError mVolleyError) {
        this.mVolleyError = mVolleyError;
        this.mError = VolleyUtils.toError(mVolleyError);
    }
    
    @Override
    public Error getError() {
        return this.mError;
    }
    
    @Override
    public String getMessage() {
        return null;
    }
    
    @Override
    public int getRequestId() {
        return 0;
    }
    
    @Override
    public StatusCode getStatusCode() {
        return StatusCode.NETWORK_ERROR;
    }
    
    public VolleyError getVolleyError() {
        return this.mVolleyError;
    }
    
    @Override
    public boolean isError() {
        return true;
    }
    
    @Override
    public boolean isSucces() {
        return false;
    }
    
    @Override
    public boolean shouldDisplayMessage() {
        return false;
    }
    
    @Override
    public String toString() {
        return "NetworkErrorStatus{VolleyError=" + this.mVolleyError + ", Error=" + this.mError + '}';
    }
}
