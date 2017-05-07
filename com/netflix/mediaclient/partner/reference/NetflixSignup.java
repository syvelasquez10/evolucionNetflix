// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner.reference;

import com.netflix.mediaclient.partner.PartnerCommunicationHandler;
import com.netflix.mediaclient.partner.BaseResponse;
import android.content.Intent;
import com.netflix.mediaclient.partner.PartnerRequest;
import com.netflix.mediaclient.partner.PartnerRequestType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.partner.ResponseListener;
import com.netflix.mediaclient.partner.Partner;
import com.netflix.mediaclient.partner.Partner$Signup;

public class NetflixSignup implements Partner$Signup
{
    private static final String TAG = "nf_partner";
    private final Partner owner;
    private final String service;
    
    NetflixSignup(final Partner owner, final String service) {
        this.owner = owner;
        this.service = service;
    }
    
    @Override
    public void getExternalUserData(final String s, final String s2, final int n, final ResponseListener responseListener) {
        Log.d("nf_partner", "getExternalUserData:: start");
        final PartnerRequestType getExternalUserData = PartnerRequestType.getExternalUserData;
        final PartnerRequest partnerRequest = new PartnerRequest(getExternalUserData, s, s2, n, responseListener);
        final PartnerCommunicationHandler partnerCommunicationHandler = this.owner.getPartnerCommunicationHandler();
        if (partnerCommunicationHandler == null) {
            Log.e("nf_partner", "Partner communication handler is missing. This should NOT happen!");
            if (responseListener != null) {
                responseListener.onResponseReceived(ResponseFactory.createErrorResponseForPartnerCommunicationHandleIsNull(partnerRequest));
            }
        }
        else {
            if (this.service.equals(s)) {
                Log.d("nf_partner", "Partner communiction handler found, send getExternalUserData request");
                final Intent intent = new Intent(getExternalUserData.getIntentName());
                intent.setComponent(this.owner.getComponentName());
                intent.putExtra("user_id", BaseResponse.noNull(s2));
                partnerCommunicationHandler.launchWithIntent(partnerRequest, intent);
                Log.d("nf_partner", "Partner communication handler found, send getExternalUserData request done.");
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
    public void requestExternalUserConfirmation(final String s, final String s2, final int n, final ResponseListener responseListener) {
        Log.d("nf_partner", "requestExternalUserConfirmation:: start");
        final PartnerRequestType requestExternalUserConfirmation = PartnerRequestType.requestExternalUserConfirmation;
        final PartnerRequest partnerRequest = new PartnerRequest(requestExternalUserConfirmation, s, s2, n, responseListener);
        final PartnerCommunicationHandler partnerCommunicationHandler = this.owner.getPartnerCommunicationHandler();
        if (partnerCommunicationHandler == null) {
            Log.e("nf_partner", "Partner communication handler is missing. This should NOT happen!");
            if (responseListener != null) {
                responseListener.onResponseReceived(ResponseFactory.createErrorResponseForPartnerCommunicationHandleIsNull(partnerRequest));
            }
        }
        else {
            if (this.service.equals(s)) {
                Log.d("nf_partner", "Partner communiction handler found, send requestExternalUserConfirmation request");
                final Intent intent = new Intent(requestExternalUserConfirmation.getIntentName());
                intent.setComponent(this.owner.getComponentName());
                intent.putExtra("user_id", BaseResponse.noNull(s2));
                partnerCommunicationHandler.launchWithIntent(partnerRequest, intent);
                Log.d("nf_partner", "Partner communication handler found, send requestExternalUserConfirmation request done.");
                return;
            }
            Log.e("nf_partner", "Partner communiction handler found, but requested service " + s + " is not implemented by this class " + this.service);
            if (responseListener != null) {
                responseListener.onResponseReceived(ResponseFactory.createErrorResponseForServiceMismatch(partnerRequest, this.service));
            }
        }
    }
}
