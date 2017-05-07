// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry$ResponsePathFormat;
import com.netflix.mediaclient.service.logging.presentation.volley.PresentationEventRequest;
import com.netflix.mediaclient.service.webclient.UserCredentialRegistry;
import com.netflix.mediaclient.service.webclient.UserCredentialRegistryWrapper;
import com.netflix.mediaclient.service.logging.client.volley.ClientLoggingVolleyWebClientRequest;
import com.netflix.mediaclient.service.webclient.NetflixWebClientInitParameters;
import com.netflix.mediaclient.service.webclient.WebClientInitParameters;
import com.android.volley.Request;
import android.content.Context;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry;

public class FalkorVolleyWebClient extends VolleyWebClient
{
    private ApiEndpointRegistry mApiEndpointRegistry;
    
    private void reportDataRequestSessionStarted(final FalkorVolleyWebClientRequest<?> falkorVolleyWebClientRequest, final String s) {
        final Context context = falkorVolleyWebClientRequest.getContext();
        if (context != null) {
            ApmLogUtils.reportDataRequestStarted(context, falkorVolleyWebClientRequest.getRequestId(), s);
        }
    }
    
    private void sendFalkorRequest(final FalkorVolleyWebClientRequest<?> falkorVolleyWebClientRequest, final String s) {
        falkorVolleyWebClientRequest.setApiEndpointRegistry(this.mApiEndpointRegistry);
        falkorVolleyWebClientRequest.setUserCredentialRegistry(this.mUserCredentialRegistry);
        falkorVolleyWebClientRequest.setRetryPolicy(this.createRetryPolicy());
        falkorVolleyWebClientRequest.initUrl(s);
        this.reportDataRequestSessionStarted(falkorVolleyWebClientRequest, s);
        FalkorVolleyWebClient.sRequestQueue.add(falkorVolleyWebClientRequest);
    }
    
    @Override
    public void init(final WebClientInitParameters webClientInitParameters) {
        if (webClientInitParameters instanceof NetflixWebClientInitParameters) {
            final NetflixWebClientInitParameters netflixWebClientInitParameters = (NetflixWebClientInitParameters)webClientInitParameters;
            this.mApiEndpointRegistry = netflixWebClientInitParameters.getApiEndpointRegistry();
            this.mUserCredentialRegistry = netflixWebClientInitParameters.getUserCredentialRegistry();
            FalkorVolleyWebClient.sRequestQueue = netflixWebClientInitParameters.getRequestQueue();
            this.mErrorLogger = netflixWebClientInitParameters.getErrorLogger();
            return;
        }
        throw new IllegalArgumentException("Expecting NetflixWebClientInitParameters and receiving " + webClientInitParameters);
    }
    
    public void sendConfigRequest(final FalkorVolleyWebClientRequest<?> falkorVolleyWebClientRequest) {
        this.sendFalkorRequest(falkorVolleyWebClientRequest, this.mApiEndpointRegistry.getConfigUrlFull());
    }
    
    public void sendLoggingRequest(final ClientLoggingVolleyWebClientRequest<?> clientLoggingVolleyWebClientRequest) {
        clientLoggingVolleyWebClientRequest.setUserCredentialRegistry(new UserCredentialRegistryWrapper(this.mUserCredentialRegistry));
        clientLoggingVolleyWebClientRequest.setRetryPolicy(this.createRetryPolicy());
        clientLoggingVolleyWebClientRequest.initUrl(this.mApiEndpointRegistry.getClientLoggingUrlFull());
        FalkorVolleyWebClient.sRequestQueue.add(clientLoggingVolleyWebClientRequest);
    }
    
    public void sendPresentationRequest(final PresentationEventRequest presentationEventRequest) {
        presentationEventRequest.setUserCredentialRegistry(new UserCredentialRegistryWrapper(this.mUserCredentialRegistry));
        presentationEventRequest.setRetryPolicy(this.createRetryPolicy());
        presentationEventRequest.initUrl(this.mApiEndpointRegistry.getPresentationTrackingUrlFull());
        FalkorVolleyWebClient.sRequestQueue.add(presentationEventRequest);
    }
    
    public void sendRequest(final FalkorVolleyWebClientRequest<?> falkorVolleyWebClientRequest) {
        this.sendRequest(falkorVolleyWebClientRequest, ApiEndpointRegistry$ResponsePathFormat.HIERARCHICAL);
    }
    
    public void sendRequest(final FalkorVolleyWebClientRequest<?> falkorVolleyWebClientRequest, final ApiEndpointRegistry$ResponsePathFormat apiEndpointRegistry$ResponsePathFormat) {
        this.sendFalkorRequest(falkorVolleyWebClientRequest, this.mApiEndpointRegistry.getApiUrlFull(apiEndpointRegistry$ResponsePathFormat));
    }
}
