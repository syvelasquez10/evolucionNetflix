// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner.reference;

import com.netflix.mediaclient.partner.PartnerCommunicationHandler;
import android.content.Intent;
import com.netflix.mediaclient.partner.ResponseListener;
import com.netflix.mediaclient.partner.PartnerRequest;
import com.netflix.mediaclient.partner.PartnerRequestType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.partner.Partner;
import com.netflix.mediaclient.partner.Partner$SSO;

public class NetflixSSO implements Partner$SSO
{
    private static final String TAG = "nf_partner";
    private final Partner owner;
    private final String service;
    
    NetflixSSO(final Partner owner, final String service) {
        this.owner = owner;
        this.service = service;
        Log.d("nf_partner", "Initialize...");
        this.getExternalSsoService();
    }
    
    private void getExternalSsoService() {
        Log.d("nf_partner", "getExternalSsoService:: start");
        final NetflixSSO$1 netflixSSO$1 = new NetflixSSO$1(this);
        final PartnerRequestType getExternalSsoService = PartnerRequestType.getExternalSsoService;
        final PartnerRequest partnerRequest = new PartnerRequest(getExternalSsoService, this.service, null, 1, netflixSSO$1);
        final PartnerCommunicationHandler partnerCommunicationHandler = this.owner.getPartnerCommunicationHandler();
        if (partnerCommunicationHandler == null) {
            Log.e("nf_partner", "Partner communication handler is missing. This should NOT happen!");
            return;
        }
        Log.d("nf_partner", "Partner communiction handler found, send getExternalSsoService request");
        partnerCommunicationHandler.launchWithIntent(partnerRequest, new Intent(getExternalSsoService.getIntentName()));
        Log.d("nf_partner", "Partner communication handler found, send getExternalSsoService request done.");
    }
    
    @Override
    public void getExternalUserToken(final String s, final int n, final ResponseListener responseListener) {
        Log.d("nf_partner", "getExternalUserToken:: start");
        final PartnerRequestType getExternalUserToken = PartnerRequestType.getExternalUserToken;
        final PartnerRequest partnerRequest = new PartnerRequest(getExternalUserToken, s, null, n, responseListener);
        final PartnerCommunicationHandler partnerCommunicationHandler = this.owner.getPartnerCommunicationHandler();
        if (partnerCommunicationHandler == null) {
            Log.e("nf_partner", "Partner communication handler is missing. This should NOT happen!");
            if (responseListener != null) {
                responseListener.onResponseReceived(ResponseFactory.createErrorResponseForPartnerCommunicationHandleIsNull(partnerRequest));
            }
        }
        else {
            if (this.service.equals(s)) {
                Log.d("nf_partner", "Partner communiction handler found, send getExternalUserToken request");
                final Intent intent = new Intent(getExternalUserToken.getIntentName());
                intent.setComponent(this.owner.getComponentName());
                partnerCommunicationHandler.launchWithIntent(partnerRequest, intent);
                Log.d("nf_partner", "Partner communication handler found, send getExternalUserToken request done.");
                return;
            }
            Log.e("nf_partner", "Partner communiction handler found, but requested service " + s + " is not implemented by this class " + this.service);
            if (responseListener != null) {
                responseListener.onResponseReceived(ResponseFactory.createErrorResponseForServiceMismatch(partnerRequest, this.service));
            }
        }
    }
    
    @Override
    public String getService() {
        return this.service;
    }
    
    @Override
    public void requestExternalUserAuth(final String s, final int n, final ResponseListener responseListener) {
        Log.d("nf_partner", "requestExternalUserAuth:: start");
        final PartnerRequestType requestExternalUserAuth = PartnerRequestType.requestExternalUserAuth;
        final PartnerRequest partnerRequest = new PartnerRequest(requestExternalUserAuth, s, null, n, responseListener);
        final PartnerCommunicationHandler partnerCommunicationHandler = this.owner.getPartnerCommunicationHandler();
        if (partnerCommunicationHandler == null) {
            Log.e("nf_partner", "Partner communication handler is missing. This should NOT happen!");
            if (responseListener != null) {
                responseListener.onResponseReceived(ResponseFactory.createErrorResponseForPartnerCommunicationHandleIsNull(partnerRequest));
            }
        }
        else {
            if (this.service.equals(s)) {
                Log.d("nf_partner", "Partner communication handler found, send requestExternalUserAuth request");
                final Intent intent = new Intent(requestExternalUserAuth.getIntentName());
                intent.setComponent(this.owner.getComponentName());
                partnerCommunicationHandler.launchWithIntent(partnerRequest, intent);
                Log.d("nf_partner", "Partner communiction handler found, send requestExternalUserAuth request done.");
                return;
            }
            Log.e("nf_partner", "Partner communiction handler found, but requested service " + s + " is not implemented by this class " + this.service);
            if (responseListener != null) {
                responseListener.onResponseReceived(ResponseFactory.createErrorResponseForServiceMismatch(partnerRequest, this.service));
            }
        }
    }
}
