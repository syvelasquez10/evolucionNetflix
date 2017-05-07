// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.android;

import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.content.pm.Signature;
import android.content.pm.PackageManager$NameNotFoundException;
import java.util.Collections;
import android.os.Bundle;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.TokenCachingStrategy;
import com.facebook.Settings;
import android.content.ContentResolver;
import java.util.Arrays;
import com.facebook.Session$StatusCallback;
import com.facebook.Session$OpenRequest;
import android.content.Context;
import com.facebook.Session$Builder;
import com.facebook.SessionLoginBehavior;
import java.util.List;
import com.facebook.SessionState;
import com.facebook.Session;
import android.app.Activity;
import android.net.Uri;

public class Facebook
{
    @Deprecated
    public static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    @Deprecated
    public static final Uri ATTRIBUTION_ID_CONTENT_URI;
    @Deprecated
    public static final String CANCEL_URI = "fbconnect://cancel";
    private static final int DEFAULT_AUTH_ACTIVITY_CODE = 32665;
    @Deprecated
    protected static String DIALOG_BASE_URL;
    @Deprecated
    public static final String EXPIRES = "expires_in";
    @Deprecated
    public static final String FB_APP_SIGNATURE = "30820268308201d102044a9c4610300d06092a864886f70d0101040500307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e3020170d3039303833313231353231365a180f32303530303932353231353231365a307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e30819f300d06092a864886f70d010101050003818d0030818902818100c207d51df8eb8c97d93ba0c8c1002c928fab00dc1b42fca5e66e99cc3023ed2d214d822bc59e8e35ddcf5f44c7ae8ade50d7e0c434f500e6c131f4a2834f987fc46406115de2018ebbb0d5a3c261bd97581ccfef76afc7135a6d59e8855ecd7eacc8f8737e794c60a761c536b72b11fac8e603f5da1a2d54aa103b8a13c0dbc10203010001300d06092a864886f70d0101040500038181005ee9be8bcbb250648d3b741290a82a1c9dc2e76a0af2f2228f1d9f9c4007529c446a70175c5a900d5141812866db46be6559e2141616483998211f4a673149fb2232a10d247663b26a9031e15f84bc1c74d141ff98a02d76f85b2c8ab2571b6469b232d8e768a7f7ca04f7abe4a775615916c07940656b58717457b42bd928a2";
    @Deprecated
    public static final int FORCE_DIALOG_AUTH = -1;
    @Deprecated
    protected static String GRAPH_BASE_URL;
    private static final String LOGIN = "oauth";
    @Deprecated
    public static final String REDIRECT_URI = "fbconnect://success";
    @Deprecated
    protected static String RESTSERVER_URL;
    @Deprecated
    public static final String SINGLE_SIGN_ON_DISABLED = "service_disabled";
    @Deprecated
    public static final String TOKEN = "access_token";
    private final long REFRESH_TOKEN_BARRIER;
    private long accessExpiresMillisecondsAfterEpoch;
    private String accessToken;
    private long lastAccessUpdateMillisecondsAfterEpoch;
    private final Object lock;
    private String mAppId;
    private Activity pendingAuthorizationActivity;
    private String[] pendingAuthorizationPermissions;
    private Session pendingOpeningSession;
    private volatile Session session;
    private boolean sessionInvalidated;
    private Facebook$SetterTokenCachingStrategy tokenCache;
    private volatile Session userSetSession;
    
    static {
        ATTRIBUTION_ID_CONTENT_URI = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
        Facebook.DIALOG_BASE_URL = "https://m.facebook.com/dialog/";
        Facebook.GRAPH_BASE_URL = "https://graph.facebook.com/";
        Facebook.RESTSERVER_URL = "https://api.facebook.com/restserver.php";
    }
    
    public Facebook(final String mAppId) {
        this.lock = new Object();
        this.accessToken = null;
        this.accessExpiresMillisecondsAfterEpoch = 0L;
        this.lastAccessUpdateMillisecondsAfterEpoch = 0L;
        this.REFRESH_TOKEN_BARRIER = 86400000L;
        if (mAppId == null) {
            throw new IllegalArgumentException("You must specify your application ID when instantiating a Facebook object. See README for details.");
        }
        this.mAppId = mAppId;
    }
    
    private void authorize(final Activity pendingAuthorizationActivity, final String[] array, final int requestCode, final SessionLoginBehavior loginBehavior, final Facebook$DialogListener facebook$DialogListener) {
        this.checkUserSession("authorize");
        this.pendingOpeningSession = new Session$Builder((Context)pendingAuthorizationActivity).setApplicationId(this.mAppId).setTokenCachingStrategy(this.getTokenCache()).build();
        this.pendingAuthorizationActivity = pendingAuthorizationActivity;
        String[] pendingAuthorizationPermissions;
        if (array != null) {
            pendingAuthorizationPermissions = array;
        }
        else {
            pendingAuthorizationPermissions = new String[0];
        }
        this.pendingAuthorizationPermissions = pendingAuthorizationPermissions;
        this.openSession(this.pendingOpeningSession, new Session$OpenRequest(pendingAuthorizationActivity).setCallback(new Facebook$1(this, facebook$DialogListener)).setLoginBehavior(loginBehavior).setRequestCode(requestCode).setPermissions(Arrays.asList(array)), this.pendingAuthorizationPermissions.length > 0);
    }
    
    private void checkUserSession(final String s) {
        if (this.userSetSession != null) {
            throw new UnsupportedOperationException(String.format("Cannot call %s after setSession has been called.", s));
        }
    }
    
    @Deprecated
    public static String getAttributionId(final ContentResolver contentResolver) {
        return Settings.getAttributionId(contentResolver);
    }
    
    private TokenCachingStrategy getTokenCache() {
        if (this.tokenCache == null) {
            this.tokenCache = new Facebook$SetterTokenCachingStrategy(this, null);
        }
        return this.tokenCache;
    }
    
    private void onSessionCallback(final Session session, final SessionState sessionState, final Exception ex, final Facebook$DialogListener facebook$DialogListener) {
        final Bundle authorizationBundle = session.getAuthorizationBundle();
        Label_0070: {
            if (sessionState != SessionState.OPENED) {
                break Label_0070;
            }
            Session session2 = null;
            synchronized (this.lock) {
                if (session != this.session) {
                    session2 = this.session;
                    this.session = session;
                    this.sessionInvalidated = false;
                }
                // monitorexit(this.lock)
                if (session2 != null) {
                    session2.close();
                }
                facebook$DialogListener.onComplete(authorizationBundle);
                return;
            }
        }
        if (ex == null) {
            return;
        }
        if (ex instanceof FacebookOperationCanceledException) {
            facebook$DialogListener.onCancel();
            return;
        }
        if (ex instanceof FacebookAuthorizationException && authorizationBundle != null && authorizationBundle.containsKey("com.facebook.sdk.WebViewErrorCode") && authorizationBundle.containsKey("com.facebook.sdk.FailingUrl")) {
            facebook$DialogListener.onError(new DialogError(ex.getMessage(), authorizationBundle.getInt("com.facebook.sdk.WebViewErrorCode"), authorizationBundle.getString("com.facebook.sdk.FailingUrl")));
            return;
        }
        facebook$DialogListener.onFacebookError(new FacebookError(ex.getMessage()));
    }
    
    private void openSession(final Session session, final Session$OpenRequest session$OpenRequest, final boolean b) {
        session$OpenRequest.setIsLegacy(true);
        if (b) {
            session.openForPublish(session$OpenRequest);
            return;
        }
        session.openForRead(session$OpenRequest);
    }
    
    private static String[] stringArray(final List<String> list) {
        final String[] array = new String[list.size()];
        if (list != null) {
            for (int i = 0; i < array.length; ++i) {
                array[i] = list.get(i);
            }
        }
        return array;
    }
    
    private static List<String> stringList(final String[] array) {
        if (array != null) {
            return Arrays.asList(array);
        }
        return Collections.emptyList();
    }
    
    private boolean validateAppSignatureForPackage(final Context context, final String s) {
        final boolean b = false;
        try {
            final Signature[] signatures = context.getPackageManager().getPackageInfo(s, 64).signatures;
            final int length = signatures.length;
            int n = 0;
            boolean b2;
            while (true) {
                b2 = b;
                if (n >= length) {
                    break;
                }
                if (signatures[n].toCharsString().equals("30820268308201d102044a9c4610300d06092a864886f70d0101040500307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e3020170d3039303833313231353231365a180f32303530303932353231353231365a307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e30819f300d06092a864886f70d010101050003818d0030818902818100c207d51df8eb8c97d93ba0c8c1002c928fab00dc1b42fca5e66e99cc3023ed2d214d822bc59e8e35ddcf5f44c7ae8ade50d7e0c434f500e6c131f4a2834f987fc46406115de2018ebbb0d5a3c261bd97581ccfef76afc7135a6d59e8855ecd7eacc8f8737e794c60a761c536b72b11fac8e603f5da1a2d54aa103b8a13c0dbc10203010001300d06092a864886f70d0101040500038181005ee9be8bcbb250648d3b741290a82a1c9dc2e76a0af2f2228f1d9f9c4007529c446a70175c5a900d5141812866db46be6559e2141616483998211f4a673149fb2232a10d247663b26a9031e15f84bc1c74d141ff98a02d76f85b2c8ab2571b6469b232d8e768a7f7ca04f7abe4a775615916c07940656b58717457b42bd928a2")) {
                    b2 = true;
                    break;
                }
                ++n;
            }
            return b2;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return false;
        }
    }
    
    private boolean validateServiceIntent(final Context context, final Intent intent) {
        final ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
        return resolveService != null && this.validateAppSignatureForPackage(context, resolveService.serviceInfo.packageName);
    }
    
    @Deprecated
    public void authorize(final Activity activity, final Facebook$DialogListener facebook$DialogListener) {
        this.authorize(activity, new String[0], 32665, SessionLoginBehavior.SSO_WITH_FALLBACK, facebook$DialogListener);
    }
    
    @Deprecated
    public void authorize(final Activity activity, final String[] array, final int n, final Facebook$DialogListener facebook$DialogListener) {
        SessionLoginBehavior sessionLoginBehavior;
        if (n >= 0) {
            sessionLoginBehavior = SessionLoginBehavior.SSO_WITH_FALLBACK;
        }
        else {
            sessionLoginBehavior = SessionLoginBehavior.SUPPRESS_SSO;
        }
        this.authorize(activity, array, n, sessionLoginBehavior, facebook$DialogListener);
    }
    
    @Deprecated
    public void authorize(final Activity activity, final String[] array, final Facebook$DialogListener facebook$DialogListener) {
        this.authorize(activity, array, 32665, SessionLoginBehavior.SSO_WITH_FALLBACK, facebook$DialogListener);
    }
    
    @Deprecated
    public void authorizeCallback(final int n, final int n2, final Intent intent) {
        this.checkUserSession("authorizeCallback");
        final Session pendingOpeningSession = this.pendingOpeningSession;
        if (pendingOpeningSession != null && pendingOpeningSession.onActivityResult(this.pendingAuthorizationActivity, n, n2, intent)) {
            this.pendingOpeningSession = null;
            this.pendingAuthorizationActivity = null;
            this.pendingAuthorizationPermissions = null;
        }
    }
    
    @Deprecated
    public void dialog(final Context context, final String s, final Bundle bundle, final Facebook$DialogListener facebook$DialogListener) {
        bundle.putString("display", "touch");
        bundle.putString("redirect_uri", "fbconnect://success");
        if (s.equals("oauth")) {
            bundle.putString("type", "user_agent");
            bundle.putString("client_id", this.mAppId);
        }
        else {
            bundle.putString("app_id", this.mAppId);
            if (this.isSessionValid()) {
                bundle.putString("access_token", this.getAccessToken());
            }
        }
        if (context.checkCallingOrSelfPermission("android.permission.INTERNET") != 0) {
            Util.showAlert(context, "Error", "Application requires permission to access the Internet");
            return;
        }
        new FbDialog(context, s, bundle, facebook$DialogListener).show();
    }
    
    @Deprecated
    public void dialog(final Context context, final String s, final Facebook$DialogListener facebook$DialogListener) {
        this.dialog(context, s, new Bundle(), facebook$DialogListener);
    }
    
    @Deprecated
    public boolean extendAccessToken(final Context context, final Facebook$ServiceListener facebook$ServiceListener) {
        this.checkUserSession("extendAccessToken");
        final Intent intent = new Intent();
        intent.setClassName("com.facebook.katana", "com.facebook.katana.platform.TokenRefreshService");
        return this.validateServiceIntent(context, intent) && context.bindService(intent, (ServiceConnection)new Facebook$TokenRefreshServiceConnection(this, context, facebook$ServiceListener), 1);
    }
    
    @Deprecated
    public boolean extendAccessTokenIfNeeded(final Context context, final Facebook$ServiceListener facebook$ServiceListener) {
        this.checkUserSession("extendAccessTokenIfNeeded");
        return !this.shouldExtendAccessToken() || this.extendAccessToken(context, facebook$ServiceListener);
    }
    
    @Deprecated
    public long getAccessExpires() {
        final Session session = this.getSession();
        if (session != null) {
            return session.getExpirationDate().getTime();
        }
        return this.accessExpiresMillisecondsAfterEpoch;
    }
    
    @Deprecated
    public String getAccessToken() {
        final Session session = this.getSession();
        if (session != null) {
            return session.getAccessToken();
        }
        return null;
    }
    
    @Deprecated
    public String getAppId() {
        return this.mAppId;
    }
    
    @Deprecated
    public long getLastAccessUpdate() {
        return this.lastAccessUpdateMillisecondsAfterEpoch;
    }
    
    @Deprecated
    public final Session getSession() {
        while (true) {
            synchronized (this.lock) {
                if (this.userSetSession != null) {
                    return this.userSetSession;
                }
                if (this.session != null || !this.sessionInvalidated) {
                    return this.session;
                }
            }
            final String accessToken = this.accessToken;
            final Session session = this.session;
            // monitorexit(o)
            if (accessToken == null) {
                return null;
            }
            List<String> permissions;
            if (session != null) {
                permissions = session.getPermissions();
            }
            else if (this.pendingAuthorizationPermissions != null) {
                permissions = Arrays.asList(this.pendingAuthorizationPermissions);
            }
            else {
                permissions = (List<String>)Collections.emptyList();
            }
            Session build = new Session$Builder((Context)this.pendingAuthorizationActivity).setApplicationId(this.mAppId).setTokenCachingStrategy(this.getTokenCache()).build();
            if (build.getState() != SessionState.CREATED_TOKEN_LOADED) {
                return null;
            }
            final Session$OpenRequest setPermissions = new Session$OpenRequest(this.pendingAuthorizationActivity).setPermissions(permissions);
            Label_0238: {
                if (permissions.isEmpty()) {
                    break Label_0238;
                }
                boolean b = true;
            Label_0221_Outer:
                while (true) {
                    this.openSession(build, setPermissions, b);
                    while (true) {
                        Label_0249: {
                            synchronized (this.lock) {
                                if (!this.sessionInvalidated && this.session != null) {
                                    break Label_0249;
                                }
                                final Session session2 = this.session;
                                this.session = build;
                                this.sessionInvalidated = false;
                                // monitorexit(this.lock)
                                if (session2 != null) {
                                    session2.close();
                                }
                                if (build != null) {
                                    return build;
                                }
                                break;
                                b = false;
                                continue Label_0221_Outer;
                            }
                        }
                        build = null;
                        final Session session2 = null;
                        continue;
                    }
                }
            }
        }
    }
    
    @Deprecated
    public boolean getShouldAutoPublishInstall() {
        return Settings.getShouldAutoPublishInstall();
    }
    
    @Deprecated
    public boolean isSessionValid() {
        return this.getAccessToken() != null && (this.getAccessExpires() == 0L || System.currentTimeMillis() < this.getAccessExpires());
    }
    
    @Deprecated
    public String logout(final Context context) {
        return this.logoutImpl(context);
    }
    
    String logoutImpl(final Context context) {
        this.checkUserSession("logout");
        final Bundle bundle = new Bundle();
        bundle.putString("method", "auth.expireSession");
        final String request = this.request(bundle);
        final long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.lock) {
            final Session session = this.session;
            this.session = null;
            this.accessToken = null;
            this.accessExpiresMillisecondsAfterEpoch = 0L;
            this.lastAccessUpdateMillisecondsAfterEpoch = currentTimeMillis;
            this.sessionInvalidated = false;
            // monitorexit(this.lock)
            if (session != null) {
                session.closeAndClearTokenInformation();
            }
            return request;
        }
    }
    
    @Deprecated
    public boolean publishInstall(final Context context) {
        Settings.publishInstallAsync(context, this.mAppId);
        return false;
    }
    
    @Deprecated
    public String request(final Bundle bundle) {
        if (!bundle.containsKey("method")) {
            throw new IllegalArgumentException("API method must be specified. (parameters must contain key \"method\" and value). See http://developers.facebook.com/docs/reference/rest/");
        }
        return this.requestImpl(null, bundle, "GET");
    }
    
    @Deprecated
    public String request(final String s) {
        return this.requestImpl(s, new Bundle(), "GET");
    }
    
    @Deprecated
    public String request(final String s, final Bundle bundle) {
        return this.requestImpl(s, bundle, "GET");
    }
    
    @Deprecated
    public String request(final String s, final Bundle bundle, final String s2) {
        return this.requestImpl(s, bundle, s2);
    }
    
    String requestImpl(String s, final Bundle bundle, final String s2) {
        bundle.putString("format", "json");
        if (this.isSessionValid()) {
            bundle.putString("access_token", this.getAccessToken());
        }
        if (s != null) {
            s = Facebook.GRAPH_BASE_URL + s;
        }
        else {
            s = Facebook.RESTSERVER_URL;
        }
        return Util.openUrl(s, s2, bundle);
    }
    
    @Deprecated
    public void setAccessExpires(final long accessExpiresMillisecondsAfterEpoch) {
        this.checkUserSession("setAccessExpires");
        synchronized (this.lock) {
            this.accessExpiresMillisecondsAfterEpoch = accessExpiresMillisecondsAfterEpoch;
            this.lastAccessUpdateMillisecondsAfterEpoch = System.currentTimeMillis();
            this.sessionInvalidated = true;
        }
    }
    
    @Deprecated
    public void setAccessExpiresIn(final String s) {
        this.checkUserSession("setAccessExpiresIn");
        if (s != null) {
            long accessExpires;
            if (s.equals("0")) {
                accessExpires = 0L;
            }
            else {
                accessExpires = System.currentTimeMillis() + Long.parseLong(s) * 1000L;
            }
            this.setAccessExpires(accessExpires);
        }
    }
    
    @Deprecated
    public void setAccessToken(final String accessToken) {
        this.checkUserSession("setAccessToken");
        synchronized (this.lock) {
            this.accessToken = accessToken;
            this.lastAccessUpdateMillisecondsAfterEpoch = System.currentTimeMillis();
            this.sessionInvalidated = true;
        }
    }
    
    @Deprecated
    public void setAppId(final String mAppId) {
        this.checkUserSession("setAppId");
        synchronized (this.lock) {
            this.mAppId = mAppId;
            this.sessionInvalidated = true;
        }
    }
    
    @Deprecated
    public void setSession(final Session userSetSession) {
        if (userSetSession == null) {
            throw new IllegalArgumentException("session cannot be null");
        }
        synchronized (this.lock) {
            this.userSetSession = userSetSession;
        }
    }
    
    @Deprecated
    public void setShouldAutoPublishInstall(final boolean shouldAutoPublishInstall) {
        Settings.setShouldAutoPublishInstall(shouldAutoPublishInstall);
    }
    
    @Deprecated
    public void setTokenFromCache(final String accessToken, final long accessExpiresMillisecondsAfterEpoch, final long lastAccessUpdateMillisecondsAfterEpoch) {
        this.checkUserSession("setTokenFromCache");
        synchronized (this.lock) {
            this.accessToken = accessToken;
            this.accessExpiresMillisecondsAfterEpoch = accessExpiresMillisecondsAfterEpoch;
            this.lastAccessUpdateMillisecondsAfterEpoch = lastAccessUpdateMillisecondsAfterEpoch;
        }
    }
    
    @Deprecated
    public boolean shouldExtendAccessToken() {
        this.checkUserSession("shouldExtendAccessToken");
        return this.isSessionValid() && System.currentTimeMillis() - this.lastAccessUpdateMillisecondsAfterEpoch >= 86400000L;
    }
}
