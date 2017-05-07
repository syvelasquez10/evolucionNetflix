// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

public enum AccessTokenSource
{
    CLIENT_TOKEN(true), 
    FACEBOOK_APPLICATION_NATIVE(true), 
    FACEBOOK_APPLICATION_SERVICE(true), 
    FACEBOOK_APPLICATION_WEB(true), 
    NONE(false), 
    TEST_USER(true), 
    WEB_VIEW(false);
    
    private final boolean canExtendToken;
    
    private AccessTokenSource(final boolean canExtendToken) {
        this.canExtendToken = canExtendToken;
    }
    
    boolean canExtendToken() {
        return this.canExtendToken;
    }
}
