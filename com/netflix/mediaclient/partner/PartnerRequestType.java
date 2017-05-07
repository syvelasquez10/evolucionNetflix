// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner;

public enum PartnerRequestType
{
    getExternalSignUpService(4, "com.netflix.mediaclient.intent.action.SSO_getExternalSignUpService"), 
    getExternalSsoService(1, "com.netflix.mediaclient.intent.action.SSO_getExternalSsoService"), 
    getExternalUserData(5, "com.netflix.mediaclient.intent.action.SSO_getExternalUserData"), 
    getExternalUserToken(2, "com.netflix.mediaclient.intent.action.SSO_getExternalUserToken"), 
    requestExternalUserAuth(3, "com.netflix.mediaclient.intent.action.SSO_requestExternalUserAuthorization"), 
    requestExternalUserConfirmation(6, "com.netflix.mediaclient.intent.action.SSO_requestExternalUserConfirmation");
    
    private String intentName;
    private int requestCode;
    
    private PartnerRequestType(final int requestCode, final String intentName) {
        this.requestCode = requestCode;
        this.intentName = intentName;
    }
    
    public static boolean iSignup(final PartnerRequestType partnerRequestType) {
        return PartnerRequestType.getExternalSignUpService.equals(partnerRequestType) || PartnerRequestType.getExternalUserData.equals(partnerRequestType) || PartnerRequestType.requestExternalUserConfirmation.equals(partnerRequestType);
    }
    
    public static boolean iSso(final PartnerRequestType partnerRequestType) {
        return PartnerRequestType.getExternalSsoService.equals(partnerRequestType) || PartnerRequestType.getExternalUserToken.equals(partnerRequestType) || PartnerRequestType.requestExternalUserAuth.equals(partnerRequestType);
    }
    
    public String getIntentName() {
        return this.intentName;
    }
    
    public int getRequestCode() {
        return this.requestCode;
    }
}
