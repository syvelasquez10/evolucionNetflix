// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import android.content.SharedPreferences$Editor;
import com.facebook.internal.Utility;
import com.facebook.internal.ServerProtocol;
import android.content.ActivityNotFoundException;
import java.util.Iterator;
import com.facebook.widget.WebDialog;
import android.content.Intent;
import android.app.Activity;
import com.facebook.model.GraphObjectList;
import java.util.Collection;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphMultiResult;
import com.facebook.model.GraphUser;
import android.os.Bundle;
import com.facebook.android.R;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import java.io.Serializable;

class AuthorizationClient implements Serializable
{
    private static final String TAG = "Facebook-AuthorizationClient";
    private static final String WEB_VIEW_AUTH_HANDLER_STORE = "com.facebook.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY";
    private static final String WEB_VIEW_AUTH_HANDLER_TOKEN_KEY = "TOKEN";
    private static final long serialVersionUID = 1L;
    transient BackgroundProcessingListener backgroundProcessingListener;
    transient boolean checkedInternetPermission;
    transient Context context;
    AuthHandler currentHandler;
    List<AuthHandler> handlersToTry;
    transient OnCompletedListener onCompletedListener;
    AuthorizationRequest pendingRequest;
    transient StartActivityDelegate startActivityDelegate;
    
    private void completeWithFailure() {
        this.complete(Result.createErrorResult("Login attempt failed.", null));
    }
    
    private List<AuthHandler> getHandlerTypes(final AuthorizationRequest authorizationRequest) {
        final ArrayList<GetTokenAuthHandler> list = (ArrayList<GetTokenAuthHandler>)new ArrayList<AuthHandler>();
        final SessionLoginBehavior loginBehavior = authorizationRequest.getLoginBehavior();
        if (loginBehavior.allowsKatanaAuth()) {
            if (!authorizationRequest.isLegacy()) {
                list.add(new GetTokenAuthHandler());
                list.add((GetTokenAuthHandler)new KatanaLoginDialogAuthHandler());
            }
            list.add((GetTokenAuthHandler)new KatanaProxyAuthHandler());
        }
        if (loginBehavior.allowsWebViewAuth()) {
            list.add((GetTokenAuthHandler)new WebViewAuthHandler());
        }
        return (List<AuthHandler>)list;
    }
    
    private void notifyBackgroundProcessingStart() {
        if (this.backgroundProcessingListener != null) {
            this.backgroundProcessingListener.onBackgroundProcessingStarted();
        }
    }
    
    private void notifyBackgroundProcessingStop() {
        if (this.backgroundProcessingListener != null) {
            this.backgroundProcessingListener.onBackgroundProcessingStopped();
        }
    }
    
    private void notifyOnCompleteListener(final Result result) {
        if (this.onCompletedListener != null) {
            this.onCompletedListener.onCompleted(result);
        }
    }
    
    void authorize(final AuthorizationRequest pendingRequest) {
        if (pendingRequest != null) {
            if (this.pendingRequest != null) {
                throw new FacebookException("Attempted to authorize while a request is pending.");
            }
            if (!pendingRequest.needsNewTokenValidation() || this.checkInternetPermission()) {
                this.pendingRequest = pendingRequest;
                this.handlersToTry = this.getHandlerTypes(pendingRequest);
                this.tryNextHandler();
            }
        }
    }
    
    void cancelCurrentHandler() {
        if (this.currentHandler != null) {
            this.currentHandler.cancel();
        }
    }
    
    boolean checkInternetPermission() {
        if (this.checkedInternetPermission) {
            return true;
        }
        if (this.checkPermission("android.permission.INTERNET") != 0) {
            this.complete(Result.createErrorResult(this.context.getString(R.string.com_facebook_internet_permission_error_title), this.context.getString(R.string.com_facebook_internet_permission_error_message)));
            return false;
        }
        return this.checkedInternetPermission = true;
    }
    
    int checkPermission(final String s) {
        return this.context.checkCallingOrSelfPermission(s);
    }
    
    void complete(final Result result) {
        this.handlersToTry = null;
        this.currentHandler = null;
        this.pendingRequest = null;
        this.notifyOnCompleteListener(result);
    }
    
    void completeAndValidate(final Result result) {
        if (result.token != null && this.pendingRequest.needsNewTokenValidation()) {
            this.validateSameFbidAndFinish(result);
            return;
        }
        this.complete(result);
    }
    
    void continueAuth() {
        if (this.pendingRequest == null || this.currentHandler == null) {
            throw new FacebookException("Attempted to continue authorization without a pending request.");
        }
        if (this.currentHandler.needsRestart()) {
            this.currentHandler.cancel();
            this.tryCurrentHandler();
        }
    }
    
    Request createGetPermissionsRequest(final String s) {
        final Bundle bundle = new Bundle();
        bundle.putString("fields", "id");
        bundle.putString("access_token", s);
        return new Request(null, "me/permissions", bundle, HttpMethod.GET, null);
    }
    
    Request createGetProfileIdRequest(final String s) {
        final Bundle bundle = new Bundle();
        bundle.putString("fields", "id");
        bundle.putString("access_token", s);
        return new Request(null, "me", bundle, HttpMethod.GET, null);
    }
    
    RequestBatch createReauthValidationBatch(final Result result) {
        final ArrayList list = new ArrayList();
        final ArrayList list2 = new ArrayList();
        final String token = result.token.getToken();
        final Request.Callback callback = new Request.Callback() {
            @Override
            public void onCompleted(final Response response) {
                try {
                    final GraphUser graphUser = response.getGraphObjectAs(GraphUser.class);
                    if (graphUser != null) {
                        list.add(graphUser.getId());
                    }
                }
                catch (Exception ex) {}
            }
        };
        final String previousAccessToken = this.pendingRequest.getPreviousAccessToken();
        final Request getProfileIdRequest = this.createGetProfileIdRequest(previousAccessToken);
        getProfileIdRequest.setCallback((Request.Callback)callback);
        final Request getProfileIdRequest2 = this.createGetProfileIdRequest(token);
        getProfileIdRequest2.setCallback((Request.Callback)callback);
        final Request getPermissionsRequest = this.createGetPermissionsRequest(previousAccessToken);
        getPermissionsRequest.setCallback((Request.Callback)new Request.Callback() {
            @Override
            public void onCompleted(final Response response) {
                try {
                    final GraphMultiResult graphMultiResult = response.getGraphObjectAs(GraphMultiResult.class);
                    if (graphMultiResult != null) {
                        final GraphObjectList<GraphObject> data = graphMultiResult.getData();
                        if (data != null && data.size() == 1) {
                            list2.addAll(((GraphObject)data.get(0)).asMap().keySet());
                        }
                    }
                }
                catch (Exception ex) {}
            }
        });
        final RequestBatch requestBatch = new RequestBatch(new Request[] { getProfileIdRequest, getProfileIdRequest2, getPermissionsRequest });
        requestBatch.setBatchApplicationId(this.pendingRequest.getApplicationId());
        requestBatch.addCallback((RequestBatch.Callback)new RequestBatch.Callback() {
            @Override
            public void onBatchCompleted(final RequestBatch requestBatch) {
                try {
                    Result result;
                    if (list.size() == 2 && list.get(0) != null && list.get(1) != null && list.get(0).equals(list.get(1))) {
                        result = Result.createTokenResult(AccessToken.createFromTokenWithRefreshedPermissions(result.token, list2));
                    }
                    else {
                        result = Result.createErrorResult("User logged in as different Facebook user.", null);
                    }
                    AuthorizationClient.this.complete(result);
                }
                catch (Exception ex) {
                    AuthorizationClient.this.complete(Result.createErrorResult("Caught exception", ex.getMessage()));
                }
                finally {
                    AuthorizationClient.this.notifyBackgroundProcessingStop();
                }
            }
        });
        return requestBatch;
    }
    
    BackgroundProcessingListener getBackgroundProcessingListener() {
        return this.backgroundProcessingListener;
    }
    
    boolean getInProgress() {
        return this.pendingRequest != null && this.currentHandler != null;
    }
    
    OnCompletedListener getOnCompletedListener() {
        return this.onCompletedListener;
    }
    
    StartActivityDelegate getStartActivityDelegate() {
        if (this.startActivityDelegate != null) {
            return this.startActivityDelegate;
        }
        if (this.pendingRequest != null) {
            return (StartActivityDelegate)new StartActivityDelegate() {
                @Override
                public Activity getActivityContext() {
                    return AuthorizationClient.this.pendingRequest.getStartActivityDelegate().getActivityContext();
                }
                
                @Override
                public void startActivityForResult(final Intent intent, final int n) {
                    AuthorizationClient.this.pendingRequest.getStartActivityDelegate().startActivityForResult(intent, n);
                }
            };
        }
        return null;
    }
    
    boolean onActivityResult(final int n, final int n2, final Intent intent) {
        return n == this.pendingRequest.getRequestCode() && this.currentHandler.onActivityResult(n, n2, intent);
    }
    
    void setBackgroundProcessingListener(final BackgroundProcessingListener backgroundProcessingListener) {
        this.backgroundProcessingListener = backgroundProcessingListener;
    }
    
    void setContext(final Activity context) {
        this.context = (Context)context;
        this.startActivityDelegate = (StartActivityDelegate)new StartActivityDelegate() {
            @Override
            public Activity getActivityContext() {
                return context;
            }
            
            @Override
            public void startActivityForResult(final Intent intent, final int n) {
                context.startActivityForResult(intent, n);
            }
        };
    }
    
    void setContext(final Context context) {
        this.context = context;
        this.startActivityDelegate = null;
    }
    
    void setOnCompletedListener(final OnCompletedListener onCompletedListener) {
        this.onCompletedListener = onCompletedListener;
    }
    
    void startOrContinueAuth(final AuthorizationRequest authorizationRequest) {
        if (this.getInProgress()) {
            this.continueAuth();
            return;
        }
        this.authorize(authorizationRequest);
    }
    
    boolean tryCurrentHandler() {
        return (!this.currentHandler.needsInternetPermission() || this.checkInternetPermission()) && this.currentHandler.tryAuthorize(this.pendingRequest);
    }
    
    void tryNextHandler() {
        while (this.handlersToTry != null && !this.handlersToTry.isEmpty()) {
            this.currentHandler = this.handlersToTry.remove(0);
            if (this.tryCurrentHandler()) {
                return;
            }
        }
        if (this.pendingRequest != null) {
            this.completeWithFailure();
        }
    }
    
    void validateSameFbidAndFinish(final Result result) {
        if (result.token == null) {
            throw new FacebookException("Can't validate without a token");
        }
        final RequestBatch reauthValidationBatch = this.createReauthValidationBatch(result);
        this.notifyBackgroundProcessingStart();
        reauthValidationBatch.executeAsync();
    }
    
    static class AuthDialogBuilder extends Builder
    {
        private static final String OAUTH_DIALOG = "oauth";
        static final String REDIRECT_URI = "fbconnect://success";
        
        public AuthDialogBuilder(final Context context, final String s, final Bundle bundle) {
            super(context, s, "oauth", bundle);
        }
        
        @Override
        public WebDialog build() {
            final Bundle parameters = ((WebDialog.BuilderBase)this).getParameters();
            parameters.putString("redirect_uri", "fbconnect://success");
            parameters.putString("client_id", ((WebDialog.BuilderBase)this).getApplicationId());
            return new WebDialog(((WebDialog.BuilderBase)this).getContext(), "oauth", parameters, ((WebDialog.BuilderBase)this).getTheme(), ((WebDialog.BuilderBase)this).getListener());
        }
    }
    
    abstract class AuthHandler implements Serializable
    {
        private static final long serialVersionUID = 1L;
        
        void cancel() {
        }
        
        boolean needsInternetPermission() {
            return false;
        }
        
        boolean needsRestart() {
            return false;
        }
        
        boolean onActivityResult(final int n, final int n2, final Intent intent) {
            return false;
        }
        
        abstract boolean tryAuthorize(final AuthorizationRequest p0);
    }
    
    static class AuthorizationRequest implements Serializable
    {
        private static final long serialVersionUID = 1L;
        private String applicationId;
        private SessionDefaultAudience defaultAudience;
        private boolean isLegacy;
        private SessionLoginBehavior loginBehavior;
        private List<String> permissions;
        private String previousAccessToken;
        private int requestCode;
        private final transient StartActivityDelegate startActivityDelegate;
        
        AuthorizationRequest(final SessionLoginBehavior loginBehavior, final int requestCode, final boolean isLegacy, final List<String> permissions, final SessionDefaultAudience defaultAudience, final String applicationId, final String previousAccessToken, final StartActivityDelegate startActivityDelegate) {
            this.isLegacy = false;
            this.loginBehavior = loginBehavior;
            this.requestCode = requestCode;
            this.isLegacy = isLegacy;
            this.permissions = permissions;
            this.defaultAudience = defaultAudience;
            this.applicationId = applicationId;
            this.previousAccessToken = previousAccessToken;
            this.startActivityDelegate = startActivityDelegate;
        }
        
        String getApplicationId() {
            return this.applicationId;
        }
        
        SessionDefaultAudience getDefaultAudience() {
            return this.defaultAudience;
        }
        
        SessionLoginBehavior getLoginBehavior() {
            return this.loginBehavior;
        }
        
        List<String> getPermissions() {
            return this.permissions;
        }
        
        String getPreviousAccessToken() {
            return this.previousAccessToken;
        }
        
        int getRequestCode() {
            return this.requestCode;
        }
        
        StartActivityDelegate getStartActivityDelegate() {
            return this.startActivityDelegate;
        }
        
        boolean isLegacy() {
            return this.isLegacy;
        }
        
        boolean needsNewTokenValidation() {
            return this.previousAccessToken != null && !this.isLegacy;
        }
        
        void setIsLegacy(final boolean isLegacy) {
            this.isLegacy = isLegacy;
        }
        
        void setPermissions(final List<String> permissions) {
            this.permissions = permissions;
        }
    }
    
    interface BackgroundProcessingListener
    {
        void onBackgroundProcessingStarted();
        
        void onBackgroundProcessingStopped();
    }
    
    class GetTokenAuthHandler extends AuthHandler
    {
        private static final long serialVersionUID = 1L;
        private transient GetTokenClient getTokenClient;
        
        @Override
        void cancel() {
            if (this.getTokenClient != null) {
                this.getTokenClient.cancel();
                this.getTokenClient = null;
            }
        }
        
        void getTokenCompleted(final AuthorizationRequest authorizationRequest, final Bundle bundle) {
            this.getTokenClient = null;
            AuthorizationClient.this.notifyBackgroundProcessingStop();
            if (bundle != null) {
                final ArrayList stringArrayList = bundle.getStringArrayList("com.facebook.platform.extra.PERMISSIONS");
                final List<String> permissions = authorizationRequest.getPermissions();
                if (stringArrayList != null && (permissions == null || stringArrayList.containsAll(permissions))) {
                    AuthorizationClient.this.completeAndValidate(Result.createTokenResult(AccessToken.createFromNativeLogin(bundle, AccessTokenSource.FACEBOOK_APPLICATION_SERVICE)));
                    return;
                }
                final ArrayList<String> permissions2 = new ArrayList<String>();
                for (final String s : permissions) {
                    if (!stringArrayList.contains(s)) {
                        permissions2.add(s);
                    }
                }
                authorizationRequest.setPermissions(permissions2);
            }
            AuthorizationClient.this.tryNextHandler();
        }
        
        @Override
        boolean tryAuthorize(final AuthorizationRequest authorizationRequest) {
            this.getTokenClient = new GetTokenClient(AuthorizationClient.this.context, authorizationRequest.getApplicationId());
            if (!this.getTokenClient.start()) {
                return false;
            }
            AuthorizationClient.this.notifyBackgroundProcessingStart();
            this.getTokenClient.setCompletedListener((GetTokenClient.CompletedListener)new GetTokenClient.CompletedListener() {
                @Override
                public void completed(final Bundle bundle) {
                    GetTokenAuthHandler.this.getTokenCompleted(authorizationRequest, bundle);
                }
            });
            return true;
        }
    }
    
    abstract class KatanaAuthHandler extends AuthHandler
    {
        private static final long serialVersionUID = 1L;
        
        protected boolean tryIntent(final Intent intent, final int n) {
            if (intent == null) {
                return false;
            }
            try {
                AuthorizationClient.this.getStartActivityDelegate().startActivityForResult(intent, n);
                return true;
            }
            catch (ActivityNotFoundException ex) {
                return false;
            }
        }
    }
    
    class KatanaLoginDialogAuthHandler extends KatanaAuthHandler
    {
        private static final long serialVersionUID = 1L;
        
        private Result handleResultOk(final Intent intent) {
            final Result result = null;
            final Bundle extras = intent.getExtras();
            final String string = extras.getString("com.facebook.platform.status.ERROR_TYPE");
            Serializable tokenResult;
            if (string == null) {
                tokenResult = Result.createTokenResult(AccessToken.createFromNativeLogin(extras, AccessTokenSource.FACEBOOK_APPLICATION_NATIVE));
            }
            else {
                tokenResult = result;
                if (!"ServiceDisabled".equals(string)) {
                    if ("UserCanceled".equals(string)) {
                        return Result.createCancelResult(null);
                    }
                    return Result.createErrorResult(string, extras.getString("error_description"));
                }
            }
            return (Result)tokenResult;
        }
        
        @Override
        boolean onActivityResult(final int n, final int n2, final Intent intent) {
            Serializable s;
            if (intent == null) {
                s = Result.createCancelResult("Operation canceled");
            }
            else if (NativeProtocol.isServiceDisabledResult20121101(intent)) {
                s = null;
            }
            else if (n2 == 0) {
                s = Result.createCancelResult(intent.getStringExtra("com.facebook.platform.status.ERROR_DESCRIPTION"));
            }
            else if (n2 != -1) {
                s = Result.createErrorResult("Unexpected resultCode from authorization.", null);
            }
            else {
                s = this.handleResultOk(intent);
            }
            if (s != null) {
                AuthorizationClient.this.completeAndValidate((Result)s);
            }
            else {
                AuthorizationClient.this.tryNextHandler();
            }
            return true;
        }
        
        @Override
        boolean tryAuthorize(final AuthorizationRequest authorizationRequest) {
            return ((KatanaAuthHandler)this).tryIntent(NativeProtocol.createLoginDialog20121101Intent(AuthorizationClient.this.context, authorizationRequest.getApplicationId(), new ArrayList<String>(authorizationRequest.getPermissions()), authorizationRequest.getDefaultAudience().getNativeProtocolAudience()), authorizationRequest.getRequestCode());
        }
    }
    
    class KatanaProxyAuthHandler extends KatanaAuthHandler
    {
        private static final long serialVersionUID = 1L;
        
        private Result handleResultOk(final Intent intent) {
            Serializable tokenResult = null;
            final Bundle extras = intent.getExtras();
            String s;
            if ((s = extras.getString("error")) == null) {
                s = extras.getString("error_type");
            }
            if (s == null) {
                tokenResult = Result.createTokenResult(AccessToken.createFromWebBundle(AuthorizationClient.this.pendingRequest.getPermissions(), extras, AccessTokenSource.FACEBOOK_APPLICATION_WEB));
            }
            else if (!ServerProtocol.errorsProxyAuthDisabled.contains(s)) {
                if (ServerProtocol.errorsUserCanceled.contains(s)) {
                    return Result.createCancelResult(null);
                }
                return Result.createErrorResult(s, extras.getString("error_description"));
            }
            return (Result)tokenResult;
        }
        
        @Override
        boolean onActivityResult(final int n, final int n2, final Intent intent) {
            Serializable s;
            if (intent == null) {
                s = Result.createCancelResult("Operation canceled");
            }
            else if (n2 == 0) {
                s = Result.createCancelResult(intent.getStringExtra("error"));
            }
            else if (n2 != -1) {
                s = Result.createErrorResult("Unexpected resultCode from authorization.", null);
            }
            else {
                s = this.handleResultOk(intent);
            }
            if (s != null) {
                AuthorizationClient.this.completeAndValidate((Result)s);
            }
            else {
                AuthorizationClient.this.tryNextHandler();
            }
            return true;
        }
        
        @Override
        boolean tryAuthorize(final AuthorizationRequest authorizationRequest) {
            return ((KatanaAuthHandler)this).tryIntent(NativeProtocol.createProxyAuthIntent(AuthorizationClient.this.context, authorizationRequest.getApplicationId(), authorizationRequest.getPermissions()), authorizationRequest.getRequestCode());
        }
    }
    
    interface OnCompletedListener
    {
        void onCompleted(final Result p0);
    }
    
    static class Result implements Serializable
    {
        private static final long serialVersionUID = 1L;
        final Code code;
        final String errorMessage;
        final AccessToken token;
        
        private Result(final Code code, final AccessToken token, final String errorMessage) {
            this.token = token;
            this.errorMessage = errorMessage;
            this.code = code;
        }
        
        static Result createCancelResult(final String s) {
            return new Result(Code.CANCEL, null, s);
        }
        
        static Result createErrorResult(final String s, final String s2) {
            String string = s;
            if (s2 != null) {
                string = s + ": " + s2;
            }
            return new Result(Code.ERROR, null, string);
        }
        
        static Result createTokenResult(final AccessToken accessToken) {
            return new Result(Code.SUCCESS, accessToken, null);
        }
        
        enum Code
        {
            CANCEL, 
            ERROR, 
            SUCCESS;
        }
    }
    
    interface StartActivityDelegate
    {
        Activity getActivityContext();
        
        void startActivityForResult(final Intent p0, final int p1);
    }
    
    class WebViewAuthHandler extends AuthHandler
    {
        private static final long serialVersionUID = 1L;
        private transient WebDialog loginDialog;
        
        private String loadCookieToken() {
            return ((Context)AuthorizationClient.this.getStartActivityDelegate().getActivityContext()).getSharedPreferences("com.facebook.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).getString("TOKEN", "");
        }
        
        private void saveCookieToken(final String s) {
            final SharedPreferences$Editor edit = ((Context)AuthorizationClient.this.getStartActivityDelegate().getActivityContext()).getSharedPreferences("com.facebook.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).edit();
            edit.putString("TOKEN", s);
            if (!edit.commit()) {
                Utility.logd("Facebook-AuthorizationClient", "Could not update saved web view auth handler token.");
            }
        }
        
        @Override
        void cancel() {
            if (this.loginDialog != null) {
                this.loginDialog.dismiss();
                this.loginDialog = null;
            }
        }
        
        @Override
        boolean needsInternetPermission() {
            return true;
        }
        
        @Override
        boolean needsRestart() {
            return true;
        }
        
        void onWebDialogComplete(final AuthorizationRequest authorizationRequest, final Bundle bundle, final FacebookException ex) {
            Result result;
            if (bundle != null) {
                final AccessToken fromWebBundle = AccessToken.createFromWebBundle(authorizationRequest.getPermissions(), bundle, AccessTokenSource.WEB_VIEW);
                result = Result.createTokenResult(fromWebBundle);
                CookieSyncManager.createInstance(AuthorizationClient.this.context).sync();
                this.saveCookieToken(fromWebBundle.getToken());
            }
            else if (ex instanceof FacebookOperationCanceledException) {
                result = Result.createCancelResult("User canceled log in.");
            }
            else {
                result = Result.createErrorResult(ex.getMessage(), null);
            }
            AuthorizationClient.this.completeAndValidate(result);
        }
        
        @Override
        boolean tryAuthorize(final AuthorizationRequest authorizationRequest) {
            final String applicationId = authorizationRequest.getApplicationId();
            final Bundle bundle = new Bundle();
            if (!Utility.isNullOrEmpty(authorizationRequest.getPermissions())) {
                bundle.putString("scope", TextUtils.join((CharSequence)",", (Iterable)authorizationRequest.getPermissions()));
            }
            final String previousAccessToken = authorizationRequest.getPreviousAccessToken();
            if (!Utility.isNullOrEmpty(previousAccessToken) && previousAccessToken.equals(this.loadCookieToken())) {
                bundle.putString("access_token", previousAccessToken);
            }
            else {
                Utility.clearFacebookCookies(AuthorizationClient.this.context);
            }
            (this.loginDialog = ((WebDialog.Builder)new AuthDialogBuilder((Context)AuthorizationClient.this.getStartActivityDelegate().getActivityContext(), applicationId, bundle).setOnCompleteListener(new WebDialog.OnCompleteListener() {
                @Override
                public void onComplete(final Bundle bundle, final FacebookException ex) {
                    WebViewAuthHandler.this.onWebDialogComplete(authorizationRequest, bundle, ex);
                }
            })).build()).show();
            return true;
        }
    }
}
