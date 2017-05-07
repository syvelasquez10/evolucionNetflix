// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.android;

import android.content.Intent;
import java.util.Collections;
import android.os.Bundle;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.TokenCachingStrategy;
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
    public static final Uri ATTRIBUTION_ID_CONTENT_URI;
    @Deprecated
    protected static String DIALOG_BASE_URL;
    @Deprecated
    protected static String GRAPH_BASE_URL;
    @Deprecated
    protected static String RESTSERVER_URL;
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
        if (mAppId == null) {
            throw new IllegalArgumentException("You must specify your application ID when instantiating a Facebook object. See README for details.");
        }
        this.mAppId = mAppId;
    }
    
    private void authorize(final Activity pendingAuthorizationActivity, String[] pendingAuthorizationPermissions, final int requestCode, final SessionLoginBehavior loginBehavior, final Facebook$DialogListener facebook$DialogListener) {
        boolean b = false;
        this.checkUserSession("authorize");
        this.pendingOpeningSession = new Session$Builder((Context)pendingAuthorizationActivity).setApplicationId(this.mAppId).setTokenCachingStrategy(this.getTokenCache()).build();
        this.pendingAuthorizationActivity = pendingAuthorizationActivity;
        if (pendingAuthorizationPermissions == null) {
            pendingAuthorizationPermissions = new String[0];
        }
        this.pendingAuthorizationPermissions = pendingAuthorizationPermissions;
        final Session$OpenRequest setPermissions = new Session$OpenRequest(pendingAuthorizationActivity).setCallback(new Facebook$1(this, facebook$DialogListener)).setLoginBehavior(loginBehavior).setRequestCode(requestCode).setPermissions(Arrays.asList(this.pendingAuthorizationPermissions));
        final Session pendingOpeningSession = this.pendingOpeningSession;
        if (this.pendingAuthorizationPermissions.length > 0) {
            b = true;
        }
        this.openSession(pendingOpeningSession, setPermissions, b);
    }
    
    private void checkUserSession(final String s) {
        if (this.userSetSession != null) {
            throw new UnsupportedOperationException(String.format("Cannot call %s after setSession has been called.", s));
        }
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
        final int n = 0;
        int size;
        if (list != null) {
            size = list.size();
        }
        else {
            size = 0;
        }
        final String[] array = new String[size];
        if (list != null) {
            for (int i = n; i < array.length; ++i) {
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
    public String request(final Bundle bundle) {
        if (!bundle.containsKey("method")) {
            throw new IllegalArgumentException("API method must be specified. (parameters must contain key \"method\" and value). See http://developers.facebook.com/docs/reference/rest/");
        }
        return this.requestImpl(null, bundle, "GET");
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
}
