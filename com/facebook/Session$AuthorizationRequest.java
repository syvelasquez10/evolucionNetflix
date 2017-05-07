// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import android.support.v4.app.Fragment;
import java.util.Collections;
import android.app.Activity;
import java.util.List;
import java.io.Serializable;

public class Session$AuthorizationRequest implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String applicationId;
    private SessionDefaultAudience defaultAudience;
    private boolean isLegacy;
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
        this.startActivityDelegate = new Session$AuthorizationRequest$1(this, activity);
    }
    
    Session$AuthorizationRequest(final Fragment fragment) {
        this.loginBehavior = SessionLoginBehavior.SSO_WITH_FALLBACK;
        this.requestCode = 64206;
        this.isLegacy = false;
        this.permissions = Collections.emptyList();
        this.defaultAudience = SessionDefaultAudience.FRIENDS;
        this.startActivityDelegate = new Session$AuthorizationRequest$2(this, fragment);
    }
    
    private Session$AuthorizationRequest(final SessionLoginBehavior loginBehavior, final int requestCode, final List<String> permissions, final String s, final boolean isLegacy, final String applicationId, final String validateSameFbidAsToken) {
        this.loginBehavior = SessionLoginBehavior.SSO_WITH_FALLBACK;
        this.requestCode = 64206;
        this.isLegacy = false;
        this.permissions = Collections.emptyList();
        this.defaultAudience = SessionDefaultAudience.FRIENDS;
        this.startActivityDelegate = new Session$AuthorizationRequest$3(this);
        this.loginBehavior = loginBehavior;
        this.requestCode = requestCode;
        this.permissions = permissions;
        this.defaultAudience = SessionDefaultAudience.valueOf(s);
        this.isLegacy = isLegacy;
        this.applicationId = applicationId;
        this.validateSameFbidAsToken = validateSameFbidAsToken;
    }
    
    String getApplicationId() {
        return this.applicationId;
    }
    
    AuthorizationClient$AuthorizationRequest getAuthorizationClientRequest() {
        return new AuthorizationClient$AuthorizationRequest(this.loginBehavior, this.requestCode, this.isLegacy, this.permissions, this.defaultAudience, this.applicationId, this.validateSameFbidAsToken, new Session$AuthorizationRequest$4(this));
    }
    
    Session$StatusCallback getCallback() {
        return this.statusCallback;
    }
    
    SessionDefaultAudience getDefaultAudience() {
        return this.defaultAudience;
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
    
    String getValidateSameFbidAsToken() {
        return this.validateSameFbidAsToken;
    }
    
    boolean isLegacy() {
        return this.isLegacy;
    }
    
    void readObject(final ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Cannot readObject, serialization proxy required");
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
    
    void setValidateSameFbidAsToken(final String validateSameFbidAsToken) {
        this.validateSameFbidAsToken = validateSameFbidAsToken;
    }
    
    Object writeReplace() {
        return new Session$AuthorizationRequest$AuthRequestSerializationProxyV1(this.loginBehavior, this.requestCode, this.permissions, this.defaultAudience.name(), this.isLegacy, this.applicationId, this.validateSameFbidAsToken, null);
    }
}
