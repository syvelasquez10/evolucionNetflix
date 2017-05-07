// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import com.facebook.widget.WebDialog$BuilderBase;
import android.app.Activity;
import android.content.Intent;
import com.facebook.android.R$string;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.Serializable;
import android.content.Context;
import com.facebook.widget.WebDialog$Builder;
import android.text.TextUtils;
import java.util.Collection;
import com.facebook.internal.Utility;
import android.webkit.CookieSyncManager;
import android.os.Bundle;
import com.facebook.widget.WebDialog$OnCompleteListener;
import com.facebook.widget.WebDialog;

class AuthorizationClient$WebViewAuthHandler extends AuthorizationClient$AuthHandler
{
    private static final long serialVersionUID = 1L;
    private String applicationId;
    private String e2e;
    private transient WebDialog loginDialog;
    final /* synthetic */ AuthorizationClient this$0;
    
    AuthorizationClient$WebViewAuthHandler(final AuthorizationClient this$0) {
        this.this$0 = this$0;
        super(this$0);
    }
    
    private String loadCookieToken() {
        return ((Context)this.this$0.getStartActivityDelegate().getActivityContext()).getSharedPreferences("com.facebook.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).getString("TOKEN", "");
    }
    
    private void saveCookieToken(final String s) {
        ((Context)this.this$0.getStartActivityDelegate().getActivityContext()).getSharedPreferences("com.facebook.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).edit().putString("TOKEN", s).apply();
    }
    
    @Override
    void cancel() {
        if (this.loginDialog != null) {
            this.loginDialog.setOnCompleteListener(null);
            this.loginDialog.dismiss();
            this.loginDialog = null;
        }
    }
    
    @Override
    String getNameForLogging() {
        return "web_view";
    }
    
    @Override
    boolean needsInternetPermission() {
        return true;
    }
    
    @Override
    boolean needsRestart() {
        return true;
    }
    
    void onWebDialogComplete(final AuthorizationClient$AuthorizationRequest authorizationClient$AuthorizationRequest, final Bundle bundle, final FacebookException ex) {
        AuthorizationClient$Result authorizationClient$Result;
        if (bundle != null) {
            if (bundle.containsKey("e2e")) {
                this.e2e = bundle.getString("e2e");
            }
            final AccessToken fromWebBundle = AccessToken.createFromWebBundle(authorizationClient$AuthorizationRequest.getPermissions(), bundle, AccessTokenSource.WEB_VIEW);
            authorizationClient$Result = AuthorizationClient$Result.createTokenResult(this.this$0.pendingRequest, fromWebBundle);
            CookieSyncManager.createInstance(this.this$0.context).sync();
            this.saveCookieToken(fromWebBundle.getToken());
        }
        else if (ex instanceof FacebookOperationCanceledException) {
            authorizationClient$Result = AuthorizationClient$Result.createCancelResult(this.this$0.pendingRequest, "User canceled log in.");
        }
        else {
            this.e2e = null;
            String s = ex.getMessage();
            String format;
            if (ex instanceof FacebookServiceException) {
                final FacebookRequestError requestError = ((FacebookServiceException)ex).getRequestError();
                format = String.format("%d", requestError.getErrorCode());
                s = requestError.toString();
            }
            else {
                format = null;
            }
            authorizationClient$Result = AuthorizationClient$Result.createErrorResult(this.this$0.pendingRequest, null, s, format);
        }
        if (!Utility.isNullOrEmpty(this.e2e)) {
            this.this$0.logWebLoginCompleted(this.applicationId, this.e2e);
        }
        this.this$0.completeAndValidate(authorizationClient$Result);
    }
    
    @Override
    boolean tryAuthorize(final AuthorizationClient$AuthorizationRequest authorizationClient$AuthorizationRequest) {
        this.applicationId = authorizationClient$AuthorizationRequest.getApplicationId();
        final Bundle bundle = new Bundle();
        if (!Utility.isNullOrEmpty(authorizationClient$AuthorizationRequest.getPermissions())) {
            final String join = TextUtils.join((CharSequence)",", (Iterable)authorizationClient$AuthorizationRequest.getPermissions());
            bundle.putString("scope", join);
            this.addLoggingExtra("scope", join);
        }
        bundle.putString("default_audience", authorizationClient$AuthorizationRequest.getDefaultAudience().getNativeProtocolAudience());
        final String previousAccessToken = authorizationClient$AuthorizationRequest.getPreviousAccessToken();
        if (!Utility.isNullOrEmpty(previousAccessToken) && previousAccessToken.equals(this.loadCookieToken())) {
            bundle.putString("access_token", previousAccessToken);
            this.addLoggingExtra("access_token", "1");
        }
        else {
            Utility.clearFacebookCookies(this.this$0.context);
            this.addLoggingExtra("access_token", "0");
        }
        final AuthorizationClient$WebViewAuthHandler$1 onCompleteListener = new AuthorizationClient$WebViewAuthHandler$1(this, authorizationClient$AuthorizationRequest);
        this.addLoggingExtra("e2e", this.e2e = getE2E());
        (this.loginDialog = new AuthorizationClient$AuthDialogBuilder((Context)this.this$0.getStartActivityDelegate().getActivityContext(), this.applicationId, bundle).setE2E(this.e2e).setIsRerequest(authorizationClient$AuthorizationRequest.isRerequest()).setOnCompleteListener(onCompleteListener).build()).show();
        return true;
    }
}
