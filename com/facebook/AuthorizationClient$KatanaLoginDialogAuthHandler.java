// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.Collection;
import java.util.ArrayList;
import android.os.Bundle;
import android.content.Intent;

class AuthorizationClient$KatanaLoginDialogAuthHandler extends AuthorizationClient$KatanaAuthHandler
{
    private static final long serialVersionUID = 1L;
    final /* synthetic */ AuthorizationClient this$0;
    
    AuthorizationClient$KatanaLoginDialogAuthHandler(final AuthorizationClient this$0) {
        this.this$0 = this$0;
        super(this$0);
    }
    
    private AuthorizationClient$Result handleResultOk(final Intent intent) {
        final AuthorizationClient$Result authorizationClient$Result = null;
        final Bundle extras = intent.getExtras();
        final String string = extras.getString("com.facebook.platform.status.ERROR_TYPE");
        AuthorizationClient$Result tokenResult;
        if (string == null) {
            tokenResult = AuthorizationClient$Result.createTokenResult(AccessToken.createFromNativeLogin(extras, AccessTokenSource.FACEBOOK_APPLICATION_NATIVE));
        }
        else {
            tokenResult = authorizationClient$Result;
            if (!"ServiceDisabled".equals(string)) {
                if ("UserCanceled".equals(string)) {
                    return AuthorizationClient$Result.createCancelResult(null);
                }
                return AuthorizationClient$Result.createErrorResult(string, extras.getString("error_description"));
            }
        }
        return tokenResult;
    }
    
    @Override
    boolean onActivityResult(final int n, final int n2, final Intent intent) {
        AuthorizationClient$Result authorizationClient$Result = null;
        if (intent == null) {
            authorizationClient$Result = AuthorizationClient$Result.createCancelResult("Operation canceled");
        }
        else if (!NativeProtocol.isServiceDisabledResult20121101(intent)) {
            if (n2 == 0) {
                authorizationClient$Result = AuthorizationClient$Result.createCancelResult(intent.getStringExtra("com.facebook.platform.status.ERROR_DESCRIPTION"));
            }
            else if (n2 != -1) {
                authorizationClient$Result = AuthorizationClient$Result.createErrorResult("Unexpected resultCode from authorization.", null);
            }
            else {
                authorizationClient$Result = this.handleResultOk(intent);
            }
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
        return this.tryIntent(NativeProtocol.createLoginDialog20121101Intent(this.this$0.context, authorizationClient$AuthorizationRequest.getApplicationId(), new ArrayList<String>(authorizationClient$AuthorizationRequest.getPermissions()), authorizationClient$AuthorizationRequest.getDefaultAudience().getNativeProtocolAudience()), authorizationClient$AuthorizationRequest.getRequestCode());
    }
}
