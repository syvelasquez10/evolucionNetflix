// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.Iterator;
import android.content.ActivityNotFoundException;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import android.util.Log;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.app.Fragment;
import android.app.Activity;
import java.util.Collection;
import com.facebook.internal.SessionAuthorizationType;
import android.content.Intent;
import java.util.Collections;
import android.os.Looper;
import java.util.ArrayList;
import com.facebook.internal.Validate;
import com.facebook.internal.Utility;
import java.util.Date;
import android.os.Handler;
import java.util.List;
import android.os.Bundle;
import android.content.Context;
import java.util.Set;
import java.io.Serializable;

public class Session implements Serializable
{
    public static final String ACTION_ACTIVE_SESSION_CLOSED = "com.facebook.sdk.ACTIVE_SESSION_CLOSED";
    public static final String ACTION_ACTIVE_SESSION_OPENED = "com.facebook.sdk.ACTIVE_SESSION_OPENED";
    public static final String ACTION_ACTIVE_SESSION_SET = "com.facebook.sdk.ACTIVE_SESSION_SET";
    public static final String ACTION_ACTIVE_SESSION_UNSET = "com.facebook.sdk.ACTIVE_SESSION_UNSET";
    public static final String APPLICATION_ID_PROPERTY = "com.facebook.sdk.ApplicationId";
    private static final String AUTH_BUNDLE_SAVE_KEY = "com.facebook.sdk.Session.authBundleKey";
    public static final int DEFAULT_AUTHORIZE_ACTIVITY_CODE = 64206;
    private static final String MANAGE_PERMISSION_PREFIX = "manage";
    private static final Set<String> OTHER_PUBLISH_PERMISSIONS;
    private static final String PUBLISH_PERMISSION_PREFIX = "publish";
    private static final String SESSION_BUNDLE_SAVE_KEY = "com.facebook.sdk.Session.saveSessionKey";
    private static final Object STATIC_LOCK;
    public static final String TAG;
    private static final int TOKEN_EXTEND_RETRY_SECONDS = 3600;
    private static final int TOKEN_EXTEND_THRESHOLD_SECONDS = 86400;
    public static final String WEB_VIEW_ERROR_CODE_KEY = "com.facebook.sdk.WebViewErrorCode";
    public static final String WEB_VIEW_FAILING_URL_KEY = "com.facebook.sdk.FailingUrl";
    private static Session activeSession;
    private static final long serialVersionUID = 1L;
    private static volatile Context staticContext;
    private final String applicationId;
    private volatile Bundle authorizationBundle;
    private AuthorizationClient authorizationClient;
    private Session$AutoPublishAsyncTask autoPublishAsyncTask;
    private final List<Session$StatusCallback> callbacks;
    private volatile Session$TokenRefreshRequest currentTokenRefreshRequest;
    private final Handler handler;
    private Date lastAttemptedTokenExtendDate;
    private final Object lock;
    private Session$AuthorizationRequest pendingRequest;
    private SessionState state;
    private TokenCachingStrategy tokenCachingStrategy;
    private AccessToken tokenInfo;
    
    static {
        TAG = Session.class.getCanonicalName();
        STATIC_LOCK = new Object();
        OTHER_PUBLISH_PERMISSIONS = new Session$1();
    }
    
    public Session(final Context context) {
        this(context, null, null, true);
    }
    
    Session(final Context context, final String s, final TokenCachingStrategy tokenCachingStrategy) {
        this(context, s, tokenCachingStrategy, true);
    }
    
    Session(final Context context, final String s, final TokenCachingStrategy tokenCachingStrategy, final boolean b) {
        final Bundle bundle = null;
        this.lastAttemptedTokenExtendDate = new Date(0L);
        this.lock = new Object();
        String metadataApplicationId = s;
        if (context != null && (metadataApplicationId = s) == null) {
            metadataApplicationId = Utility.getMetadataApplicationId(context);
        }
        Validate.notNull(metadataApplicationId, "applicationId");
        initializeStaticContext(context);
        TokenCachingStrategy tokenCachingStrategy2;
        if ((tokenCachingStrategy2 = tokenCachingStrategy) == null) {
            tokenCachingStrategy2 = new SharedPreferencesTokenCachingStrategy(Session.staticContext);
        }
        this.applicationId = metadataApplicationId;
        this.tokenCachingStrategy = tokenCachingStrategy2;
        this.state = SessionState.CREATED;
        this.pendingRequest = null;
        this.callbacks = new ArrayList<Session$StatusCallback>();
        this.handler = new Handler(Looper.getMainLooper());
        Bundle load = bundle;
        if (b) {
            load = tokenCachingStrategy2.load();
        }
        if (!TokenCachingStrategy.hasTokenInformation(load)) {
            this.tokenInfo = AccessToken.createEmptyToken(Collections.emptyList());
            return;
        }
        final Date date = TokenCachingStrategy.getDate(load, "com.facebook.TokenCachingStrategy.ExpirationDate");
        final Date date2 = new Date();
        if (date == null || date.before(date2)) {
            tokenCachingStrategy2.clear();
            this.tokenInfo = AccessToken.createEmptyToken(Collections.emptyList());
            return;
        }
        this.tokenInfo = AccessToken.createFromCache(load);
        this.state = SessionState.CREATED_TOKEN_LOADED;
    }
    
    private Session(final String applicationId, final SessionState state, final AccessToken tokenInfo, final Date lastAttemptedTokenExtendDate, final boolean b, final Session$AuthorizationRequest pendingRequest) {
        this.lastAttemptedTokenExtendDate = new Date(0L);
        this.lock = new Object();
        this.applicationId = applicationId;
        this.state = state;
        this.tokenInfo = tokenInfo;
        this.lastAttemptedTokenExtendDate = lastAttemptedTokenExtendDate;
        this.pendingRequest = pendingRequest;
        this.handler = new Handler(Looper.getMainLooper());
        this.currentTokenRefreshRequest = null;
        this.tokenCachingStrategy = null;
        this.callbacks = new ArrayList<Session$StatusCallback>();
    }
    
    private static boolean areEqual(final Object o, final Object o2) {
        if (o == null) {
            return o2 == null;
        }
        return o.equals(o2);
    }
    
    private void autoPublishAsync() {
        final Session$AutoPublishAsyncTask session$AutoPublishAsyncTask = null;
        // monitorenter(this)
        Session$AutoPublishAsyncTask autoPublishAsyncTask = session$AutoPublishAsyncTask;
        try {
            if (this.autoPublishAsyncTask == null) {
                autoPublishAsyncTask = session$AutoPublishAsyncTask;
                if (Settings.getShouldAutoPublishInstall()) {
                    final String applicationId = this.applicationId;
                    autoPublishAsyncTask = session$AutoPublishAsyncTask;
                    if (applicationId != null) {
                        autoPublishAsyncTask = new Session$AutoPublishAsyncTask(this, applicationId, Session.staticContext);
                        this.autoPublishAsyncTask = autoPublishAsyncTask;
                    }
                }
            }
            // monitorexit(this)
            if (autoPublishAsyncTask != null) {
                autoPublishAsyncTask.execute((Object[])new Void[0]);
            }
        }
        finally {
        }
        // monitorexit(this)
    }
    
    private void finishAuthorization(final AccessToken tokenInfo, final Exception ex) {
        final SessionState state = this.state;
        if (tokenInfo != null) {
            this.saveTokenToCache(this.tokenInfo = tokenInfo);
            this.state = SessionState.OPENED;
        }
        else if (ex != null) {
            this.state = SessionState.CLOSED_LOGIN_FAILED;
        }
        this.pendingRequest = null;
        this.postStateChange(state, this.state, ex);
    }
    
    private void finishReauthorization(final AccessToken tokenInfo, final Exception ex) {
        final SessionState state = this.state;
        if (tokenInfo != null) {
            this.saveTokenToCache(this.tokenInfo = tokenInfo);
            this.state = SessionState.OPENED_TOKEN_UPDATED;
        }
        this.pendingRequest = null;
        this.postStateChange(state, this.state, ex);
    }
    
    public static final Session getActiveSession() {
        synchronized (Session.STATIC_LOCK) {
            return Session.activeSession;
        }
    }
    
    private Intent getLoginActivityIntent(final Session$AuthorizationRequest session$AuthorizationRequest) {
        final Intent intent = new Intent();
        intent.setClass(getStaticContext(), (Class)LoginActivity.class);
        intent.setAction(session$AuthorizationRequest.getLoginBehavior().toString());
        intent.putExtras(LoginActivity.populateIntentExtras(session$AuthorizationRequest.getAuthorizationClientRequest()));
        return intent;
    }
    
    static Context getStaticContext() {
        return Session.staticContext;
    }
    
    private void handleAuthorizationResult(final int n, final AuthorizationClient$Result authorizationClient$Result) {
        AccessToken token;
        Exception ex;
        if (n == -1) {
            if (authorizationClient$Result.code == AuthorizationClient$Result$Code.SUCCESS) {
                token = authorizationClient$Result.token;
                ex = null;
            }
            else {
                ex = new FacebookAuthorizationException(authorizationClient$Result.errorMessage);
                token = null;
            }
        }
        else if (n == 0) {
            ex = new FacebookOperationCanceledException(authorizationClient$Result.errorMessage);
            token = null;
        }
        else {
            ex = null;
            token = null;
        }
        this.authorizationClient = null;
        this.finishAuthOrReauth(token, ex);
    }
    
    static void initializeStaticContext(Context staticContext) {
        if (staticContext != null && Session.staticContext == null) {
            final Context applicationContext = staticContext.getApplicationContext();
            if (applicationContext != null) {
                staticContext = applicationContext;
            }
            Session.staticContext = staticContext;
        }
    }
    
    static boolean isPublishPermission(final String s) {
        return s != null && (s.startsWith("publish") || s.startsWith("manage") || Session.OTHER_PUBLISH_PERMISSIONS.contains(s));
    }
    
    private void open(final Session$OpenRequest session$OpenRequest, final SessionAuthorizationType sessionAuthorizationType) {
        SessionState state = null;
        SessionState state2;
        while (true) {
            this.validatePermissions(session$OpenRequest, sessionAuthorizationType);
            this.validateLoginBehavior(session$OpenRequest);
            Label_0172: {
            Label_0108:
                while (true) {
                    Label_0237: {
                        synchronized (this.lock) {
                            if (this.pendingRequest != null) {
                                this.postStateChange(this.state, this.state, new UnsupportedOperationException("Session: an attempt was made to open a session that has a pending request."));
                                return;
                            }
                            state = this.state;
                            switch (Session$4.$SwitchMap$com$facebook$SessionState[this.state.ordinal()]) {
                                case 2: {
                                    throw new UnsupportedOperationException("Session: an attempt was made to open an already opened session.");
                                }
                                case 1: {
                                    break;
                                }
                                case 3: {
                                    break Label_0172;
                                }
                                default: {
                                    break Label_0237;
                                }
                            }
                        }
                        break Label_0108;
                    }
                    continue;
                }
                state2 = SessionState.OPENING;
                this.state = state2;
                if (session$OpenRequest == null) {
                    throw new IllegalArgumentException("openRequest cannot be null when opening a new Session");
                }
                this.pendingRequest = session$OpenRequest;
                break;
            }
            if (session$OpenRequest != null && !Utility.isNullOrEmpty(session$OpenRequest.getPermissions()) && !Utility.isSubset(session$OpenRequest.getPermissions(), this.getPermissions())) {
                this.pendingRequest = session$OpenRequest;
            }
            if (this.pendingRequest == null) {
                state2 = SessionState.OPENED;
                this.state = state2;
                break;
            }
            state2 = SessionState.OPENING;
            this.state = state2;
            break;
        }
        if (session$OpenRequest != null) {
            this.addCallback(session$OpenRequest.getCallback());
        }
        this.postStateChange(state, state2, null);
        // monitorexit(o)
        if (state2 == SessionState.OPENING) {
            this.authorize(session$OpenRequest);
        }
    }
    
    public static Session openActiveSession(final Activity activity, final boolean b, final Session$StatusCallback callback) {
        return openActiveSession((Context)activity, b, new Session$OpenRequest(activity).setCallback(callback));
    }
    
    public static Session openActiveSession(final Activity activity, final boolean b, final Session$StatusCallback callback, final String s) {
        return openActiveSession((Context)activity, b, new Session$OpenRequest(activity).setCallback(callback), s);
    }
    
    public static Session openActiveSession(final Context context, final Fragment fragment, final boolean b, final Session$StatusCallback callback) {
        return openActiveSession(context, b, new Session$OpenRequest(fragment).setCallback(callback));
    }
    
    private static Session openActiveSession(final Context context, final boolean b, final Session$OpenRequest session$OpenRequest) {
        return openActiveSession(new Session$Builder(context).build(), b, session$OpenRequest);
    }
    
    private static Session openActiveSession(final Context context, final boolean b, final Session$OpenRequest session$OpenRequest, final String applicationId) {
        return openActiveSession(new Session$Builder(context).setApplicationId(applicationId).build(), b, session$OpenRequest);
    }
    
    private static Session openActiveSession(final Session activeSession, final boolean b, final Session$OpenRequest session$OpenRequest) {
        if (SessionState.CREATED_TOKEN_LOADED.equals(activeSession.getState()) || b) {
            setActiveSession(activeSession);
            activeSession.openForRead(session$OpenRequest);
            return activeSession;
        }
        return null;
    }
    
    public static Session openActiveSessionFromCache(final Context context) {
        return openActiveSession(context, false, null);
    }
    
    public static Session openActiveSessionWithAccessToken(final Context context, final AccessToken accessToken, final Session$StatusCallback session$StatusCallback) {
        final Session activeSession = new Session(context, null, null, false);
        setActiveSession(activeSession);
        activeSession.open(accessToken, session$StatusCallback);
        return activeSession;
    }
    
    static void postActiveSessionAction(final String s) {
        LocalBroadcastManager.getInstance(getStaticContext()).sendBroadcast(new Intent(s));
    }
    
    private void readObject(final ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Cannot readObject, serialization proxy required");
    }
    
    private void requestNewPermissions(final Session$NewPermissionsRequest session$NewPermissionsRequest, final SessionAuthorizationType sessionAuthorizationType) {
        this.validatePermissions(session$NewPermissionsRequest, sessionAuthorizationType);
        this.validateLoginBehavior(session$NewPermissionsRequest);
        if (session$NewPermissionsRequest != null) {
            synchronized (this.lock) {
                if (this.pendingRequest != null) {
                    throw new UnsupportedOperationException("Session: an attempt was made to request new permissions for a session that has a pending request.");
                }
            }
            switch (Session$4.$SwitchMap$com$facebook$SessionState[this.state.ordinal()]) {
                case 4:
                case 5: {
                    final Session$AuthorizationRequest pendingRequest;
                    this.pendingRequest = pendingRequest;
                    // monitorexit(sessionAuthorizationType)
                    pendingRequest.setValidateSameFbidAsToken(this.getAccessToken());
                    this.authorize(pendingRequest);
                    break;
                }
                default: {
                    throw new UnsupportedOperationException("Session: an attempt was made to request new permissions for a session that is not currently open.");
                }
            }
        }
    }
    
    private boolean resolveIntent(final Intent intent) {
        return getStaticContext().getPackageManager().resolveActivity(intent, 0) != null;
    }
    
    public static final Session restoreSession(final Context context, final TokenCachingStrategy tokenCachingStrategy, final Session$StatusCallback session$StatusCallback, final Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        final byte[] byteArray = bundle.getByteArray("com.facebook.sdk.Session.saveSessionKey");
        if (byteArray == null) {
            goto Label_0097;
        }
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        try {
            final Session session = (Session)new ObjectInputStream(byteArrayInputStream).readObject();
            initializeStaticContext(context);
            if (tokenCachingStrategy != null) {
                session.tokenCachingStrategy = tokenCachingStrategy;
                if (session$StatusCallback != null) {
                    session.addCallback(session$StatusCallback);
                }
                session.authorizationBundle = bundle.getBundle("com.facebook.sdk.Session.authBundleKey");
                return session;
            }
            goto Label_0099;
        }
        catch (ClassNotFoundException ex) {
            Log.w(Session.TAG, "Unable to restore session", (Throwable)ex);
        }
        catch (IOException ex2) {
            Log.w(Session.TAG, "Unable to restore session.", (Throwable)ex2);
            goto Label_0097;
        }
    }
    
    private static void runWithHandlerOrExecutor(final Handler handler, final Runnable runnable) {
        if (handler != null) {
            handler.post(runnable);
            return;
        }
        Settings.getExecutor().execute(runnable);
    }
    
    public static final void saveSession(final Session session, final Bundle bundle) {
        if (bundle == null || session == null || bundle.containsKey("com.facebook.sdk.Session.saveSessionKey")) {
            return;
        }
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(session);
            bundle.putByteArray("com.facebook.sdk.Session.saveSessionKey", byteArrayOutputStream.toByteArray());
            bundle.putBundle("com.facebook.sdk.Session.authBundleKey", session.authorizationBundle);
        }
        catch (IOException ex) {
            throw new FacebookException("Unable to save session.", ex);
        }
    }
    
    private void saveTokenToCache(final AccessToken accessToken) {
        if (accessToken != null && this.tokenCachingStrategy != null) {
            this.tokenCachingStrategy.save(accessToken.toCacheBundle());
        }
    }
    
    public static final void setActiveSession(final Session activeSession) {
        synchronized (Session.STATIC_LOCK) {
            if (activeSession != Session.activeSession) {
                final Session activeSession2 = Session.activeSession;
                if (activeSession2 != null) {
                    activeSession2.close();
                }
                Session.activeSession = activeSession;
                if (activeSession2 != null) {
                    postActiveSessionAction("com.facebook.sdk.ACTIVE_SESSION_UNSET");
                }
                if (activeSession != null) {
                    postActiveSessionAction("com.facebook.sdk.ACTIVE_SESSION_SET");
                    if (activeSession.isOpened()) {
                        postActiveSessionAction("com.facebook.sdk.ACTIVE_SESSION_OPENED");
                    }
                }
            }
        }
    }
    
    private boolean tryLegacyAuth(final Session$AuthorizationRequest session$AuthorizationRequest) {
        (this.authorizationClient = new AuthorizationClient()).setOnCompletedListener(new Session$2(this));
        this.authorizationClient.setContext(getStaticContext());
        this.authorizationClient.startOrContinueAuth(session$AuthorizationRequest.getAuthorizationClientRequest());
        return true;
    }
    
    private boolean tryLoginActivity(final Session$AuthorizationRequest session$AuthorizationRequest) {
        final Intent loginActivityIntent = this.getLoginActivityIntent(session$AuthorizationRequest);
        if (!this.resolveIntent(loginActivityIntent)) {
            return false;
        }
        try {
            session$AuthorizationRequest.getStartActivityDelegate().startActivityForResult(loginActivityIntent, session$AuthorizationRequest.getRequestCode());
            return true;
        }
        catch (ActivityNotFoundException ex) {
            return false;
        }
    }
    
    private void validateLoginBehavior(final Session$AuthorizationRequest session$AuthorizationRequest) {
        if (session$AuthorizationRequest != null && !session$AuthorizationRequest.isLegacy) {
            final Intent intent = new Intent();
            intent.setClass(getStaticContext(), (Class)LoginActivity.class);
            if (!this.resolveIntent(intent)) {
                throw new FacebookException(String.format("Cannot use SessionLoginBehavior %s when %s is not declared as an activity in AndroidManifest.xml", session$AuthorizationRequest.getLoginBehavior(), LoginActivity.class.getName()));
            }
        }
    }
    
    private void validatePermissions(final Session$AuthorizationRequest session$AuthorizationRequest, final SessionAuthorizationType sessionAuthorizationType) {
        if (session$AuthorizationRequest == null || Utility.isNullOrEmpty(session$AuthorizationRequest.getPermissions())) {
            if (SessionAuthorizationType.PUBLISH.equals(sessionAuthorizationType)) {
                throw new FacebookException("Cannot request publish or manage authorization with no permissions.");
            }
        }
        else {
            for (final String s : session$AuthorizationRequest.getPermissions()) {
                if (isPublishPermission(s)) {
                    if (SessionAuthorizationType.READ.equals(sessionAuthorizationType)) {
                        throw new FacebookException(String.format("Cannot pass a publish or manage permission (%s) to a request for read authorization", s));
                    }
                    continue;
                }
                else {
                    if (!SessionAuthorizationType.PUBLISH.equals(sessionAuthorizationType)) {
                        continue;
                    }
                    Log.w(Session.TAG, String.format("Should not pass a read permission (%s) to a request for publish or manage authorization", s));
                }
            }
        }
    }
    
    private Object writeReplace() {
        return new Session$SerializationProxyV1(this.applicationId, this.state, this.tokenInfo, this.lastAttemptedTokenExtendDate, false, this.pendingRequest);
    }
    
    public final void addCallback(final Session$StatusCallback session$StatusCallback) {
        final List<Session$StatusCallback> callbacks = this.callbacks;
        // monitorenter(callbacks)
        if (session$StatusCallback == null) {
            return;
        }
        try {
            if (!this.callbacks.contains(session$StatusCallback)) {
                this.callbacks.add(session$StatusCallback);
            }
        }
        finally {
        }
        // monitorexit(callbacks)
    }
    
    void authorize(final Session$AuthorizationRequest session$AuthorizationRequest) {
        session$AuthorizationRequest.setApplicationId(this.applicationId);
        this.autoPublishAsync();
        boolean b2;
        final boolean b = b2 = this.tryLoginActivity(session$AuthorizationRequest);
        if (!b) {
            b2 = b;
            if (session$AuthorizationRequest.isLegacy) {
                b2 = this.tryLegacyAuth(session$AuthorizationRequest);
            }
        }
        if (b2) {
            return;
        }
        while (true) {
            while (true) {
                Label_0128: {
                    synchronized (this.lock) {
                        final SessionState state = this.state;
                        switch (Session$4.$SwitchMap$com$facebook$SessionState[this.state.ordinal()]) {
                            case 6:
                            case 7: {
                                return;
                            }
                            default: {
                                break Label_0128;
                            }
                        }
                        this.postStateChange(state, this.state = SessionState.CLOSED_LOGIN_FAILED, new FacebookException("Log in attempt failed."));
                        return;
                    }
                }
                continue;
            }
        }
    }
    
    public final void close() {
        while (true) {
            final SessionState state;
            synchronized (this.lock) {
                state = this.state;
                switch (Session$4.$SwitchMap$com$facebook$SessionState[this.state.ordinal()]) {
                    case 1:
                    case 2: {
                        this.postStateChange(state, this.state = SessionState.CLOSED_LOGIN_FAILED, new FacebookException("Log in attempt aborted."));
                        return;
                    }
                    case 3:
                    case 4:
                    case 5: {
                        break;
                    }
                    default: {
                        return;
                    }
                }
            }
            this.postStateChange(state, this.state = SessionState.CLOSED, null);
        }
    }
    
    public final void closeAndClearTokenInformation() {
        if (this.tokenCachingStrategy != null) {
            this.tokenCachingStrategy.clear();
        }
        Utility.clearFacebookCookies(Session.staticContext);
        this.close();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof Session) {
            final Session session = (Session)o;
            if (areEqual(session.applicationId, this.applicationId) && areEqual(session.authorizationBundle, this.authorizationBundle) && areEqual(session.state, this.state) && areEqual(session.getExpirationDate(), this.getExpirationDate())) {
                return true;
            }
        }
        return false;
    }
    
    void extendAccessToken() {
        Session$TokenRefreshRequest currentTokenRefreshRequest = null;
        synchronized (this.lock) {
            if (this.currentTokenRefreshRequest == null) {
                currentTokenRefreshRequest = new Session$TokenRefreshRequest(this);
                this.currentTokenRefreshRequest = currentTokenRefreshRequest;
            }
            // monitorexit(this.lock)
            if (currentTokenRefreshRequest != null) {
                currentTokenRefreshRequest.bind();
            }
        }
    }
    
    void extendAccessTokenIfNeeded() {
        if (this.shouldExtendAccessToken()) {
            this.extendAccessToken();
        }
    }
    
    void extendTokenCompleted(final Bundle bundle) {
        while (true) {
            while (true) {
                Label_0135: {
                    synchronized (this.lock) {
                        final SessionState state = this.state;
                        switch (Session$4.$SwitchMap$com$facebook$SessionState[this.state.ordinal()]) {
                            case 4: {
                                this.postStateChange(state, this.state = SessionState.OPENED_TOKEN_UPDATED, null);
                            }
                            case 5: {
                                this.tokenInfo = AccessToken.createFromRefresh(this.tokenInfo, bundle);
                                if (this.tokenCachingStrategy != null) {
                                    this.tokenCachingStrategy.save(this.tokenInfo.toCacheBundle());
                                }
                                return;
                            }
                            default: {
                                break Label_0135;
                            }
                        }
                        Log.d(Session.TAG, "refreshToken ignored in state " + this.state);
                        return;
                    }
                }
                continue;
            }
        }
    }
    
    void finishAuthOrReauth(final AccessToken accessToken, final Exception ex) {
        AccessToken accessToken2 = accessToken;
        Exception ex2 = ex;
        if (accessToken != null) {
            accessToken2 = accessToken;
            ex2 = ex;
            if (accessToken.isInvalid()) {
                accessToken2 = null;
                ex2 = new FacebookException("Invalid access token.");
            }
        }
        while (true) {
            synchronized (this.lock) {
                switch (Session$4.$SwitchMap$com$facebook$SessionState[this.state.ordinal()]) {
                    case 2: {
                        this.finishAuthorization(accessToken2, ex2);
                    }
                    case 3: {
                        return;
                    }
                    case 4:
                    case 5: {
                        break;
                    }
                    default: {
                        return;
                    }
                }
            }
            this.finishReauthorization(accessToken2, ex2);
        }
    }
    
    public final String getAccessToken() {
        synchronized (this.lock) {
            String token;
            if (this.tokenInfo == null) {
                token = null;
            }
            else {
                token = this.tokenInfo.getToken();
            }
            return token;
        }
    }
    
    public final String getApplicationId() {
        return this.applicationId;
    }
    
    public final Bundle getAuthorizationBundle() {
        synchronized (this.lock) {
            return this.authorizationBundle;
        }
    }
    
    public final Date getExpirationDate() {
        synchronized (this.lock) {
            Date expires;
            if (this.tokenInfo == null) {
                expires = null;
            }
            else {
                expires = this.tokenInfo.getExpires();
            }
            return expires;
        }
    }
    
    Date getLastAttemptedTokenExtendDate() {
        return this.lastAttemptedTokenExtendDate;
    }
    
    public final List<String> getPermissions() {
        synchronized (this.lock) {
            List<String> permissions;
            if (this.tokenInfo == null) {
                permissions = null;
            }
            else {
                permissions = this.tokenInfo.getPermissions();
            }
            return permissions;
        }
    }
    
    public final SessionState getState() {
        synchronized (this.lock) {
            return this.state;
        }
    }
    
    AccessToken getTokenInfo() {
        return this.tokenInfo;
    }
    
    @Override
    public int hashCode() {
        return 0;
    }
    
    public final boolean isClosed() {
        synchronized (this.lock) {
            return this.state.isClosed();
        }
    }
    
    public final boolean isOpened() {
        synchronized (this.lock) {
            return this.state.isOpened();
        }
    }
    
    public final boolean onActivityResult(Activity lock, final int n, final int n2, final Intent intent) {
        Validate.notNull(lock, "currentActivity");
        initializeStaticContext((Context)lock);
        lock = (Activity)this.lock;
        while (true) {
            Label_0109: {
                synchronized (lock) {
                    if (this.pendingRequest == null || n != this.pendingRequest.getRequestCode()) {
                        return false;
                    }
                    // monitorexit(lock)
                    if (intent == null) {
                        break Label_0109;
                    }
                    lock = (Activity)intent.getSerializableExtra("com.facebook.LoginActivity:Result");
                    if (lock != null) {
                        this.handleAuthorizationResult(n2, (AuthorizationClient$Result)lock);
                        return true;
                    }
                }
                if (this.authorizationClient != null) {
                    final Intent intent2;
                    this.authorizationClient.onActivityResult(n, n2, intent2);
                    return true;
                }
                final Exception ex = null;
                this.finishAuthOrReauth(null, ex);
                return true;
            }
            if (n2 == 0) {
                final Exception ex = new FacebookOperationCanceledException("User canceled operation.");
                continue;
            }
            final Exception ex = null;
            continue;
        }
    }
    
    public final void open(final AccessToken accessToken, final Session$StatusCallback session$StatusCallback) {
        synchronized (this.lock) {
            if (this.pendingRequest != null) {
                throw new UnsupportedOperationException("Session: an attempt was made to open a session that has a pending request.");
            }
        }
        if (this.state != SessionState.CREATED && this.state != SessionState.CREATED_TOKEN_LOADED) {
            throw new UnsupportedOperationException("Session: an attempt was made to open an already opened session.");
        }
        if (session$StatusCallback != null) {
            this.addCallback(session$StatusCallback);
        }
        final AccessToken tokenInfo;
        this.tokenInfo = tokenInfo;
        if (this.tokenCachingStrategy != null) {
            this.tokenCachingStrategy.save(tokenInfo.toCacheBundle());
        }
        this.postStateChange(this.state, this.state = SessionState.OPENED, null);
        // monitorexit(o)
        this.autoPublishAsync();
    }
    
    public final void openForPublish(final Session$OpenRequest session$OpenRequest) {
        this.open(session$OpenRequest, SessionAuthorizationType.PUBLISH);
    }
    
    public final void openForRead(final Session$OpenRequest session$OpenRequest) {
        this.open(session$OpenRequest, SessionAuthorizationType.READ);
    }
    
    void postStateChange(final SessionState sessionState, final SessionState sessionState2, final Exception ex) {
        if (sessionState != sessionState2 || sessionState == SessionState.OPENED_TOKEN_UPDATED || ex != null) {
            if (sessionState2.isClosed()) {
                this.tokenInfo = AccessToken.createEmptyToken(Collections.emptyList());
            }
            synchronized (this.callbacks) {
                runWithHandlerOrExecutor(this.handler, new Session$3(this, sessionState2, ex));
                // monitorexit(this.callbacks)
                if (this != Session.activeSession || sessionState.isOpened() == sessionState2.isOpened()) {
                    return;
                }
                if (sessionState2.isOpened()) {
                    postActiveSessionAction("com.facebook.sdk.ACTIVE_SESSION_OPENED");
                    return;
                }
            }
            postActiveSessionAction("com.facebook.sdk.ACTIVE_SESSION_CLOSED");
        }
    }
    
    public final void removeCallback(final Session$StatusCallback session$StatusCallback) {
        synchronized (this.callbacks) {
            this.callbacks.remove(session$StatusCallback);
        }
    }
    
    public final void requestNewPublishPermissions(final Session$NewPermissionsRequest session$NewPermissionsRequest) {
        this.requestNewPermissions(session$NewPermissionsRequest, SessionAuthorizationType.PUBLISH);
    }
    
    public final void requestNewReadPermissions(final Session$NewPermissionsRequest session$NewPermissionsRequest) {
        this.requestNewPermissions(session$NewPermissionsRequest, SessionAuthorizationType.READ);
    }
    
    void setCurrentTokenRefreshRequest(final Session$TokenRefreshRequest currentTokenRefreshRequest) {
        this.currentTokenRefreshRequest = currentTokenRefreshRequest;
    }
    
    void setLastAttemptedTokenExtendDate(final Date lastAttemptedTokenExtendDate) {
        this.lastAttemptedTokenExtendDate = lastAttemptedTokenExtendDate;
    }
    
    void setTokenInfo(final AccessToken tokenInfo) {
        this.tokenInfo = tokenInfo;
    }
    
    boolean shouldExtendAccessToken() {
        if (this.currentTokenRefreshRequest == null) {
            final Date date = new Date();
            if (this.state.isOpened() && this.tokenInfo.getSource().canExtendToken() && date.getTime() - this.lastAttemptedTokenExtendDate.getTime() > 3600000L && date.getTime() - this.tokenInfo.getLastRefresh().getTime() > 86400000L) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder().append("{Session").append(" state:").append(this.state).append(", token:");
        Serializable tokenInfo;
        if (this.tokenInfo == null) {
            tokenInfo = "null";
        }
        else {
            tokenInfo = this.tokenInfo;
        }
        final StringBuilder append2 = append.append(tokenInfo).append(", appId:");
        String applicationId;
        if (this.applicationId == null) {
            applicationId = "null";
        }
        else {
            applicationId = this.applicationId;
        }
        return append2.append(applicationId).append("}").toString();
    }
}
