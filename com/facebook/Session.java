// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.support.v4.app.Fragment;
import java.util.HashMap;
import java.util.UUID;
import java.util.Collections;
import android.util.Log;
import android.content.ActivityNotFoundException;
import android.support.v4.content.LocalBroadcastManager;
import android.app.Activity;
import java.util.Collection;
import com.facebook.internal.SessionAuthorizationType;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;
import com.facebook.model.GraphObjectList;
import java.util.Map;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphMultiResult;
import android.content.Intent;
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
    private static final Set<String> OTHER_PUBLISH_PERMISSIONS;
    private static final Object STATIC_LOCK;
    public static final String TAG;
    private static Session activeSession;
    private static volatile Context staticContext;
    private AppEventsLogger appEventsLogger;
    private final String applicationId;
    private volatile Bundle authorizationBundle;
    private AuthorizationClient authorizationClient;
    private Session$AutoPublishAsyncTask autoPublishAsyncTask;
    private final List<Session$StatusCallback> callbacks;
    private volatile Session$TokenRefreshRequest currentTokenRefreshRequest;
    private final Handler handler;
    private Date lastAttemptedTokenExtendDate;
    private final Object lock;
    private Session$AuthorizationRequest pendingAuthorizationRequest;
    private SessionState state;
    private TokenCachingStrategy tokenCachingStrategy;
    private AccessToken tokenInfo;
    
    static {
        TAG = Session.class.getCanonicalName();
        STATIC_LOCK = new Object();
        OTHER_PUBLISH_PERMISSIONS = new Session$1();
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
        this.pendingAuthorizationRequest = null;
        this.callbacks = new ArrayList<Session$StatusCallback>();
        this.handler = new Handler(Looper.getMainLooper());
        Bundle load = bundle;
        if (b) {
            load = tokenCachingStrategy2.load();
        }
        if (!TokenCachingStrategy.hasTokenInformation(load)) {
            this.tokenInfo = AccessToken.createEmptyToken();
            return;
        }
        final Date date = TokenCachingStrategy.getDate(load, "com.facebook.TokenCachingStrategy.ExpirationDate");
        final Date date2 = new Date();
        if (date == null || date.before(date2)) {
            tokenCachingStrategy2.clear();
            this.tokenInfo = AccessToken.createEmptyToken();
            return;
        }
        this.tokenInfo = AccessToken.createFromCache(load);
        this.state = SessionState.CREATED_TOKEN_LOADED;
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
        this.pendingAuthorizationRequest = null;
        this.postStateChange(state, this.state, ex);
    }
    
    private void finishReauthorization(final AccessToken tokenInfo, final Exception ex) {
        final SessionState state = this.state;
        if (tokenInfo != null) {
            this.saveTokenToCache(this.tokenInfo = tokenInfo);
            this.state = SessionState.OPENED_TOKEN_UPDATED;
        }
        this.pendingAuthorizationRequest = null;
        this.postStateChange(state, this.state, ex);
    }
    
    public static final Session getActiveSession() {
        synchronized (Session.STATIC_LOCK) {
            return Session.activeSession;
        }
    }
    
    private AppEventsLogger getAppEventsLogger() {
        synchronized (this.lock) {
            if (this.appEventsLogger == null) {
                this.appEventsLogger = AppEventsLogger.newLogger(Session.staticContext, this.applicationId);
            }
            return this.appEventsLogger;
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
        this.logAuthorizationComplete(authorizationClient$Result.code, authorizationClient$Result.loggingExtras, ex);
        this.authorizationClient = null;
        this.finishAuthOrReauth(token, ex);
    }
    
    static Session$PermissionsPair handlePermissionResponse(final Response response) {
        if (response.getError() != null) {
            return null;
        }
        final GraphMultiResult graphMultiResult = response.getGraphObjectAs(GraphMultiResult.class);
        if (graphMultiResult == null) {
            return null;
        }
        final GraphObjectList<GraphObject> data = graphMultiResult.getData();
        if (data == null || data.size() == 0) {
            return null;
        }
        final ArrayList list = new ArrayList<String>(data.size());
        final ArrayList list2 = new ArrayList<String>(data.size());
        final GraphObject graphObject = data.get(0);
        if (graphObject.getProperty("permission") != null) {
            for (final GraphObject graphObject2 : data) {
                final String s = (String)graphObject2.getProperty("permission");
                if (!s.equals("installed")) {
                    final String s2 = (String)graphObject2.getProperty("status");
                    if (s2.equals("granted")) {
                        list.add(s);
                    }
                    else {
                        if (!s2.equals("declined")) {
                            continue;
                        }
                        list2.add(s);
                    }
                }
            }
        }
        else {
            for (final Map.Entry<String, Object> entry : graphObject.asMap().entrySet()) {
                if (!entry.getKey().equals("installed") && entry.getValue() == 1) {
                    list.add(entry.getKey());
                }
            }
        }
        return new Session$PermissionsPair((List<String>)list, (List<String>)list2);
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
    
    public static boolean isPublishPermission(final String s) {
        return s != null && (s.startsWith("publish") || s.startsWith("manage") || Session.OTHER_PUBLISH_PERMISSIONS.contains(s));
    }
    
    private void logAuthorizationComplete(AuthorizationClient$Result$Code o, final Map<String, String> map, final Exception ex) {
        Bundle authorizationLoggingBundle;
        if (this.pendingAuthorizationRequest == null) {
            authorizationLoggingBundle = AuthorizationClient.newAuthorizationLoggingBundle("");
            authorizationLoggingBundle.putString("2_result", AuthorizationClient$Result$Code.ERROR.getLoggingValue());
            authorizationLoggingBundle.putString("5_error_message", "Unexpected call to logAuthorizationComplete with null pendingAuthorizationRequest.");
        }
        else {
            final Bundle authorizationLoggingBundle2 = AuthorizationClient.newAuthorizationLoggingBundle(this.pendingAuthorizationRequest.getAuthId());
            if (o != null) {
                authorizationLoggingBundle2.putString("2_result", ((AuthorizationClient$Result$Code)o).getLoggingValue());
            }
            if (ex != null && ex.getMessage() != null) {
                authorizationLoggingBundle2.putString("5_error_message", ex.getMessage());
            }
            if (!this.pendingAuthorizationRequest.loggingExtras.isEmpty()) {
                o = new JSONObject(this.pendingAuthorizationRequest.loggingExtras);
            }
            else {
                o = null;
            }
            Object o2 = o;
            Label_0216: {
                if (map != null) {
                    if (o == null) {
                        o = new JSONObject();
                    }
                    Label_0238: {
                        try {
                            for (final Map.Entry<String, String> entry : map.entrySet()) {
                                ((JSONObject)o).put((String)entry.getKey(), (Object)entry.getValue());
                            }
                            break Label_0238;
                        }
                        catch (JSONException ex2) {
                            o2 = o;
                        }
                        break Label_0216;
                    }
                    o2 = o;
                }
            }
            if (o2 != null) {
                authorizationLoggingBundle2.putString("6_extras", ((JSONObject)o2).toString());
            }
            authorizationLoggingBundle = authorizationLoggingBundle2;
        }
        authorizationLoggingBundle.putLong("1_timestamp_ms", System.currentTimeMillis());
        this.getAppEventsLogger().logSdkEvent("fb_mobile_login_complete", null, authorizationLoggingBundle);
    }
    
    private void logAuthorizationStart() {
        final Bundle authorizationLoggingBundle = AuthorizationClient.newAuthorizationLoggingBundle(this.pendingAuthorizationRequest.getAuthId());
        authorizationLoggingBundle.putLong("1_timestamp_ms", System.currentTimeMillis());
        while (true) {
            try {
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("login_behavior", (Object)this.pendingAuthorizationRequest.loginBehavior.toString());
                jsonObject.put("request_code", this.pendingAuthorizationRequest.requestCode);
                jsonObject.put("is_legacy", this.pendingAuthorizationRequest.isLegacy);
                jsonObject.put("permissions", (Object)TextUtils.join((CharSequence)",", (Iterable)this.pendingAuthorizationRequest.permissions));
                jsonObject.put("default_audience", (Object)this.pendingAuthorizationRequest.defaultAudience.toString());
                authorizationLoggingBundle.putString("6_extras", jsonObject.toString());
                this.getAppEventsLogger().logSdkEvent("fb_mobile_login_start", null, authorizationLoggingBundle);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
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
                            if (this.pendingAuthorizationRequest != null) {
                                this.postStateChange(this.state, this.state, new UnsupportedOperationException("Session: an attempt was made to open a session that has a pending request."));
                                return;
                            }
                            state = this.state;
                            switch (Session$5.$SwitchMap$com$facebook$SessionState[this.state.ordinal()]) {
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
                this.pendingAuthorizationRequest = session$OpenRequest;
                break;
            }
            if (session$OpenRequest != null && !Utility.isNullOrEmpty(session$OpenRequest.getPermissions()) && !Utility.isSubset(session$OpenRequest.getPermissions(), this.getPermissions())) {
                this.pendingAuthorizationRequest = session$OpenRequest;
            }
            if (this.pendingAuthorizationRequest == null) {
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
    
    public static Session openActiveSession(final Activity activity, final boolean b, final Session$StatusCallback callback, final String applicationId) {
        return openActiveSession((Context)activity, b, new Session$OpenRequest(activity).setCallback(callback), new Session$Builder((Context)activity).setApplicationId(applicationId).build());
    }
    
    private static Session openActiveSession(final Context context, final boolean b, final Session$OpenRequest session$OpenRequest) {
        return openActiveSession(context, b, session$OpenRequest, null);
    }
    
    private static Session openActiveSession(final Context context, final boolean b, final Session$OpenRequest session$OpenRequest, final Session session) {
        Session build = session;
        if (session == null) {
            build = new Session$Builder(context).build();
        }
        if (SessionState.CREATED_TOKEN_LOADED.equals(build.getState()) || b) {
            setActiveSession(build);
            build.openForRead(session$OpenRequest);
            return build;
        }
        return null;
    }
    
    public static Session openActiveSessionFromCache(final Context context) {
        return openActiveSession(context, false, null);
    }
    
    static void postActiveSessionAction(final String s) {
        LocalBroadcastManager.getInstance(getStaticContext()).sendBroadcast(new Intent(s));
    }
    
    private boolean resolveIntent(final Intent intent) {
        return getStaticContext().getPackageManager().resolveActivity(intent, 0) != null;
    }
    
    private static void runWithHandlerOrExecutor(final Handler handler, final Runnable runnable) {
        if (handler != null) {
            handler.post(runnable);
            return;
        }
        Settings.getExecutor().execute(runnable);
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
    
    private void tryLegacyAuth(final Session$AuthorizationRequest session$AuthorizationRequest) {
        (this.authorizationClient = new AuthorizationClient()).setOnCompletedListener(new Session$3(this));
        this.authorizationClient.setContext(getStaticContext());
        this.authorizationClient.startOrContinueAuth(session$AuthorizationRequest.getAuthorizationClientRequest());
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
        this.logAuthorizationStart();
        int tryLoginActivity = this.tryLoginActivity(session$AuthorizationRequest) ? 1 : 0;
        Object access$500 = this.pendingAuthorizationRequest.loggingExtras;
        while (true) {
            Label_0194: {
                while (true) {
                    final String s;
                    Label_0039: {
                        if (tryLoginActivity != 0) {
                            s = "1";
                            break Label_0039;
                        }
                        Label_0179: {
                            break Label_0179;
                            while (true) {
                                while (true) {
                                    Label_0197: {
                                        synchronized (this.lock) {
                                            final SessionState state = this.state;
                                            switch (Session$5.$SwitchMap$com$facebook$SessionState[this.state.ordinal()]) {
                                                case 6:
                                                case 7: {
                                                    return;
                                                }
                                                default: {
                                                    break Label_0197;
                                                }
                                            }
                                            return;
                                            this.state = SessionState.CLOSED_LOGIN_FAILED;
                                            access$500 = new FacebookException("Log in attempt failed: LoginActivity could not be started, and not legacy request");
                                            this.logAuthorizationComplete(AuthorizationClient$Result$Code.ERROR, null, (Exception)access$500);
                                            this.postStateChange(state, this.state, (Exception)access$500);
                                            return;
                                            break;
                                        }
                                        break Label_0194;
                                    }
                                    continue;
                                }
                            }
                        }
                    }
                    ((Map<String, String>)access$500).put("try_login_activity", s);
                    if (tryLoginActivity == 0 && session$AuthorizationRequest.isLegacy) {
                        this.pendingAuthorizationRequest.loggingExtras.put("try_legacy", "1");
                        this.tryLegacyAuth(session$AuthorizationRequest);
                        tryLoginActivity = 1;
                    }
                    if (tryLoginActivity == 0) {
                        continue;
                    }
                    break;
                }
                return;
            }
            continue;
        }
    }
    
    public final void close() {
        while (true) {
            final SessionState state;
            synchronized (this.lock) {
                state = this.state;
                switch (Session$5.$SwitchMap$com$facebook$SessionState[this.state.ordinal()]) {
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
        Utility.clearCaches(Session.staticContext);
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
                        switch (Session$5.$SwitchMap$com$facebook$SessionState[this.state.ordinal()]) {
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
            Label_0124: {
                synchronized (this.lock) {
                    switch (Session$5.$SwitchMap$com$facebook$SessionState[this.state.ordinal()]) {
                        case 2: {
                            this.finishAuthorization(accessToken2, ex2);
                            return;
                        }
                        case 4:
                        case 5: {
                            break;
                        }
                        case 1:
                        case 3:
                        case 6:
                        case 7: {
                            break Label_0124;
                        }
                        default: {
                            return;
                        }
                    }
                }
                this.finishReauthorization(accessToken2, ex2);
                return;
            }
            Log.d(Session.TAG, "Unexpected call to finishAuthOrReauth in state " + this.state);
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
            final AuthorizationClient$Result$Code error;
            Label_0147: {
                synchronized (lock) {
                    if (this.pendingAuthorizationRequest == null || n != this.pendingAuthorizationRequest.getRequestCode()) {
                        return false;
                    }
                    // monitorexit(lock)
                    error = AuthorizationClient$Result$Code.ERROR;
                    if (intent == null) {
                        break Label_0147;
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
                final FacebookOperationCanceledException ex = null;
                final AuthorizationClient$Result$Code cancel = error;
                FacebookException ex2 = ex;
                if (ex == null) {
                    ex2 = new FacebookException("Unexpected call to Session.onActivityResult");
                }
                this.logAuthorizationComplete(cancel, null, ex2);
                this.finishAuthOrReauth(null, ex2);
                return true;
            }
            if (n2 == 0) {
                final FacebookOperationCanceledException ex = new FacebookOperationCanceledException("User canceled operation.");
                final AuthorizationClient$Result$Code cancel = AuthorizationClient$Result$Code.CANCEL;
                continue;
            }
            final FacebookOperationCanceledException ex = null;
            final AuthorizationClient$Result$Code cancel = error;
            continue;
        }
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
                this.tokenInfo = AccessToken.createEmptyToken();
            }
            runWithHandlerOrExecutor(this.handler, new Session$4(this, sessionState2, ex));
            if (this == Session.activeSession && sessionState.isOpened() != sessionState2.isOpened()) {
                if (sessionState2.isOpened()) {
                    postActiveSessionAction("com.facebook.sdk.ACTIVE_SESSION_OPENED");
                    return;
                }
                postActiveSessionAction("com.facebook.sdk.ACTIVE_SESSION_CLOSED");
            }
        }
    }
    
    public final void removeCallback(final Session$StatusCallback session$StatusCallback) {
        synchronized (this.callbacks) {
            this.callbacks.remove(session$StatusCallback);
        }
    }
    
    void setLastAttemptedTokenExtendDate(final Date lastAttemptedTokenExtendDate) {
        this.lastAttemptedTokenExtendDate = lastAttemptedTokenExtendDate;
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
