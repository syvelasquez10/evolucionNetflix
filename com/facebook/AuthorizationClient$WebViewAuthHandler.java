// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import com.facebook.widget.WebDialog$BuilderBase;
import com.facebook.widget.WebDialog$OnCompleteListener;
import android.content.Context;
import com.facebook.widget.WebDialog$Builder;
import android.text.TextUtils;
import java.util.Collection;
import android.webkit.CookieSyncManager;
import android.os.Bundle;
import android.content.SharedPreferences$Editor;
import com.facebook.internal.Utility;
import com.facebook.widget.WebDialog;

class AuthorizationClient$WebViewAuthHandler extends AuthorizationClient$AuthHandler
{
    private static final long serialVersionUID = 1L;
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
        final SharedPreferences$Editor edit = ((Context)this.this$0.getStartActivityDelegate().getActivityContext()).getSharedPreferences("com.facebook.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).edit();
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
    
    void onWebDialogComplete(final AuthorizationClient$AuthorizationRequest authorizationClient$AuthorizationRequest, final Bundle bundle, final FacebookException ex) {
        AuthorizationClient$Result authorizationClient$Result;
        if (bundle != null) {
            final AccessToken fromWebBundle = AccessToken.createFromWebBundle(authorizationClient$AuthorizationRequest.getPermissions(), bundle, AccessTokenSource.WEB_VIEW);
            authorizationClient$Result = AuthorizationClient$Result.createTokenResult(fromWebBundle);
            CookieSyncManager.createInstance(this.this$0.context).sync();
            this.saveCookieToken(fromWebBundle.getToken());
        }
        else if (ex instanceof FacebookOperationCanceledException) {
            authorizationClient$Result = AuthorizationClient$Result.createCancelResult("User canceled log in.");
        }
        else {
            authorizationClient$Result = AuthorizationClient$Result.createErrorResult(ex.getMessage(), null);
        }
        this.this$0.completeAndValidate(authorizationClient$Result);
    }
    
    @Override
    boolean tryAuthorize(final AuthorizationClient$AuthorizationRequest authorizationClient$AuthorizationRequest) {
        final String applicationId = authorizationClient$AuthorizationRequest.getApplicationId();
        final Bundle bundle = new Bundle();
        if (!Utility.isNullOrEmpty(authorizationClient$AuthorizationRequest.getPermissions())) {
            bundle.putString("scope", TextUtils.join((CharSequence)",", (Iterable)authorizationClient$AuthorizationRequest.getPermissions()));
        }
        final String previousAccessToken = authorizationClient$AuthorizationRequest.getPreviousAccessToken();
        if (!Utility.isNullOrEmpty(previousAccessToken) && previousAccessToken.equals(this.loadCookieToken())) {
            bundle.putString("access_token", previousAccessToken);
        }
        else {
            Utility.clearFacebookCookies(this.this$0.context);
        }
        (this.loginDialog = new AuthorizationClient$AuthDialogBuilder((Context)this.this$0.getStartActivityDelegate().getActivityContext(), applicationId, bundle).setOnCompleteListener(new AuthorizationClient$WebViewAuthHandler$1(this, authorizationClient$AuthorizationRequest)).build()).show();
        return true;
    }
}
