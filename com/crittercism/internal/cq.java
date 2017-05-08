// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public enum cq
{
    a("APP_LOADS_FILES", 0, "com.crittercism.apploads", "appLoadFiles"), 
    b("HANDLED_EXCEPTION_FILES", 1, "com.crittercism.exceptions", "handledExceptionFiles"), 
    c("SDK_CRASHES_FILES", 2, "com.crittercism.sdkcrashes", "sdkCrashFiles"), 
    d("NDK_CRASHES_FILES", 3, "com.crittercism.ndkcrashes", "ndkCrashFiles"), 
    e("CURRENT_BREADCRUMBS_FILES", 4, "com.crittercism.breadcrumbs", "currentBreadcrumbFiles"), 
    f("PREVIOUS_BREADCRUMBS_FILES", 5, "com.crittercism.breadcrumbs", "previousBreadcrumbFiles"), 
    g("NETWORK_BREADCRUMBS_FILES", 6, "com.crittercism.breadcrumbs", "networkBreadcrumbFiles"), 
    h("CRASHED_ON_LAST_LOAD_SETTING", 7, "com.crittercism.usersettings", "crashedOnLastLoad"), 
    i("OPT_OUT_STATUS_SETTING", 8, "com.crittercism.usersettings", "optOutStatusSettings"), 
    j("SESSION_ID_SETTING", 9, "com.crittercism.usersettings", "sessionIDSetting"), 
    k("OLD_SESSION_ID_SETTING", 10, "com.crittercism.prefs", "com.crittercism.prefs.sessid"), 
    l("OLD_OPT_OUT_STATUS_SETTING", 11, "com.crittercism.prefs", "optOutStatus");
    
    public String m;
    public String n;
    
    private cq(final String s, final int n, final String m, final String n2) {
        this.m = m;
        this.n = n2;
    }
}
