// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

import com.netflix.mediaclient.service.logging.presentation.volley.PresentationEventRequest;
import com.netflix.mediaclient.service.logging.client.volley.ClientLoggingVolleyWebClientRequest;
import com.android.volley.RequestQueue;
import com.netflix.mediaclient.service.webclient.NetflixWebClientInitParameters;
import com.netflix.mediaclient.service.webclient.WebClientInitParameters;
import com.android.volley.Request;
import android.content.Context;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry;

public class FalcorVolleyWebClient extends VolleyWebClient
{
    private ApiEndpointRegistry mApiEndpointRegistry;
    
    private void reportDataRequestSessionStarted(final FalcorVolleyWebClientRequest<?> falcorVolleyWebClientRequest, final String s) {
        final Context context = falcorVolleyWebClientRequest.getContext();
        if (context != null) {
            LogUtils.reportDataRequestStarted(context, String.valueOf(falcorVolleyWebClientRequest.getRequestId()), s);
        }
    }
    
    private void sendFalcorRequest(final FalcorVolleyWebClientRequest<?> falcorVolleyWebClientRequest, final String s) {
        falcorVolleyWebClientRequest.setApiEndpointRegistry(this.mApiEndpointRegistry);
        falcorVolleyWebClientRequest.setUserCredentialRegistry(this.mUserCredentialRegistry);
        falcorVolleyWebClientRequest.setRetryPolicy(this.createRetryPolicy());
        falcorVolleyWebClientRequest.initUrl(s);
        this.reportDataRequestSessionStarted(falcorVolleyWebClientRequest, s);
        FalcorVolleyWebClient.mRequestQueue.add(falcorVolleyWebClientRequest);
    }
    
    @Override
    public void init(final WebClientInitParameters webClientInitParameters) {
        if (webClientInitParameters instanceof NetflixWebClientInitParameters) {
            final NetflixWebClientInitParameters netflixWebClientInitParameters = (NetflixWebClientInitParameters)webClientInitParameters;
            this.mApiEndpointRegistry = netflixWebClientInitParameters.getApiEndpointRegistry();
            this.mUserCredentialRegistry = netflixWebClientInitParameters.getUserCredentialRegistry();
            FalcorVolleyWebClient.mRequestQueue = (RequestQueue)netflixWebClientInitParameters.getConnectionObject();
            this.mErrorLogger = netflixWebClientInitParameters.getErrorLogger();
            return;
        }
        throw new IllegalArgumentException("Expecting NetflixWebClientInitParameters and receiving " + webClientInitParameters);
    }
    
    public void sendConfigRequest(final FalcorVolleyWebClientRequest<?> falcorVolleyWebClientRequest) {
        this.sendFalcorRequest(falcorVolleyWebClientRequest, this.mApiEndpointRegistry.getConfigUrlFull());
    }
    
    public void sendLoggingRequest(final ClientLoggingVolleyWebClientRequest<?> clientLoggingVolleyWebClientRequest) {
        clientLoggingVolleyWebClientRequest.setUserCredentialRegistry(this.mUserCredentialRegistry);
        clientLoggingVolleyWebClientRequest.setRetryPolicy(this.createRetryPolicy());
        clientLoggingVolleyWebClientRequest.initUrl(this.mApiEndpointRegistry.getClientLoggingUrlFull());
        FalcorVolleyWebClient.mRequestQueue.add(clientLoggingVolleyWebClientRequest);
    }
    
    public void sendPresentationRequest(final PresentationEventRequest presentationEventRequest) {
        presentationEventRequest.setUserCredentialRegistry(this.mUserCredentialRegistry);
        presentationEventRequest.setRetryPolicy(this.createRetryPolicy());
        presentationEventRequest.initUrl(this.mApiEndpointRegistry.getPresentationTrackingUrlFull());
        FalcorVolleyWebClient.mRequestQueue.add(presentationEventRequest);
    }
    
    public void sendRequest(final FalcorVolleyWebClientRequest<?> falcorVolleyWebClientRequest) {
        this.sendFalcorRequest(falcorVolleyWebClientRequest, this.mApiEndpointRegistry.getApiUrlFull());
    }
}
