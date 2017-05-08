// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl.volley;

import java.util.Map;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry$ResponsePathFormat;

public abstract class ApiFalkorMSLVolleyRequest<T> extends FalkorMSLVolleyRequest<T>
{
    public ApiFalkorMSLVolleyRequest() {
    }
    
    public ApiFalkorMSLVolleyRequest(final ApiEndpointRegistry$ResponsePathFormat apiEndpointRegistry$ResponsePathFormat) {
        super(apiEndpointRegistry$ResponsePathFormat);
    }
    
    @Override
    public String getMSLUri() {
        return "/android/4.15/api";
    }
    
    @Override
    protected Map<String, String> getParams() {
        final Map<String, String> params = super.getParams();
        final Map<String, String> apiRequestParams = this.getMSLClient().getUrlProvider().getApiRequestParams(this.mResponsePathFormat);
        if (params == null) {
            return apiRequestParams;
        }
        params.putAll(apiRequestParams);
        return params;
    }
    
    @Override
    protected void injectUrl() {
        this.initUrl(this.getMSLClient().getApiUrl());
    }
}
