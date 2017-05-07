// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.io.Serializable;

class AuthorizationClient$Result implements Serializable
{
    private static final long serialVersionUID = 1L;
    final AuthorizationClient$Result$Code code;
    final String errorMessage;
    final AccessToken token;
    
    private AuthorizationClient$Result(final AuthorizationClient$Result$Code code, final AccessToken token, final String errorMessage) {
        this.token = token;
        this.errorMessage = errorMessage;
        this.code = code;
    }
    
    static AuthorizationClient$Result createCancelResult(final String s) {
        return new AuthorizationClient$Result(AuthorizationClient$Result$Code.CANCEL, null, s);
    }
    
    static AuthorizationClient$Result createErrorResult(final String s, final String s2) {
        String string = s;
        if (s2 != null) {
            string = s + ": " + s2;
        }
        return new AuthorizationClient$Result(AuthorizationClient$Result$Code.ERROR, null, string);
    }
    
    static AuthorizationClient$Result createTokenResult(final AccessToken accessToken) {
        return new AuthorizationClient$Result(AuthorizationClient$Result$Code.SUCCESS, accessToken, null);
    }
}
