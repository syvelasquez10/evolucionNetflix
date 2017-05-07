// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.support.v4.app.Fragment;
import java.util.HashMap;
import java.util.UUID;
import java.util.Collections;
import android.app.Activity;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

public class Session$AuthorizationRequest implements Serializable
{
    private String applicationId;
    private final String authId;
    private SessionDefaultAudience defaultAudience;
    private boolean isLegacy;
    private final Map<String, String> loggingExtras;
    private SessionLoginBehavior loginBehavior;
    private List<String> permissions;
    private int requestCode;
    private final Session$StartActivityDelegate startActivityDelegate;
    private Session$StatusCallback statusCallback;
    private String validateSameFbidAsToken;
    
    Session$AuthorizationRequest(final Activity activity) {
        this.loginBehavior = SessionLoginBehavior.SSO_WITH_FALLBACK;
        this.requestCode = 64206;
        this.isLegacy = false;
        this.permissions = Collections.emptyList();
        this.defaultAudience = SessionDefaultAudience.FRIENDS;
        this.authId = UUID.randomUUID().toString();
        this.loggingExtras = new HashMap<String, String>();
        this.startActivityDelegate = new Session$AuthorizationRequest$1(this, activity);
    }
    
    Session$AuthorizationRequest(final Fragment fragment) {
        this.loginBehavior = SessionLoginBehavior.SSO_WITH_FALLBACK;
        this.requestCode = 64206;
        this.isLegacy = false;
        this.permissions = Collections.emptyList();
        this.defaultAudience = SessionDefaultAudience.FRIENDS;
        this.authId = UUID.randomUUID().toString();
        this.loggingExtras = new HashMap<String, String>();
        this.startActivityDelegate = new Session$AuthorizationRequest$2(this, fragment);
    }
    
    String getAuthId() {
        return this.authId;
    }
    
    AuthorizationClient$AuthorizationRequest getAuthorizationClientRequest() {
        return new AuthorizationClient$AuthorizationRequest(this.loginBehavior, this.requestCode, this.isLegacy, this.permissions, this.defaultAudience, this.applicationId, this.validateSameFbidAsToken, new Session$AuthorizationRequest$4(this), this.authId);
    }
    
    Session$StatusCallback getCallback() {
        return this.statusCallback;
    }
    
    SessionLoginBehavior getLoginBehavior() {
        return this.loginBehavior;
    }
    
    List<String> getPermissions() {
        return this.permissions;
    }
    
    int getRequestCode() {
        return this.requestCode;
    }
    
    Session$StartActivityDelegate getStartActivityDelegate() {
        return this.startActivityDelegate;
    }
    
    void setApplicationId(final String applicationId) {
        this.applicationId = applicationId;
    }
    
    Session$AuthorizationRequest setCallback(final Session$StatusCallback statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }
    
    Session$AuthorizationRequest setDefaultAudience(final SessionDefaultAudience defaultAudience) {
        if (defaultAudience != null) {
            this.defaultAudience = defaultAudience;
        }
        return this;
    }
    
    public void setIsLegacy(final boolean isLegacy) {
        this.isLegacy = isLegacy;
    }
    
    Session$AuthorizationRequest setLoginBehavior(final SessionLoginBehavior loginBehavior) {
        if (loginBehavior != null) {
            this.loginBehavior = loginBehavior;
        }
        return this;
    }
    
    Session$AuthorizationRequest setPermissions(final List<String> permissions) {
        if (permissions != null) {
            this.permissions = permissions;
        }
        return this;
    }
    
    Session$AuthorizationRequest setRequestCode(final int requestCode) {
        if (requestCode >= 0) {
            this.requestCode = requestCode;
        }
        return this;
    }
}
