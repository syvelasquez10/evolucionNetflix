// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import java.util.Map;
import org.apache.http.HttpEntity;
import com.android.volley.NetworkResponse;

public class ProgressiveNetworkResponse extends NetworkResponse
{
    private final HttpEntity mHttpEntity;
    
    public ProgressiveNetworkResponse(final int n, final HttpEntity mHttpEntity, final Map<String, String> map, final boolean b) {
        super(n, null, map, b);
        this.mHttpEntity = mHttpEntity;
    }
    
    public HttpEntity getHttpEntity() {
        return this.mHttpEntity;
    }
}
