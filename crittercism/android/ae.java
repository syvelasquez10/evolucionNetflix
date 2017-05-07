// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

public enum ae
{
    a("CURRENT_BREADCRUMBS", 0, "com.crittercism.breadcrumbs", "currentBreadcrumbs"), 
    b("PREVIOUS_BREADCRUMBS", 1, "com.crittercism.breadcrumbs", "previousBreadcrumbs"), 
    c("NETWORK_BREADCRUMBS", 2, "com.crittercism.breadcrumbs", "networkBreadcrumbs"), 
    d("RATE_APP_SETTING", 3, "com.crittercism.usersettings", "rateAppSettings"), 
    e("CRASHED_ON_LAST_LOAD_SETTING", 4, "com.crittercism.usersettings", "crashedOnLastLoad"), 
    f("OPT_OUT_STATUS_SETTING", 5, "com.crittercism.usersettings", "optOutStatusSettings"), 
    g("HASHED_DEVICE_ID_SETTING", 6, "com.crittercism.usersettings", "hashedDeviceID"), 
    h("SESSION_ID_SETTING", 7, "com.crittercism.usersettings", "sessionIDSetting"), 
    i("OLD_HASHED_DEVICE_ID_SETTING", 8, "com.crittercism.prefs", "com.crittercism.prefs.did"), 
    j("OLD_SESSION_ID_SETTING", 9, "com.crittercism.prefs", "com.crittercism.prefs.sessid"), 
    k("OLD_OPT_OUT_STATUS_SETTING", 10, "com.crittercism.prefs", "optOutStatus");
    
    private String l;
    private String m;
    private String n;
    
    private ae(final String s, final int n, final String l, final String m) {
        this.l = l;
        this.m = m;
        this.n = null;
    }
    
    public final String a() {
        return this.l;
    }
    
    public final String b() {
        return this.m;
    }
}
