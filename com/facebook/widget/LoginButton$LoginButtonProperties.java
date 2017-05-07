// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import java.util.Collections;
import com.facebook.Session$StatusCallback;
import java.util.List;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionDefaultAudience;
import com.facebook.internal.SessionAuthorizationType;

class LoginButton$LoginButtonProperties
{
    private SessionAuthorizationType authorizationType;
    private SessionDefaultAudience defaultAudience;
    private SessionLoginBehavior loginBehavior;
    private LoginButton$OnErrorListener onErrorListener;
    private List<String> permissions;
    private Session$StatusCallback sessionStatusCallback;
    
    LoginButton$LoginButtonProperties() {
        this.defaultAudience = SessionDefaultAudience.FRIENDS;
        this.permissions = Collections.emptyList();
        this.authorizationType = null;
        this.loginBehavior = SessionLoginBehavior.SSO_WITH_FALLBACK;
    }
}
