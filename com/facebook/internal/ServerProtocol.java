// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.util.Collection;

public final class ServerProtocol
{
    public static final String BATCHED_REST_METHOD_URL_BASE = "method/";
    public static final String DIALOG_AUTHORITY = "m.facebook.com";
    public static final String DIALOG_PARAM_ACCESS_TOKEN = "access_token";
    public static final String DIALOG_PARAM_APP_ID = "app_id";
    public static final String DIALOG_PARAM_CLIENT_ID = "client_id";
    public static final String DIALOG_PARAM_DISPLAY = "display";
    public static final String DIALOG_PARAM_REDIRECT_URI = "redirect_uri";
    public static final String DIALOG_PARAM_SCOPE = "scope";
    public static final String DIALOG_PARAM_TYPE = "type";
    public static final String DIALOG_PATH = "dialog/";
    static final String FACEBOOK_COM = "facebook.com";
    public static final String GRAPH_URL = "https://graph.facebook.com";
    public static final String GRAPH_URL_BASE = "https://graph.facebook.com/";
    public static final String REST_URL_BASE = "https://api.facebook.com/method/";
    public static final Collection<String> errorsProxyAuthDisabled;
    public static final Collection<String> errorsUserCanceled;
    
    static {
        errorsProxyAuthDisabled = Utility.unmodifiableCollection("service_disabled", "AndroidAuthKillSwitchException");
        errorsUserCanceled = Utility.unmodifiableCollection("access_denied", "OAuthAccessDeniedException");
    }
}
