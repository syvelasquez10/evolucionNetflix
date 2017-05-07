// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

import com.netflix.mediaclient.StatusCode;
import com.android.volley.VolleyError;

public class StatusCodeError extends VolleyError
{
    private final StatusCode statusCode;
    
    public StatusCodeError(final StatusCode statusCode) {
        this.statusCode = statusCode;
    }
    
    public StatusCode getStatusCode() {
        return this.statusCode;
    }
}
