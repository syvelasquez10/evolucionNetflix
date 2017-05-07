// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.presentation.volley;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.volley.VolleyWebClientRequest;

public abstract class PresentationVolleyWebClientRequest<T> extends VolleyWebClientRequest<T>
{
    private static final String TAG = "nf_presentation";
    
    protected PresentationVolleyWebClientRequest() {
        super(1);
    }
    
    @Override
    protected String getMethodType() {
        return "post";
    }
    
    @Override
    protected String getUrl(final String s) {
        this.storeReqNetflixId(this.getCurrentNetflixId());
        if (Log.isLoggable("nf_presentation", 2)) {
            Log.v("nf_presentation", "PresentationVolleyWebClientRequest URL = " + s);
        }
        return s;
    }
}
