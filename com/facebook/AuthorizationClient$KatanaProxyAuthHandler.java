// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.os.Bundle;
import com.facebook.internal.ServerProtocol;
import android.content.Intent;

class AuthorizationClient$KatanaProxyAuthHandler extends AuthorizationClient$KatanaAuthHandler
{
    private static final long serialVersionUID = 1L;
    final /* synthetic */ AuthorizationClient this$0;
    
    AuthorizationClient$KatanaProxyAuthHandler(final AuthorizationClient this$0) {
        this.this$0 = this$0;
        super(this$0);
    }
    
    private AuthorizationClient$Result handleResultOk(final Intent intent) {
        AuthorizationClient$Result tokenResult = null;
        final Bundle extras = intent.getExtras();
        String s;
        if ((s = extras.getString("error")) == null) {
            s = extras.getString("error_type");
        }
        if (s == null) {
            tokenResult = AuthorizationClient$Result.createTokenResult(AccessToken.createFromWebBundle(this.this$0.pendingRequest.getPermissions(), extras, AccessTokenSource.FACEBOOK_APPLICATION_WEB));
        }
        else if (!ServerProtocol.errorsProxyAuthDisabled.contains(s)) {
            if (ServerProtocol.errorsUserCanceled.contains(s)) {
                return AuthorizationClient$Result.createCancelResult(null);
            }
            return AuthorizationClient$Result.createErrorResult(s, extras.getString("error_description"));
        }
        return tokenResult;
    }
    
    @Override
    boolean onActivityResult(final int n, final int n2, final Intent intent) {
        AuthorizationClient$Result authorizationClient$Result;
        if (intent == null) {
            authorizationClient$Result = AuthorizationClient$Result.createCancelResult("Operation canceled");
        }
        else if (n2 == 0) {
            authorizationClient$Result = AuthorizationClient$Result.createCancelResult(intent.getStringExtra("error"));
        }
        else if (n2 != -1) {
            authorizationClient$Result = AuthorizationClient$Result.createErrorResult("Unexpected resultCode from authorization.", null);
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
        return this.tryIntent(NativeProtocol.createProxyAuthIntent(this.this$0.context, authorizationClient$AuthorizationRequest.getApplicationId(), authorizationClient$AuthorizationRequest.getPermissions()), authorizationClient$AuthorizationRequest.getRequestCode());
    }
}
