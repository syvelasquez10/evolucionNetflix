// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.presentation.volley;

import com.netflix.mediaclient.service.logging.presentation.PresentationWebCallback;
import com.netflix.mediaclient.service.logging.presentation.PresentationRequest;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClient;
import com.netflix.mediaclient.service.logging.presentation.PresentationWebClient;

public class PresentationVolleyWebClient implements PresentationWebClient
{
    private final FalkorVolleyWebClient mWebClient;
    
    public PresentationVolleyWebClient(final FalkorVolleyWebClient mWebClient) {
        this.mWebClient = mWebClient;
    }
    
    @Override
    public boolean isSynchronous() {
        return this.mWebClient.isSynchronous();
    }
    
    @Override
    public void sendPresentationEvents(final PresentationRequest presentationRequest) {
        this.mWebClient.sendPresentationRequest(new PresentationEventRequest(null, presentationRequest, null));
    }
    
    @Override
    public void sendPresentationEvents(final String s, final PresentationRequest presentationRequest, final PresentationWebCallback presentationWebCallback) {
        this.mWebClient.sendPresentationRequest(new PresentationEventRequest(s, presentationRequest, presentationWebCallback));
    }
}
