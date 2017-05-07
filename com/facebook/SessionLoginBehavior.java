// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

public enum SessionLoginBehavior
{
    SSO_ONLY(true, false), 
    SSO_WITH_FALLBACK(true, true), 
    SUPPRESS_SSO(false, true);
    
    private final boolean allowsKatanaAuth;
    private final boolean allowsWebViewAuth;
    
    private SessionLoginBehavior(final boolean allowsKatanaAuth, final boolean allowsWebViewAuth) {
        this.allowsKatanaAuth = allowsKatanaAuth;
        this.allowsWebViewAuth = allowsWebViewAuth;
    }
    
    boolean allowsKatanaAuth() {
        return this.allowsKatanaAuth;
    }
    
    boolean allowsWebViewAuth() {
        return this.allowsWebViewAuth;
    }
}
