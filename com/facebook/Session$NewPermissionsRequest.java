// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.support.v4.app.Fragment;
import java.util.List;
import android.app.Activity;

public final class Session$NewPermissionsRequest extends Session$AuthorizationRequest
{
    private static final long serialVersionUID = 1L;
    
    public Session$NewPermissionsRequest(final Activity activity, final List<String> permissions) {
        super(activity);
        this.setPermissions(permissions);
    }
    
    public Session$NewPermissionsRequest(final Activity activity, final String... permissions) {
        super(activity);
        this.setPermissions(permissions);
    }
    
    public Session$NewPermissionsRequest(final Fragment fragment, final List<String> permissions) {
        super(fragment);
        this.setPermissions(permissions);
    }
    
    public Session$NewPermissionsRequest(final Fragment fragment, final String... permissions) {
        super(fragment);
        this.setPermissions(permissions);
    }
    
    @Override
    AuthorizationClient$AuthorizationRequest getAuthorizationClientRequest() {
        final AuthorizationClient$AuthorizationRequest authorizationClientRequest = super.getAuthorizationClientRequest();
        authorizationClientRequest.setRerequest(true);
        return authorizationClientRequest;
    }
    
    public final Session$NewPermissionsRequest setCallback(final Session$StatusCallback callback) {
        super.setCallback(callback);
        return this;
    }
    
    public final Session$NewPermissionsRequest setDefaultAudience(final SessionDefaultAudience defaultAudience) {
        super.setDefaultAudience(defaultAudience);
        return this;
    }
    
    public final Session$NewPermissionsRequest setLoginBehavior(final SessionLoginBehavior loginBehavior) {
        super.setLoginBehavior(loginBehavior);
        return this;
    }
    
    public final Session$NewPermissionsRequest setRequestCode(final int requestCode) {
        super.setRequestCode(requestCode);
        return this;
    }
}
