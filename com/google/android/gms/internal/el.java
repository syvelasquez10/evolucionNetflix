// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public enum el
{
    wR("Ok"), 
    wS("BadAuthentication"), 
    wT("InvalidSecondFactor"), 
    wU("NotVerified"), 
    wV("TermsNotAgreed"), 
    wW("Unknown"), 
    wX("UNKNOWN_ERR"), 
    wY("AccountDeleted"), 
    wZ("AccountDisabled"), 
    xA("GPlusInterstitial"), 
    xB("ProfileUpgradeError"), 
    xC("INVALID_SCOPE");
    
    public static String xD;
    public static String xE;
    
    xa("ServiceDisabled"), 
    xb("ServiceUnavailable"), 
    xc("CaptchaRequired"), 
    xd("NetworkError"), 
    xe("UserCancel"), 
    xf("PermissionDenied"), 
    xg("DeviceManagementRequiredOrSyncDisabled"), 
    xh("ClientLoginDisabled"), 
    xi("NeedPermission"), 
    xj("WeakPassword"), 
    xk("ALREADY_HAS_GMAIL"), 
    xl("BadRequest"), 
    xm("BadUsername"), 
    xn("LoginFail"), 
    xo("NotLoggedIn"), 
    xp("NoGmail"), 
    xq("RequestDenied"), 
    xr("ServerError"), 
    xs("UsernameUnavailable"), 
    xt("DeletedGmail"), 
    xu("SocketTimeout"), 
    xv("ExistingUsername"), 
    xw("NeedsBrowser"), 
    xx("GPlusOther"), 
    xy("GPlusNickname"), 
    xz("GPlusInvalidChar");
    
    private final String xF;
    
    static {
        el.xD = "Error";
        el.xE = "status";
    }
    
    private el(final String xf) {
        this.xF = xf;
    }
}
