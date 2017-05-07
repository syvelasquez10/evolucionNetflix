// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.android.R$string;
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
    transient AuthorizationClient$BackgroundProcessingListener backgroundProcessingListener;
    transient boolean checkedInternetPermission;
    transient Context context;
    AuthorizationClient$AuthHandler currentHandler;
    List<AuthorizationClient$AuthHandler> handlersToTry;
    transient AuthorizationClient$OnCompletedListener onCompletedListener;
    AuthorizationClient$AuthorizationRequest pendingRequest;
    transient AuthorizationClient$StartActivityDelegate startActivityDelegate;
    
    private void completeWithFailure() {
        this.complete(AuthorizationClient$Result.createErrorResult("Login attempt failed.", null));
    }
    
    private List<AuthorizationClient$AuthHandler> getHandlerTypes(final AuthorizationClient$AuthorizationRequest authorizationClient$AuthorizationRequest) {
        final ArrayList<AuthorizationClient$GetTokenAuthHandler> list = (ArrayList<AuthorizationClient$GetTokenAuthHandler>)new ArrayList<AuthorizationClient$AuthHandler>();
        final SessionLoginBehavior loginBehavior = authorizationClient$AuthorizationRequest.getLoginBehavior();
        if (loginBehavior.allowsKatanaAuth()) {
            if (!authorizationClient$AuthorizationRequest.isLegacy()) {
                list.add(new AuthorizationClient$GetTokenAuthHandler(this));
                list.add((AuthorizationClient$GetTokenAuthHandler)new AuthorizationClient$KatanaLoginDialogAuthHandler(this));
            }
            list.add((AuthorizationClient$GetTokenAuthHandler)new AuthorizationClient$KatanaProxyAuthHandler(this));
        }
        if (loginBehavior.allowsWebViewAuth()) {
            list.add((AuthorizationClient$GetTokenAuthHandler)new AuthorizationClient$WebViewAuthHandler(this));
        }
        return (List<AuthorizationClient$AuthHandler>)list;
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
    
    private void notifyOnCompleteListener(final AuthorizationClient$Result authorizationClient$Result) {
        if (this.onCompletedListener != null) {
            this.onCompletedListener.onCompleted(authorizationClient$Result);
        }
    }
    
    void authorize(final AuthorizationClient$AuthorizationRequest pendingRequest) {
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
            this.complete(AuthorizationClient$Result.createErrorResult(this.context.getString(R$string.com_facebook_internet_permission_error_title), this.context.getString(R$string.com_facebook_internet_permission_error_message)));
            return false;
        }
        return this.checkedInternetPermission = true;
    }
    
    int checkPermission(final String s) {
        return this.context.checkCallingOrSelfPermission(s);
    }
    
    void complete(final AuthorizationClient$Result authorizationClient$Result) {
        this.handlersToTry = null;
        this.currentHandler = null;
        this.pendingRequest = null;
        this.notifyOnCompleteListener(authorizationClient$Result);
    }
    
    void completeAndValidate(final AuthorizationClient$Result authorizationClient$Result) {
        if (authorizationClient$Result.token != null && this.pendingRequest.needsNewTokenValidation()) {
            this.validateSameFbidAndFinish(authorizationClient$Result);
            return;
        }
        this.complete(authorizationClient$Result);
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
    
    RequestBatch createReauthValidationBatch(final AuthorizationClient$Result authorizationClient$Result) {
        final ArrayList list = new ArrayList();
        final ArrayList list2 = new ArrayList();
        final String token = authorizationClient$Result.token.getToken();
        final AuthorizationClient$3 authorizationClient$3 = new AuthorizationClient$3(this, list);
        final String previousAccessToken = this.pendingRequest.getPreviousAccessToken();
        final Request getProfileIdRequest = this.createGetProfileIdRequest(previousAccessToken);
        getProfileIdRequest.setCallback(authorizationClient$3);
        final Request getProfileIdRequest2 = this.createGetProfileIdRequest(token);
        getProfileIdRequest2.setCallback(authorizationClient$3);
        final Request getPermissionsRequest = this.createGetPermissionsRequest(previousAccessToken);
        getPermissionsRequest.setCallback(new AuthorizationClient$4(this, list2));
        final RequestBatch requestBatch = new RequestBatch(new Request[] { getProfileIdRequest, getProfileIdRequest2, getPermissionsRequest });
        requestBatch.setBatchApplicationId(this.pendingRequest.getApplicationId());
        requestBatch.addCallback(new AuthorizationClient$5(this, list, authorizationClient$Result, list2));
        return requestBatch;
    }
    
    AuthorizationClient$BackgroundProcessingListener getBackgroundProcessingListener() {
        return this.backgroundProcessingListener;
    }
    
    boolean getInProgress() {
        return this.pendingRequest != null && this.currentHandler != null;
    }
    
    AuthorizationClient$OnCompletedListener getOnCompletedListener() {
        return this.onCompletedListener;
    }
    
    AuthorizationClient$StartActivityDelegate getStartActivityDelegate() {
        if (this.startActivityDelegate != null) {
            return this.startActivityDelegate;
        }
        if (this.pendingRequest != null) {
            return new AuthorizationClient$2(this);
        }
        return null;
    }
    
    boolean onActivityResult(final int n, final int n2, final Intent intent) {
        return n == this.pendingRequest.getRequestCode() && this.currentHandler.onActivityResult(n, n2, intent);
    }
    
    void setBackgroundProcessingListener(final AuthorizationClient$BackgroundProcessingListener backgroundProcessingListener) {
        this.backgroundProcessingListener = backgroundProcessingListener;
    }
    
    void setContext(final Activity context) {
        this.context = (Context)context;
        this.startActivityDelegate = new AuthorizationClient$1(this, context);
    }
    
    void setContext(final Context context) {
        this.context = context;
        this.startActivityDelegate = null;
    }
    
    void setOnCompletedListener(final AuthorizationClient$OnCompletedListener onCompletedListener) {
        this.onCompletedListener = onCompletedListener;
    }
    
    void startOrContinueAuth(final AuthorizationClient$AuthorizationRequest authorizationClient$AuthorizationRequest) {
        if (this.getInProgress()) {
            this.continueAuth();
            return;
        }
        this.authorize(authorizationClient$AuthorizationRequest);
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
    
    void validateSameFbidAndFinish(final AuthorizationClient$Result authorizationClient$Result) {
        if (authorizationClient$Result.token == null) {
            throw new FacebookException("Can't validate without a token");
        }
        final RequestBatch reauthValidationBatch = this.createReauthValidationBatch(authorizationClient$Result);
        this.notifyBackgroundProcessingStart();
        reauthValidationBatch.executeAsync();
    }
}
