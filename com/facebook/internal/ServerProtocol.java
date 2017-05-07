// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import org.json.JSONObject;
import org.json.JSONException;
import com.facebook.LoggingBehavior;
import android.os.Bundle;
import android.content.Context;
import com.facebook.Settings;
import java.util.Collection;

public final class ServerProtocol
{
    private static final String DIALOG_AUTHORITY_FORMAT = "m.%s";
    public static final String DIALOG_PARAM_ACCESS_TOKEN = "access_token";
    public static final String DIALOG_PARAM_APP_ID = "app_id";
    public static final String DIALOG_PARAM_AUTH_TYPE = "auth_type";
    public static final String DIALOG_PARAM_CLIENT_ID = "client_id";
    public static final String DIALOG_PARAM_DEFAULT_AUDIENCE = "default_audience";
    public static final String DIALOG_PARAM_DISPLAY = "display";
    public static final String DIALOG_PARAM_E2E = "e2e";
    public static final String DIALOG_PARAM_LEGACY_OVERRIDE = "legacy_override";
    public static final String DIALOG_PARAM_REDIRECT_URI = "redirect_uri";
    public static final String DIALOG_PARAM_RESPONSE_TYPE = "response_type";
    public static final String DIALOG_PARAM_RETURN_SCOPES = "return_scopes";
    public static final String DIALOG_PARAM_SCOPE = "scope";
    public static final String DIALOG_PATH = "dialog/";
    public static final String DIALOG_REREQUEST_AUTH_TYPE = "rerequest";
    public static final String DIALOG_RESPONSE_TYPE_TOKEN = "token";
    public static final String DIALOG_RETURN_SCOPES_TRUE = "true";
    public static final String FALLBACK_DIALOG_DISPLAY_VALUE_TOUCH = "touch";
    public static final String FALLBACK_DIALOG_PARAM_APP_ID = "app_id";
    public static final String FALLBACK_DIALOG_PARAM_BRIDGE_ARGS = "bridge_args";
    public static final String FALLBACK_DIALOG_PARAM_KEY_HASH = "android_key_hash";
    public static final String FALLBACK_DIALOG_PARAM_METHOD_ARGS = "method_args";
    public static final String FALLBACK_DIALOG_PARAM_METHOD_RESULTS = "method_results";
    public static final String FALLBACK_DIALOG_PARAM_VERSION = "version";
    public static final String GRAPH_API_VERSION = "v2.2";
    private static final String GRAPH_URL_FORMAT = "https://graph.%s";
    private static final String GRAPH_VIDEO_URL_FORMAT = "https://graph-video.%s";
    private static final String LEGACY_API_VERSION = "v1.0";
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
    
    public static Bundle getQueryParamsForPlatformActivityIntentWebFallback(final Context context, final String s, final int n, final String s2, final Bundle bundle) {
        final String applicationSignature = Settings.getApplicationSignature(context);
        if (!Utility.isNullOrEmpty(applicationSignature)) {
            final Bundle bundle2 = new Bundle();
            bundle2.putString("android_key_hash", applicationSignature);
            bundle2.putString("app_id", Settings.getApplicationId());
            bundle2.putInt("version", n);
            bundle2.putString("display", "touch");
            final Bundle bundle3 = new Bundle();
            bundle3.putString("action_id", s);
            bundle3.putString("app_name", s2);
            Bundle bundle4;
            if ((bundle4 = bundle) == null) {
                bundle4 = new Bundle();
            }
            try {
                final JSONObject convertToJSON = BundleJSONConverter.convertToJSON(bundle3);
                final JSONObject convertToJSON2 = BundleJSONConverter.convertToJSON(bundle4);
                if (convertToJSON != null && convertToJSON2 != null) {
                    bundle2.putString("bridge_args", convertToJSON.toString());
                    bundle2.putString("method_args", convertToJSON2.toString());
                    return bundle2;
                }
            }
            catch (JSONException ex) {
                Logger.log(LoggingBehavior.DEVELOPER_ERRORS, 6, ServerProtocol.TAG, "Error creating Url -- " + ex);
                return null;
            }
        }
        return null;
    }
}
