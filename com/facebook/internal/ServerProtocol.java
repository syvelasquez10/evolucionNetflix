// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import com.facebook.Settings;
import java.util.Collection;

public final class ServerProtocol
{
    private static final String TAG;
    public static final Collection<String> errorsProxyAuthDisabled;
    public static final Collection<String> errorsUserCanceled;
    
    static {
        TAG = ServerProtocol.class.getName();
        errorsProxyAuthDisabled = Utility.unmodifiableCollection("service_disabled", "AndroidAuthKillSwitchException");
        errorsUserCanceled = Utility.unmodifiableCollection("access_denied", "OAuthAccessDeniedException");
    }
    
    public static final String getAPIVersion() {
        if (Settings.getPlatformCompatibilityEnabled()) {
            return "v1.0";
        }
        return "v2.2";
    }
    
    public static final String getDialogAuthority() {
        return String.format("m.%s", Settings.getFacebookDomain());
    }
    
    public static final String getGraphUrlBase() {
        return String.format("https://graph.%s", Settings.getFacebookDomain());
    }
    
    public static final String getGraphVideoUrlBase() {
        return String.format("https://graph-video.%s", Settings.getFacebookDomain());
    }
}
