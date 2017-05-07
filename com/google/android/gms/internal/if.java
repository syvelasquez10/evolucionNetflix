// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public enum if
{
    DC("Ok"), 
    DD("BadAuthentication"), 
    DE("InvalidSecondFactor"), 
    DF("NotVerified"), 
    DG("TermsNotAgreed"), 
    DH("Unknown"), 
    DI("UNKNOWN_ERR"), 
    DJ("AccountDeleted"), 
    DK("AccountDisabled"), 
    DL("ServiceDisabled"), 
    DM("ServiceUnavailable"), 
    DN("CaptchaRequired"), 
    DO("NetworkError"), 
    DP("UserCancel"), 
    DQ("PermissionDenied"), 
    DR("DeviceManagementRequiredOrSyncDisabled"), 
    DS("DeviceManagementInternalError"), 
    DT("DeviceManagementSyncDisabled"), 
    DU("DeviceManagementAdminBlocked"), 
    DV("DeviceManagementAdminPendingApproval"), 
    DW("DeviceManagementStaleSyncRequired"), 
    DX("DeviceManagementDeactivated"), 
    DY("DeviceManagementRequired"), 
    DZ("ClientLoginDisabled"), 
    Ea("NeedPermission"), 
    Eb("WeakPassword"), 
    Ec("ALREADY_HAS_GMAIL"), 
    Ed("BadRequest"), 
    Ee("BadUsername"), 
    Ef("LoginFail"), 
    Eg("NotLoggedIn"), 
    Eh("NoGmail"), 
    Ei("RequestDenied"), 
    Ej("ServerError"), 
    Ek("UsernameUnavailable"), 
    El("DeletedGmail"), 
    Em("SocketTimeout"), 
    En("ExistingUsername"), 
    Eo("NeedsBrowser"), 
    Ep("GPlusOther"), 
    Eq("GPlusNickname"), 
    Er("GPlusInvalidChar"), 
    Es("GPlusInterstitial"), 
    Et("ProfileUpgradeError"), 
    Eu("INVALID_SCOPE");
    
    public static String Ev;
    public static String Ew;
    private final String Ex;
    
    static {
        if.Ev = "Error";
        if.Ew = "status";
    }
    
    private if(final String ex) {
        this.Ex = ex;
    }
    
    public String fu() {
        return this.Ex;
    }
}
