// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.app.Activity;
import com.facebook.android.R$string;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import android.content.Context;
import java.io.Serializable;
import com.facebook.internal.NativeProtocol;
import android.os.Bundle;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import android.content.Intent;

class AuthorizationClient$KatanaProxyAuthHandler extends AuthorizationClient$KatanaAuthHandler
{
    private String applicationId;
    final /* synthetic */ AuthorizationClient this$0;
    
    AuthorizationClient$KatanaProxyAuthHandler(final AuthorizationClient this$0) {
        this.this$0 = this$0;
        super(this$0);
    }
    
    private AuthorizationClient$Result handleResultOk(final Intent intent) {
        final Bundle extras = intent.getExtras();
        String s;
        if ((s = extras.getString("error")) == null) {
            s = extras.getString("error_type");
        }
        final String string = extras.getString("error_code");
        String s2;
        if ((s2 = extras.getString("error_message")) == null) {
            s2 = extras.getString("error_description");
        }
        final String string2 = extras.getString("e2e");
        if (!Utility.isNullOrEmpty(string2)) {
            this.this$0.logWebLoginCompleted(this.applicationId, string2);
        }
        if (s == null && string == null && s2 == null) {
            return AuthorizationClient$Result.createTokenResult(this.this$0.pendingRequest, AccessToken.createFromWebBundle(this.this$0.pendingRequest.getPermissions(), extras, AccessTokenSource.FACEBOOK_APPLICATION_WEB));
        }
        if (ServerProtocol.errorsProxyAuthDisabled.contains(s)) {
            return null;
        }
        if (ServerProtocol.errorsUserCanceled.contains(s)) {
            return AuthorizationClient$Result.createCancelResult(this.this$0.pendingRequest, null);
        }
        return AuthorizationClient$Result.createErrorResult(this.this$0.pendingRequest, s, s2, string);
    }
    
    @Override
    String getNameForLogging() {
        return "katana_proxy_auth";
    }
    
    @Override
    boolean onActivityResult(final int n, final int n2, final Intent intent) {
        AuthorizationClient$Result authorizationClient$Result;
        if (intent == null) {
            authorizationClient$Result = AuthorizationClient$Result.createCancelResult(this.this$0.pendingRequest, "Operation canceled");
        }
        else if (n2 == 0) {
            authorizationClient$Result = AuthorizationClient$Result.createCancelResult(this.this$0.pendingRequest, intent.getStringExtra("error"));
        }
        else if (n2 != -1) {
            authorizationClient$Result = AuthorizationClient$Result.createErrorResult(this.this$0.pendingRequest, "Unexpected resultCode from authorization.", null);
        }
        else {
            authorizationClient$Result = this.handleResultOk(intent);
        }
        if (authorizationClient$Result != null) {
            this.this$0.completeAndValidate(authorizationClient$Result);
        }
        else {
            this.this$0.tryNextHandler();
        }
        return true;
    }
    
    @Override
    boolean tryAuthorize(final AuthorizationClient$AuthorizationRequest authorizationClient$AuthorizationRequest) {
        this.applicationId = authorizationClient$AuthorizationRequest.getApplicationId();
        final String access$100 = getE2E();
        final Intent proxyAuthIntent = NativeProtocol.createProxyAuthIntent(this.this$0.context, authorizationClient$AuthorizationRequest.getApplicationId(), authorizationClient$AuthorizationRequest.getPermissions(), access$100, authorizationClient$AuthorizationRequest.isRerequest(), authorizationClient$AuthorizationRequest.getDefaultAudience());
        this.addLoggingExtra("e2e", access$100);
        return this.tryIntent(proxyAuthIntent, authorizationClient$AuthorizationRequest.getRequestCode());
    }
}
