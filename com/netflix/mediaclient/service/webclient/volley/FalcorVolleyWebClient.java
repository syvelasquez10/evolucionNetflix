// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

import com.netflix.mediaclient.service.logging.presentation.volley.PresentationEventRequest;
import com.netflix.mediaclient.service.webclient.UserCredentialRegistry;
import com.netflix.mediaclient.service.webclient.UserCredentialRegistryWrapper;
import com.netflix.mediaclient.service.logging.client.volley.ClientLoggingVolleyWebClientRequest;
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
            LogUtils.reportDataRequestStarted(context, falcorVolleyWebClientRequest.getRequestId(), s);
        }
    }
    
    private void sendFalcorRequest(final FalcorVolleyWebClientRequest<?> falcorVolleyWebClientRequest, final String s) {
        falcorVolleyWebClientRequest.setApiEndpointRegistry(this.mApiEndpointRegistry);
        falcorVolleyWebClientRequest.setUserCredentialRegistry(this.mUserCredentialRegistry);
        falcorVolleyWebClientRequest.setRetryPolicy(this.createRetryPolicy());
        falcorVolleyWebClientRequest.initUrl(s);
        this.reportDataRequestSessionStarted(falcorVolleyWebClientRequest, s);
        FalcorVolleyWebClient.sRequestQueue.add(falcorVolleyWebClientRequest);
    }
    
    @Override
    public void init(final WebClientInitParameters webClientInitParameters) {
        if (webClientInitParameters instanceof NetflixWebClientInitParameters) {
            final NetflixWebClientInitParameters netflixWebClientInitParameters = (NetflixWebClientInitParameters)webClientInitParameters;
            this.mApiEndpointRegistry = netflixWebClientInitParameters.getApiEndpointRegistry();
            this.mUserCredentialRegistry = netflixWebClientInitParameters.getUserCredentialRegistry();
            FalcorVolleyWebClient.sRequestQueue = netflixWebClientInitParameters.getRequestQueue();
            this.mErrorLogger = netflixWebClientInitParameters.getErrorLogger();
            return;
        }
        throw new IllegalArgumentException("Expecting NetflixWebClientInitParameters and receiving " + webClientInitParameters);
    }
    
    public void sendConfigRequest(final FalcorVolleyWebClientRequest<?> falcorVolleyWebClientRequest) {
        this.sendFalcorRequest(falcorVolleyWebClientRequest, this.mApiEndpointRegistry.getConfigUrlFull());
    }
    
    public void sendLoggingRequest(final ClientLoggingVolleyWebClientRequest<?> clientLoggingVolleyWebClientRequest) {
        clientLoggingVolleyWebClientRequest.setUserCredentialRegistry(new UserCredentialRegistryWrapper(this.mUserCredentialRegistry));
        clientLoggingVolleyWebClientRequest.setRetryPolicy(this.createRetryPolicy());
        clientLoggingVolleyWebClientRequest.initUrl(this.mApiEndpointRegistry.getClientLoggingUrlFull());
        FalcorVolleyWebClient.sRequestQueue.add(clientLoggingVolleyWebClientRequest);
    }
    
    public void sendPresentationRequest(final PresentationEventRequest presentationEventRequest) {
        presentationEventRequest.setUserCredentialRegistry(new UserCredentialRegistryWrapper(this.mUserCredentialRegistry));
        presentationEventRequest.setRetryPolicy(this.createRetryPolicy());
        presentationEventRequest.initUrl(this.mApiEndpointRegistry.getPresentationTrackingUrlFull());
        FalcorVolleyWebClient.sRequestQueue.add(presentationEventRequest);
    }
    
    public void sendRequest(final FalcorVolleyWebClientRequest<?> falcorVolleyWebClientRequest) {
        this.sendRequest(falcorVolleyWebClientRequest, ApiEndpointRegistry.ResponsePathFormat.HIERARCHICAL);
    }
    
    public void sendRequest(final FalcorVolleyWebClientRequest<?> falcorVolleyWebClientRequest, final ApiEndpointRegistry.ResponsePathFormat responsePathFormat) {
        this.sendFalcorRequest(falcorVolleyWebClientRequest, this.mApiEndpointRegistry.getApiUrlFull(responsePathFormat));
    }
}
